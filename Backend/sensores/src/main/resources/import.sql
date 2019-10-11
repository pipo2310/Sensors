-- Pruebas

-- Tabla test
INSERT INTO test (test_pk, description) VALUES (1,'Hola');
INSERT INTO test (test_pk, description) VALUES (2,'Adios');
INSERT INTO test (test_pk, description) VALUES (3,'ñññ');
INSERT INTO test (test_pk, description) VALUES (4,'asdkjnsakjda');

----------------------------------------------------------------------------------------

-- Real

-- Tabla Cuentas
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, email, usuario, nombre, codigo) VALUES (1,true, '87334455', '123', 'kat@gmail.com', 'kat', 'katherine', '11');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, email, usuario, nombre, codigo) VALUES (2,true, '87592234', '145', 'cris@gmail.com', 'cris', 'christian', '22');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, email, usuario, nombre, codigo) VALUES (3,true, '86265544', '777', 'wal@gmail.com', 'wal', 'walter', '33');
INSERT INTO cuentas (cuentas_pk,es_admin, telefono, clave, email, usuario, nombre, codigo) VALUES (4,true, '87654533', '890', 'jav@gmail.com', 'jav', 'javier', '44');


--Tabla Tipo Sensor
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global) VALUES (1, 'Gas', 12.3 , 19.5);
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global) VALUES (2, 'Electricidad', 12.3 , 19.5);
INSERT INTO tipo_sensor (id_tipo, nombre, alerta_amarilla_global, alerta_roja_global) VALUES (3, 'Agua', 12.3 , 19.5);

--TablaSensores
-- Tabla Cuentas
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES (1,'mg/m3', 1, 12.3 , 20.0, 1, 'Sensor de Gas 1');
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES (2,'Ws', 1, 12.3 , 20.0, 2, 'Sensor de Electricidad 1');
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES (3,'ml/s', 1, 12.3 , 20.0, 3, 'Sensor de Agua 1');
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja, tipo, nombre) VALUES (4,'m1/s', 1, 12.3 , 20.0, 3 , 'Sensor de Agua 2');


--Tabla Sensores Logs



