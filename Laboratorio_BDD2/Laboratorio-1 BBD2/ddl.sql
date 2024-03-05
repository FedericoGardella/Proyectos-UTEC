
CREATE TABLE marcas (
	marca varchar(20),
	pais_origen varchar(20) NOT NULL
);

CREATE TABLE articulos (
    id_articulo serial,
    monto float NOT NULL,
    stock int NOT NULL,
    tangible boolean NOT NULL,
    nombre varchar(20) NOT NULL
);

CREATE TABLE usuarios (
    nom_usuario varchar(20),
    rol varchar(15) NOT NULL,
    contrasenia varchar(30) NOT NULL,
    nombre varchar(20) NOT NULL,
    apellido varchar(20) NOT NULL
);

CREATE TABLE modelos (
    modelo varchar(20),
    marca varchar(20) NOT NULL
);

CREATE TABLE visitantes (
    id_visit serial,
    fecha date NOT NULL,
    dia_sem varchar(9) NOT NULL,
    pago boolean NOT NULL,
    nom_usuario varchar(20) NOT NULL,
    id_articulo int NOT NULL
);

CREATE TABLE calificaciones (
    id_calif serial,
    fecha date NOT NULL,
    cant_estrellas int NOT NULL,
    selfie BYTEA,
    id_visit int NOT NULL
);

CREATE TABLE mov_art_sti( 
    id_movas serial,
    cantidad int NOT NULL,
    costo float,
    repos boolean NOT NULL,
    fecha date NOT NULL,
    id_articulo int,
    nom_usuario varchar(20) NOT NULL,
);

CREATE TABLE autos (
    matricula varchar(10),
    modelo varchar(20) NOT NULL,
    color varchar(10) NOT NULL,
    descripcion varchar(100) NOT NULL,
    anio int NOT NULL,
    nom_usuario varchar(20) NOT NULL
);

CREATE TABLE bajas (
    id_baja serial,
    fecha date NOT NULL,
    nom_usuario varchar(20) NOT NULL,
    matricula varchar(20) NOT NULL
);

CREATE TABLE fotos (
    matricula varchar(10) NOT NULL,
    foto BYTEA NOT NULL
);