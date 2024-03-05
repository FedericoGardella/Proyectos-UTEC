INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (350, NULL, False, 'Entrada');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (0, 44, True, 'Sticker');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (220, 26, True, 'Gorra');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (500, 22, True, 'Remera');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (50, 15, True, 'Lapicera');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (40, 28, True, 'Llavero');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (30, 35, True, 'Postal');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (150, 18, True, 'Muñeco');
INSERT INTO articulos (monto, stock, tangible, nombre) VALUES (200, 30, True, 'Vaso');



INSERT INTO usuarios VALUES ('amendez', 'entradas', 'amendez123', 'Antonio', 'Mendez');
INSERT INTO usuarios VALUES ('acervantes', 'entradas', 'acervantes123', 'Alicia', 'Cervantes');
INSERT INTO usuarios VALUES ('pviera', 'recuerdos', 'pviera123', 'Pedro', 'Viera');
INSERT INTO usuarios VALUES ('aprats', 'vehiculos', 'aprats123', 'Ana', 'Prats');
INSERT INTO usuarios VALUES ('nolmedo', 'vehiculos', 'nolmedo123', 'Nicolas', 'Olmedo');
INSERT INTO usuarios VALUES ('erobledo', 'director', 'erobledo123', 'Eduardo', 'Robledo');


INSERT INTO marcas VALUES ('AUDI', 'Alemania');
INSERT INTO marcas VALUES ('BMW', 'Alemania');
INSERT INTO marcas VALUES ('CHEVROLET', 'Estados Unidos');
INSERT INTO marcas VALUES ('HONDA', 'Japón');
INSERT INTO marcas VALUES ('RENAULT', 'Francia');


INSERT INTO modelos VALUES ('100', 'AUDI');
INSERT INTO modelos VALUES ('507', 'BMW');
INSERT INTO modelos VALUES ('M1', 'BMW');
INSERT INTO modelos VALUES ('Royal Mail Roadster', 'CHEVROLET');
INSERT INTO modelos VALUES ('Impala', 'CHEVROLET');
INSERT INTO modelos VALUES ('Corvair', 'CHEVROLET');
INSERT INTO modelos VALUES ('S500', 'HONDA');
INSERT INTO modelos VALUES ('Accord', 'HONDA');
INSERT INTO modelos VALUES ('Juvaquatre', 'RENAULT');
INSERT INTO modelos VALUES ('16', 'RENAULT');


INSERT INTO autos VALUES ('HH 12 ABC', '100', 'Gris', 'Automóvil de turismo producido por Audi entre los años 1968 y 1994', 1969, 'aprats');
INSERT INTO autos VALUES ('KFZ AB 123', '507', 'Blanco', 'Automóvil deportivo fabricado por la marca alemana BMW entre 1956 y 1960', 1956, 'aprats');
INSERT INTO autos VALUES ('FA 42-57', 'M1', 'Azul', 'Automóvil deportivo producido por la marca alemana BMW entre 1978 y 1981', 1981, 'nolmedo');
INSERT INTO autos VALUES ('JKK 814', 'Royal Mail Roadster', 'Blanco', 'Automóvil fabricado por Chevrolet entre los años 1914 y 1915', 1915, 'aprats');
INSERT INTO autos VALUES ('HCE 712', 'Impala', 'Rojo', 'Automóvil producido por el fabricante estadounidense Chevrolet desde 1958', 1963, 'nolmedo'); 
INSERT INTO autos VALUES ('GGE 714', 'Impala', 'Negro', 'Automóvil producido por el fabricante estadounidense Chevrolet desde 1958', 1959, 'aprats'); 
INSERT INTO autos VALUES ('WES 639', 'Corvair', 'Celeste', 'Automóvil de la marca Chevrolet fabricado en Estados Unidos de 1959 a 1969', 1959, 'nolmedo');
INSERT INTO autos VALUES ('D20-CT', 'S500', 'Amarillo', 'Fue el segundo automóvil fabricado por Honda, presentado en 1963', 1962, 'aprats'); 
INSERT INTO autos VALUES ('A35-TT', 'Accord', 'Verde', 'Automóvil de turismo producido por Honda desde el año 1976', 1976, 'aprats');
INSERT INTO autos VALUES ('HH 5445', 'Juvaquatre', 'Celeste', 'Se vendió entre los años 1937 y 1960, la producción se redujo durante los años de la guerra', 1940, 'nolmedo');
INSERT INTO autos VALUES ('GP 7123', '16', 'Mostaza', 'Automóvil de turismo producido por el fabricante francés Renault entre los años 1965 y 1980', 1967, 'nolmedo');


INSERT INTO fotos VALUES ('HH 12 ABC', '1001.jpg');
INSERT INTO fotos VALUES ('HH 12 ABC', '1002.jpg');
INSERT INTO fotos VALUES ('KFZ AB 123', '5071.jpg');
INSERT INTO fotos VALUES ('KFZ AB 123', '5072.jpg');
INSERT INTO fotos VALUES ('FA 42-57', 'M11.jpg');
INSERT INTO fotos VALUES ('JKK 814', 'RMR.jpg');
INSERT INTO fotos VALUES ('HCE 712', 'IMP1.jpg');
INSERT INTO fotos VALUES ('HCE 712', 'IMP2.jpg');
INSERT INTO fotos VALUES ('GGE 714', 'IMP3.jpg');
INSERT INTO fotos VALUES ('WES 639', 'COR.jpg');
INSERT INTO fotos VALUES ('D20-CT', 'S5001.jpg');
INSERT INTO fotos VALUES ('A35-TT', 'ACC.jpg');
INSERT INTO fotos VALUES ('HH 5445', 'JUV.jpg');
INSERT INTO fotos VALUES ('GP 7123', '161.jpg');
INSERT INTO fotos VALUES ('GP 7123', '162.jpg');


INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('10/05/2022', 'aprats', 'FA 42-57');
INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('17/12/2022', 'aprats', 'WES 639');
INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('27/02/2023', 'nolmedo', 'A35-TT');


INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('01/02/2022', 'Sabado', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('01/02/2022', 'Sabado', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('01/02/2022', 'Sabado', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('03/06/2022', 'Miercoles', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('03/06/2022', 'Miercoles', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('19/10/2022', 'Lunes', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('30/12/2022', 'Miercoles', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('30/12/2022', 'Miercoles', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/03/2023', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/03/2023', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/03/2023', 'Martes', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/03/2023', 'Martes', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('05/03/2022', 'Sabado', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/04/2022', 'Viernes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('19/04/2022', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('12/05/2022', 'Jueves', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('22/07/2022', 'Viernes', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('09/08/2022', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('09/08/2022', 'Martes', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('21/09/2022', 'Miercoles', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('15/11/2022', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('11/01/2023', 'Miercoles', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('26/02/2023', 'Domingo', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('21/04/2023', 'Viernes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('07/05/2023', 'Domingo', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_articulo) VALUES ('07/05/2023', 'Domingo', FALSE, 'acervantes', 1);



INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('01/02/2020', 4, NULL, 1);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('01/02/2020', 5, 'sf1', 2);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('03/06/2020', 4, 'sf2', 6);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('19/10/2020', 4, 'sf3', 7);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('30/12/2020', 4, 'sf4', 8);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('30/12/2020', 3, NULL, 9);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('15/03/2022', 5, NULL, 10);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('15/03/2022', 5, 'sf5', 11);


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (20, 200, TRUE, '10/01/2022', 2, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (30, 4500, TRUE, '10/01/2022', 3, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (25, 7500, TRUE, '10/01/2022', 4, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (20, 600, TRUE, '10/01/2022', 5, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (30, 750, TRUE, '10/01/2022', 6, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (30, 450, TRUE, '10/01/2022', 7, 'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (20, 1600, TRUE, '10/01/2022', 8,'pviera');


INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (35, 4200, TRUE, '10/01/2022', 9, 'pviera');



INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '01/02/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '01/02/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '01/02/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '03/06/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '03/06/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '19/10/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '30/12/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '30/12/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '05/03/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/04/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '19/04/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '12/05/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '22/07/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '09/08/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '09/08/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '21/09/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/11/2022', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '11/01/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '26/02/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '21/04/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '07/05/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '07/05/2023', 2, 'pviera');



INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (50, 500, TRUE, '10/06/2022', 2, 'pviera');




INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/03/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '15/03/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/03/2023', 2, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/03/2023', 2, 'pviera');


INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (2, FALSE, '01/02/2022', 3, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '05/03/2022', 5, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '15/04/2022', 8, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (2, FALSE, '19/04/2022', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '12/05/2022', 6, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (4, FALSE, '03/06/2022', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '03/06/2022', 8, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '22/07/2022', 4, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (2, FALSE, '09/08/2022', 9, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '21/09/2022', 3, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (3, FALSE, '19/10/2022', 7, 'pviera');



INSERT INTO mov_art_sti (cantidad, costo, repos, fecha, id_articulo, nom_usuario)
VALUES (20, 300, TRUE, '25/10/2022', 7, 'pviera');



INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (2, FALSE, '15/11/2022', 5, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '15/11/2022', 6, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '30/12/2022', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '30/12/2022', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '11/01/2023', 9, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '26/02/2023', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (2, FALSE, '15/03/2023', 7, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/03/2023', 4, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (2, FALSE, '15/03/2023', 9, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario)
VALUES (1, FALSE, '15/03/2023', 3, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '21/04/2023', 4, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (2, FALSE, '07/05/2023', 5, 'pviera');
INSERT INTO mov_art_sti (cantidad, repos, fecha, id_articulo, nom_usuario) 
VALUES (1, FALSE, '07/05/2023', 7, 'pviera');


CREATE TABLE actualizacion (operacion text, fecha timestamp, antiguo_monto float, id_articulo int, nuevo_monto float);

	
CREATE OR REPLACE FUNCTION trigger_actualiza() RETURNS trigger AS $$
	BEGIN
		IF TG_OP = 'UPDATE' AND NEW.monto <> OLD.monto THEN
		RAISE NOTICE 'Operacion UPDATE';
			INSERT INTO actualizacion (operacion, fecha, antiguo_monto,  id_articulo, nuevo_monto)
            		VALUES (TG_OP, now(), OLD.monto::float, NEW.id_articulo::int, NEW.monto::float);
		END IF;
		RETURN null;
	END;
$$ LANGUAGE plpgsql;
		
CREATE TRIGGER trigger_a
AFTER UPDATE
ON articulos
FOR EACH ROW
EXECUTE PROCEDURE trigger_actualiza();


INSERT INTO actualizacion VALUES ('UPDATE', '01/11/2022', 45, 5, 50);


CREATE OR REPLACE FUNCTION recaudacion(anio INTEGER, mes INTEGER)
RETURNS FLOAT AS $$
DECLARE
   resultado FLOAT;
BEGIN
	resultado := (SELECT ((SELECT (SELECT monto FROM articulos
										WHERE id_articulo = 1) * count(*) FROM visitantes v
									WHERE pago = true AND EXTRACT(YEAR FROM fecha) = anio
									AND EXTRACT(MONTH FROM fecha) = mes) +

								(SELECT sum(cantidad*(
										   
										SELECT 
											  CASE 
												WHEN a.id_articulo IN (
												  SELECT id_articulo FROM actualizacion
												  WHERE fecha > (SELECT TO_DATE('1-' || mes || '-' || anio, 'DD-MM-YYYY'))
												)
												THEN (
												  SELECT antiguo_monto FROM actualizacion	
												  WHERE fecha = (
													SELECT MIN(fecha) FROM actualizacion
													WHERE fecha > (SELECT TO_DATE('1-' || mes || '-' || anio, 'DD-MM-YYYY'))
													AND id_articulo = a.id_articulo
												  )
												)
												ELSE a.monto
											  END))
										   
										   
										   
										   
										    FROM mov_art_sti m
								JOIN articulos a ON a.id_articulo = m.id_articulo
								WHERE repos = false AND m.id_articulo != 2 AND EXTRACT(YEAR FROM fecha) = anio
								AND EXTRACT(MONTH FROM fecha) = mes)) AS total_mes);


   RETURN resultado;
END;
$$ LANGUAGE plpgsql;
