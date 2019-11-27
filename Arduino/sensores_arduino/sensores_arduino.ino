#include "Adafruit_FONA.h"

#define FONA_RX 2
#define FONA_TX 3
#define FONA_RST 4
#define FLOWSENSORPIN 6

//Para app
#define ID_SENSOR_AGUA 3

// this is a large buffer for replies
char replybuffer[255];

//Serial FONA
#include <SoftwareSerial.h>
SoftwareSerial fonaSS = SoftwareSerial(FONA_TX, FONA_RX);
SoftwareSerial *fonaSerial = &fonaSS;

//Variables FONA
Adafruit_FONA fona = Adafruit_FONA(FONA_RST);
uint8_t readline(char *buff, uint8_t maxbuff, uint16_t timeout = 0);
uint8_t type;


//Variables y funciones para sensor de agua, extraido de https://github.com/adafruit/Adafruit-Flow-Meter/blob/master/Adafruit_FlowMeter.pde
// Para propositos de la app, se quere obtener los litros que pasaron por el sensor desde la ultima medicion, si este es diferente de 0, se hace un POST a la base de datos.
volatile float litros_nuevos = 0.00;
volatile float litros_anteriores = 0.00;
// count how many pulses!
volatile uint16_t pulses = 0;
// track the state of the pulse pin
volatile uint8_t lastflowpinstate;
// you can try to keep time of how long it is between pulses
volatile uint32_t lastflowratetimer = 0;
// and use that to calculate a flow rate
volatile float flowrate;
// Interrupt is called once a millisecond, looks for any pulses from the sensor!
SIGNAL(TIMER0_COMPA_vect) {
  uint8_t x = digitalRead(FLOWSENSORPIN);
  
  if (x == lastflowpinstate) {
    lastflowratetimer++;
    return; // nothing changed!
  }
  
  if (x == HIGH) {
    //low to high transition!
    pulses++;
  }
  lastflowpinstate = x;
  flowrate = 1000.0;
  flowrate /= lastflowratetimer;  // in hertz
  lastflowratetimer = 0;
}

void useInterrupt(boolean v) {
  if (v) {
    // Timer0 is already used for millis() - we'll just interrupt somewhere
    // in the middle and call the "Compare A" function above
    OCR0A = 0xAF;
    TIMSK0 |= _BV(OCIE0A);
  } else {
    // do not call the interrupt function COMPA anymore
    TIMSK0 &= ~_BV(OCIE0A);
  }
}

//Realiza post al servidor, tomado del programa de ejemplo para el FONA
void postDatos(int id, float valor){
  // Post data to website
  Serial.println("Begin POST");
        uint16_t statuscode;
        int16_t length;
        char url[]  = "ec2-34-235-147-100.compute-1.amazonaws.com/sensores/api/sensoresLogs_post";
        String jsonstring = "{\"sensoresLogsPk\":{\"id_sensor\":" + String(id) + ",\"dateTime\":\"2019-12-30T09:43:21.2999\"},\"valor\":" + String(valor) + "}";
        char json[200];
        jsonstring.toCharArray(json,jsonstring.length()+1);

        Serial.println(jsonstring);
        Serial.println(url);
        Serial.println(json);

        Serial.println(F("****"));
        if (!fona.HTTP_POST_start(url, F("application/json"), json, strlen(json), &statuscode, (uint16_t *)&length)) {
          Serial.println("Failed!");
        }
        else {
           Serial.println("POST!");
          while (length > 0) {
          while (fona.available()) {
            char c = fona.read();

#if defined(__AVR_ATmega328P__) || defined(__AVR_ATmega168__)
            loop_until_bit_is_set(UCSR0A, UDRE0); /* Wait until data register empty. */
            UDR0 = c;
#else
            Serial.write(c);
#endif

            length--;
            if (! length) break;
          }
        }
        Serial.println(F("\n****"));
        fona.HTTP_POST_end();
        Serial.println("End POST");
        }
}


void setup() {
  while (!Serial);

  Serial.begin(115200);
  Serial.println(F("FONA basic test"));
  Serial.println(F("Initializing....(May take 3 seconds)"));

  fonaSerial->begin(4800);
  if (! fona.begin(*fonaSerial)) {
    Serial.println(F("Couldn't find FONA"));
    while (1);
  }
  type = fona.type();
  Serial.println(F("FONA is OK"));
  Serial.print(F("Found "));
  switch (type) {
    case FONA800L:
      Serial.println(F("FONA 800L")); break;
    case FONA800H:
      Serial.println(F("FONA 800H")); break;
    case FONA808_V1:
      Serial.println(F("FONA 808 (v1)")); break;
    case FONA808_V2:
      Serial.println(F("FONA 808 (v2)")); break;
    case FONA3G_A:
      Serial.println(F("FONA 3G (American)")); break;
    case FONA3G_E:
      Serial.println(F("FONA 3G (European)")); break;
    default: 
      Serial.println(F("???")); break;
  }
  
  // Print module IMEI number.
  char imei[16] = {0}; // MUST use a 16 character buffer for IMEI!
  uint8_t imeiLen = fona.getIMEI(imei);
  if (imeiLen > 0) {
    Serial.print("Module IMEI: "); Serial.println(imei);
  }

  //Para la comunicacion del GPRS se tiene que definir el APN del proveedor, en este caso Kolbi
  fona.setGPRSNetworkSettings(F("kolbi3g"));

  //Inicializa GPRS para conexion a internet
  fona.enableGPRS(true);


  //Prueba si la conexion GPRS esta funcionando
  uint16_t statuscode;
  int16_t length;
  char url[80];
  String url_string = "google.com";
  url_string.toCharArray(url,80);
  if (fona.HTTP_GET_start(url, &statuscode, (uint16_t *)&length)) {
          Serial.println("CONEXION A INTERNET EXITOSA");
  } else Serial.println("FALLO CONEXION A INTERNET");

  //Para sensor Agua
  pinMode(FLOWSENSORPIN, INPUT);
  digitalWrite(FLOWSENSORPIN, HIGH);
  lastflowpinstate = digitalRead(FLOWSENSORPIN);
  useInterrupt(true);

}

void loop() {
  //Una vez que inicializa el modulo GPRS
  Serial.print("Freq: "); Serial.println(flowrate);
  Serial.print("Pulses: "); Serial.println(pulses, DEC);
  
  // if a plastic sensor use the following calculation
  // Sensor Frequency (Hz) = 7.5 * Q (Liters/min)
  // Liters = Q * time elapsed (seconds) / 60 (seconds/minute)
  // Liters = (Frequency (Pulses/second) / 7.5) * time elapsed (seconds) / 60
  // Liters = Pulses / (7.5 * 60)
  float liters = pulses;
  liters /= 7.5;
  liters /= 60.0;

  Serial.print(liters); Serial.println(" Liters");

  //Compara los litros actuales con los litros del ciclo anterior para ver si incremento la variable. Si si, realiza el POST al servidor.
  litros_nuevos = liters - litros_anteriores;
  Serial.print("Litros nuevos: "); Serial.println(litros_nuevos);
  if (litros_nuevos != 0.00){
    postDatos(ID_SENSOR_AGUA,litros_nuevos);
  }
  // Espera
  litros_nuevos = 0.00;
  litros_anteriores = liters;
  delay(5000);
}
