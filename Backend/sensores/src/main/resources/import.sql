-- Pruebas

-- Tabla test
INSERT INTO test (test_pk, description) VALUES (1,'Hola');
INSERT INTO test (test_pk, description) VALUES (2,'Adios');
INSERT INTO test (test_pk, description) VALUES (3,'ñññ');
INSERT INTO test (test_pk, description) VALUES (4,'asdkjnsakjda');

----------------------------------------------------------------------------------------

-- Real

-- Tabla Cuentas
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, seccion, email, usuario, nombre, codigo) VALUES (1,true, '87334455', '123', 'Alimentos', 'walmart@gmail.com', 'kat', 'Walmart', '9F4636P3');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, seccion, email, usuario, nombre, codigo) VALUES (2,true, '87592234', '456', 'Alimentos', 'florida@gmail.com', 'cris', 'Florida Bebidas', 'XCGPQU8V');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, seccion, email, usuario, nombre, codigo) VALUES (3,true, '86265544', '789', 'Alimentos', 'wal@gmail.com', 'wal', 'Perimercados', 'GZRAK38Z');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, seccion, email, usuario, nombre, codigo) VALUES (4,true, '87654533', '100', 'Alimentos', 'jav@gmail.com', 'jav', 'Automercado', '3CDWV48H');


--Tabla Tipo Sensor
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global, costo) VALUES (1, 'Gas', 12.3 , 19.5, 1.0);
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global, costo) VALUES (2, 'Electricidad', 12.3 , 19.5,2.0);
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global, costo) VALUES (3, 'Agua', 12.3 , 19.5,3.0);

--TablaSensores
INSERT INTO sensores (unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES ('mg/m3', 1, 12.3 , 20.0, 1, 'Sensor de Gas 1');
INSERT INTO sensores (unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES ('Ws', 1, 12.3 , 20.0, 2, 'Sensor de Electricidad 1');
INSERT INTO sensores (unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES ('ml/s', 1, 12.3 , 20.0, 3, 'Sensor de Agua 1');
INSERT INTO sensores (unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES ('m1/s', 1, 12.3 , 20.0, 3 , 'Sensor de Agua 2');



--Tabla Sensores Logs

INSERT INTO sensores_logs VALUES ('2018-11-12 09:43:21.043412', 3 ,12.3);
INSERT INTO sensores_logs VALUES ('2018-12-23 09:43:21.043412', 3 ,8.8);
INSERT INTO sensores_logs VALUES ('2019-01-21 09:43:21.043412', 3 ,10.4);
INSERT INTO sensores_logs VALUES ('2019-02-10 09:43:21.043412', 3 ,13.1);
INSERT INTO sensores_logs VALUES ('2019-03-04 09:43:21.043412', 3 ,6.9);
INSERT INTO sensores_logs VALUES ('2019-04-16 09:43:21.043412', 3 ,7.7);
INSERT INTO sensores_logs VALUES ('2019-05-12 09:43:21.043412', 3 ,13.9);
INSERT INTO sensores_logs VALUES ('2019-06-03 09:43:21.043412', 3 ,6.2);
INSERT INTO sensores_logs VALUES ('2019-07-10 09:43:21.043412', 3 ,16.3);
INSERT INTO sensores_logs VALUES ('2019-08-14 09:43:21.043412', 3 ,9.5);
INSERT INTO sensores_logs VALUES ('2019-09-20 09:43:21.043412', 3 ,5.8);
INSERT INTO sensores_logs VALUES ('2019-10-10 09:43:21.043412', 3 ,11.3);

INSERT INTO sensores_logs VALUES ('2019-10-3 09:43:21.043412', 4 ,7.4);
INSERT INTO sensores_logs VALUES ('2019-10-9 09:43:21.043412', 3 ,14.7);
INSERT INTO sensores_logs VALUES ('2019-10-17 09:43:21.043412', 4 ,6.1);
INSERT INTO sensores_logs VALUES ('2019-10-24 09:43:21.043412', 3 ,6.3);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:43:21.043412', 3 ,12.5);

INSERT INTO sensores_logs VALUES ('2019-10-25 09:43:21.043412', 3 ,5.3);
INSERT INTO sensores_logs VALUES ('2019-10-26 09:43:21.043412', 3 ,3.1);
INSERT INTO sensores_logs VALUES ('2019-10-27 09:43:21.043412', 3 ,18.2);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:44:21.043412', 3 ,7.8);
INSERT INTO sensores_logs VALUES ('2019-10-29 09:43:21.043412', 3 ,10.3);
INSERT INTO sensores_logs VALUES ('2019-10-30 09:43:21.043412', 3 ,9.1);
INSERT INTO sensores_logs VALUES ('2019-10-31 09:43:21.043412', 3 ,6.3);

-- ELECTRICIDAD
INSERT INTO sensores_logs (id_sensor, valor) VALUES (2 ,1.3);
INSERT INTO sensores_logs VALUES ('2018-11-12 09:43:21.043412', 2 ,1.3);
INSERT INTO sensores_logs VALUES ('2018-12-23 09:43:21.043412', 2 ,1.8);
INSERT INTO sensores_logs VALUES ('2019-01-21 09:43:21.043412', 2 ,1.4);
INSERT INTO sensores_logs VALUES ('2019-02-10 09:43:21.043412', 2 ,1.1);
INSERT INTO sensores_logs VALUES ('2019-03-04 09:43:21.043412', 2 ,1.9);
INSERT INTO sensores_logs VALUES ('2019-04-16 09:43:21.043412', 2 ,1.7);
INSERT INTO sensores_logs VALUES ('2019-05-12 09:43:21.043412', 2 ,1.9);
INSERT INTO sensores_logs VALUES ('2019-06-03 09:43:21.043412', 2 ,1.2);
INSERT INTO sensores_logs VALUES ('2019-07-10 09:43:21.043412', 2 ,1.3);
INSERT INTO sensores_logs VALUES ('2019-08-14 09:43:21.043412', 2 ,1.5);
INSERT INTO sensores_logs VALUES ('2019-09-20 09:43:21.043412', 2 ,1.8);
INSERT INTO sensores_logs VALUES ('2019-10-10 09:43:21.043412', 2 ,1.3);

INSERT INTO sensores_logs VALUES ('2019-10-3 09:43:21.043412', 2 ,7.4);
INSERT INTO sensores_logs VALUES ('2019-10-9 09:43:21.043412', 2 ,14.7);
INSERT INTO sensores_logs VALUES ('2019-10-17 09:43:21.043412', 2 ,6.1);
INSERT INTO sensores_logs VALUES ('2019-10-24 09:43:21.043412', 2 ,6.3);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:43:21.043412', 2 ,12.5);

INSERT INTO sensores_logs VALUES ('2019-10-25 09:43:21.043412', 2 ,5.3);
INSERT INTO sensores_logs VALUES ('2019-10-26 09:43:21.043412', 2 ,3.1);
INSERT INTO sensores_logs VALUES ('2019-10-27 09:43:21.043412', 2 ,18.2);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:44:21.043412', 2 ,7.8);
INSERT INTO sensores_logs VALUES ('2019-10-29 09:43:21.043412', 2 ,10.3);
INSERT INTO sensores_logs VALUES ('2019-10-30 09:43:21.043412', 2 ,9.1);
INSERT INTO sensores_logs VALUES ('2019-10-31 09:43:21.043412', 2 ,6.3);


-- GAS

INSERT INTO sensores_logs VALUES ('2018-11-12 09:43:21.043412', 1 ,8.3);
INSERT INTO sensores_logs VALUES ('2018-12-23 09:43:21.043412', 1 ,8.8);
INSERT INTO sensores_logs VALUES ('2019-01-21 09:43:21.043412', 1 ,8.4);
INSERT INTO sensores_logs VALUES ('2019-02-10 09:43:21.043412', 1 ,8.1);
INSERT INTO sensores_logs VALUES ('2019-03-04 09:43:21.043412', 1 ,8.9);
INSERT INTO sensores_logs VALUES ('2019-04-16 09:43:21.043412', 1 ,8.7);
INSERT INTO sensores_logs VALUES ('2019-05-12 09:43:21.043412', 1 ,8.9);
INSERT INTO sensores_logs VALUES ('2019-06-03 09:43:21.043412', 1 ,8.2);
INSERT INTO sensores_logs VALUES ('2019-07-10 09:43:21.043412', 1 ,8.3);
INSERT INTO sensores_logs VALUES ('2019-08-14 09:43:21.043412', 1 ,8.5);
INSERT INTO sensores_logs VALUES ('2019-09-20 09:43:21.043412', 1 ,8.8);
INSERT INTO sensores_logs VALUES ('2019-10-10 09:43:21.043412', 1 ,8.3);

INSERT INTO sensores_logs VALUES ('2019-10-3 09:43:21.043412', 1 ,7.4);
INSERT INTO sensores_logs VALUES ('2019-10-9 09:43:21.043412', 1 ,14.7);
INSERT INTO sensores_logs VALUES ('2019-10-17 09:43:21.043412', 1 ,6.1);
INSERT INTO sensores_logs VALUES ('2019-10-24 09:43:21.043412', 1 ,6.3);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:43:21.043412', 1 ,12.5);

INSERT INTO sensores_logs VALUES ('2019-10-25 09:43:21.043412', 1 ,5.3);
INSERT INTO sensores_logs VALUES ('2019-10-26 09:43:21.043412', 1 ,3.1);
INSERT INTO sensores_logs VALUES ('2019-10-27 09:43:21.043412', 1 ,18.2);
INSERT INTO sensores_logs VALUES ('2019-10-28 09:44:21.043412', 1 ,7.8);
INSERT INTO sensores_logs VALUES ('2019-10-29 09:43:21.043412', 1 ,10.3);
INSERT INTO sensores_logs VALUES ('2019-10-30 09:43:21.043412', 1 ,9.1);
INSERT INTO sensores_logs VALUES ('2019-10-31 09:43:21.043412', 1 ,6.3);