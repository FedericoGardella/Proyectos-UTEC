
--Creacion de grupos
CREATE GROUP entradas;
CREATE GROUP recuerdos;
CREATE GROUP vehiculos;
CREATE GROUP director;

--Creacion de usuarios y adjudicacion roles
CREATE USER erobledo with password 'erobledo123' in group director;
CREATE USER amendez with password 'amendez123' in group entradas;
CREATE USER acervantes with password 'acervantes123' in group entradas;
CREATE USER pviera with password 'pviera123' in group recuerdos;
CREATE USER aprats with password 'aprats123' in group vehiculos;
CREATE USER nolmedo with password 'nolmedo123' in group vehiculos;

--Otorgamiento de permisos a los grupos sobre las distintas tablas
GRANT SELECT, INSERT ON  visitantes TO entradas;
GRANT SELECT, INSERT, UPDATE ON mov_art_sti, articulos TO recuerdos;
GRANT SELECT, UPDATE, INSERT ON autos, bajas, marcas, modelos TO vehiculos;
--Total acceso a al director
GRANT ALL ON ALL TABLES IN SCHEMA public TO director;
