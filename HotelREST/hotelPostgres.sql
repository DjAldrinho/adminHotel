CREATE TABLE hoteles (
  idhotel         SERIAL PRIMARY KEY,
  idadministrador INT               NOT NULL,
  nombre          CHARACTER VARYING NOT NULL UNIQUE,
  pais            CHARACTER VARYING NOT NULL,
  ciudad          CHARACTER VARYING NOT NULL,
  direccion       CHARACTER VARYING NOT NULL
);


CREATE TABLE usuarios (
  idusuario      SERIAL PRIMARY KEY,
  idpersona      INT               NOT NULL,
  email          CHARACTER VARYING NOT NULL UNIQUE,
  password       CHARACTER VARYING NOT NULL,
  cargo          CHARACTER VARYING NOT NULL,
  fecha_registro TIMESTAMP DEFAULT now()
);


ALTER TABLE usuarios
  ADD CONSTRAINT
  fk_usuarios_personas
FOREIGN KEY (idpersona)
REFERENCES personas (idpersona) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE personas (
  idpersona      SERIAL PRIMARY KEY,
  idusuario      INT               NULL,
  idhotel        INT               NULL,
  nombres        CHARACTER VARYING NOT NULL,
  apellidos      CHARACTER VARYING NOT NULL,
  identificacion CHARACTER VARYING NOT NULL UNIQUE
);

ALTER TABLE personas
  ADD CONSTRAINT
  fk_personas_usuarios
FOREIGN KEY (idusuario)
REFERENCES usuarios (idusuario) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE personas
  ADD CONSTRAINT
  fk_personas_hoteles
FOREIGN KEY (idhotel)
REFERENCES hoteles (idhotel) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE habitaciones (
  idhabitacion SERIAL PRIMARY KEY,
  idhotel      INT               NOT NULL,
  nombre       CHARACTER VARYING NOT NULL UNIQUE,
  descripcion  TEXT              NULL,
  capacidad    INT               NOT NULL,
  tipo         CHARACTER VARYING NOT NULL,
  estado       CHARACTER VARYING NOT NULL,
  precio       INT               NOT NULL,
  planta       CHARACTER VARYING NOT NULL
);

ALTER TABLE habitaciones
  ADD CONSTRAINT fk_habitaciones_hoteles FOREIGN KEY (idhotel)
REFERENCES hoteles (idhotel) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE reservas (
  idreserva            SERIAL PRIMARY KEY,
  idhabitacion         INT               NOT NULL,
  idpersona            INT               NOT NULL,
  ref_reserva          CHARACTER VARYING NOT NULL UNIQUE,
  fecha_solicitud      DATE              NOT NULL DEFAULT now(),
  fecha_reserva        DATE              NOT NULL,
  finalizacion_reserva DATE              NULL
);

ALTER TABLE reservas
  ADD CONSTRAINT fk_reservas_habitaciones FOREIGN KEY (idhabitacion)
REFERENCES habitaciones (idhabitacion) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reservas
  ADD CONSTRAINT fk_reservas_personas FOREIGN KEY (idpersona)
REFERENCES personas (idpersona) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE servicios (
  idservicio    SERIAL PRIMARY KEY,
  nombre        CHARACTER VARYING NOT NULL UNIQUE,
  precio_unidad INT               NOT NULL,
  tipo          CHARACTER VARYING NOT NULL
);


CREATE TABLE reservas_servicios (
  idservicio INT NOT NULL,
  idreserva  INT NOT NULL,
  cantidad   INT NOT NULL,
  PRIMARY KEY (idreserva, idservicio)
);

ALTER TABLE reservas_servicios
  ADD CONSTRAINT fk_rs_servicios FOREIGN KEY (idservicio)
REFERENCES servicios (idservicio) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reservas_servicios
  ADD CONSTRAINT fk_rs_reservas FOREIGN KEY (idreserva)
REFERENCES reservas (idreserva) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE planillas (
  idplanilla  SERIAL UNIQUE PRIMARY KEY,
  idencargado INT               NOT NULL,
  fecha       DATE              NOT NULL DEFAULT now(),
  turno       CHARACTER VARYING NOT NULL,
  tipo        CHARACTER VARYING NOT NULL
);

ALTER TABLE planillas
  ADD CONSTRAINT fk_planillas_personas FOREIGN KEY (idencargado)
REFERENCES personas (idpersona) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE asistentes (
  idplanilla    INT               NOT NULL,
  idpersona     INT               NOT NULL,
  hora_entrada  TIME              NOT NULL,
  hora_salida   TIME              NULL,
  proposito     CHARACTER VARYING NOT NULL,
  observaciones TEXT              NULL,
  PRIMARY KEY (idplanilla, idpersona)
);

ALTER TABLE asistentes
  ADD CONSTRAINT fk_asistentes_planillas FOREIGN KEY (idplanilla)
REFERENCES planillas (idplanilla) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE asistentes
  ADD CONSTRAINT fk_asistentes_personas FOREIGN KEY (idpersona)
REFERENCES personas (idpersona) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE parqueaderos (
  idparqueadero SERIAL PRIMARY KEY,
  idhotel       INT               NOT NULL,
  nombre        CHARACTER VARYING NOT NULL UNIQUE
);

ALTER TABLE parqueaderos
  ADD CONSTRAINT fk_parqueaderos_hoteles FOREIGN KEY (idhotel)
REFERENCES hoteles (idhotel) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE parqueaderos_planillas (
  idparqueadero INT NOT NULL,
  idplanilla    INT NOT NULL,
  PRIMARY KEY (idparqueadero, idplanilla)
);

ALTER TABLE parqueaderos_planillas
  ADD CONSTRAINT fk_pp_planillas FOREIGN KEY (idplanilla)
REFERENCES planillas (idplanilla) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE parqueaderos_planillas
  ADD CONSTRAINT fk_pp_parqueadero FOREIGN KEY (idparqueadero)
REFERENCES parqueaderos (idparqueadero) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE facturas (
  idfactura   SERIAL PRIMARY KEY,
  idreserva   INT               NOT NULL,
  ref_factura CHARACTER VARYING NOT NULL UNIQUE,
  valor_pagar INT               NOT NULL,
  estado      CHARACTER VARYING NOT NULL
);

ALTER TABLE facturas
  ADD CONSTRAINT fk_factura_reservas FOREIGN KEY (idreserva)
REFERENCES reservas (idreserva) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE pagos (
  idpago                   SERIAL PRIMARY KEY,
  idfactura                INT               NOT NULL,
  tipo                     CHARACTER VARYING NOT NULL,
  valor_cancelado          INT               NOT NULL,
  numero_tarjeta           CHARACTER VARYING NULL,
  codigo_seguridad_tarjeta CHARACTER VARYING NULL,
  mes_vencimiento          DATE              NULL,
  anio_vencimiento         DATE              NULL
);

ALTER TABLE pagos
  ADD CONSTRAINT fk_pagos_facturas FOREIGN KEY (idfactura)
REFERENCES facturas (idfactura) ON DELETE CASCADE ON UPDATE CASCADE;