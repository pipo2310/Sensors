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


--TablaSensores
-- Tabla Cuentas
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja) VALUES (1,'ml/s', 1, true , true);
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja) VALUES (2,'mg/m3', 1, true, false);
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja) VALUES (3,'ml/s', 1, false , false);
INSERT INTO sensores (sensores_pk,unidad, id_cuenta, alerta_amarilla, alerta_roja) VALUES (4,'Ws', 1, false, false);