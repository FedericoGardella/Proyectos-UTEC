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


INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 350, 'Entrada');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 0, 'Sticker');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 220, 'Gorra');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 500, 'Remera');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 45, 'Lapicera');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 40, 'Llavero');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 30, 'Postal');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 150, 'Muñeco');
INSERT INTO precios (fecha, monto, nombre) VALUES ('01/01/2020', 200, 'Vaso');


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


INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('10/05/2021', 'aprats', 'FA 42-57');
INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('17/02/2022', 'aprats', 'WES 639');
INSERT INTO bajas (fecha, nom_usuario, matricula) VALUES ('27/10/2022', 'nolmedo', 'A35-TT');

INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('01/02/2020', 'Sabado', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('01/02/2020', 'Sabado', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('01/02/2020', 'Sabado', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('03/06/2020', 'Miercoles', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('03/06/2020', 'Miercoles', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('19/10/2020', 'Lunes', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('30/12/2020', 'Miercoles', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('30/12/2020', 'Miercoles', TRUE, 'amendez', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('15/03/2022', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('15/03/2022', 'Martes', TRUE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('15/03/2022', 'Martes', FALSE, 'acervantes', 1);
INSERT INTO visitantes (fecha, dia_sem, pago, nom_usuario, id_precio) VALUES ('15/03/2022', 'Martes', FALSE, 'acervantes', 1);


INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('01/02/2020', 4, NULL, 1);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('01/02/2020', 5, 'sf1', 2);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('03/06/2020', 4, 'sf2', 6);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('19/10/2020', 4, 'sf3', 7);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('30/12/2020', 4, 'sf4', 8);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('30/12/2020', 3, NULL, 9);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('15/03/2022', 5, NULL, 10);
INSERT INTO calificaciones (fecha, cant_estrellas, selfie, id_visit) VALUES ('15/03/2022', 5, 'sf5', 11);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Sticker', 20, TRUE, 2,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (20, 1, '01/01/2020', 'pviera', 1);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Gorra', 30, TRUE, 3,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (30, 140, '01/01/2020', 'pviera', 2);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Remera', 25, TRUE, 4,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (25, 350, '01/01/2020', 'pviera', 3);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Lapicera', 20, TRUE, 5,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (20, 20, '01/01/2020', 'pviera', 4);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Llavero', 30, TRUE, 6,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (30, 15, '01/01/2020', 'pviera', 5);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Postal', 30, TRUE, 7,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (30, 10, '01/01/2020', 'pviera', 6);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Muñeco', 20, TRUE, 8,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (20, 80, '01/01/2020', 'pviera', 7);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Vaso', 35, TRUE, 9,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (35, 100, '01/01/2020', 'pviera', 8);



INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 19, FALSE, 2,'pviera', 1);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 17, FALSE, 2,'pviera', 2);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 16, FALSE, 2,'pviera', 3);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 15, FALSE, 2,'pviera', 4);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 14, FALSE, 2,'pviera', 5);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 13, FALSE, 2,'pviera', 6);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 12, FALSE, 2,'pviera', 7);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 11, FALSE, 2,'pviera', 8);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Sticker', 41, TRUE, 2,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (30, 1, '01/02/2022', 'pviera', 17);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 40, FALSE, 2,'pviera', 9);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 39, FALSE, 2,'pviera', 10);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 38, FALSE, 2,'pviera', 11);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Sticker', 37, FALSE, 2,'pviera', 12);




INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Gorra', 29, FALSE, 3,'pviera', 2);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Gorra', 28, FALSE, 3,'pviera', 2);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 29, FALSE, 7,'pviera', 4);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 28, FALSE, 7,'pviera', 4);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 27, FALSE, 7,'pviera', 4);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 26, FALSE, 7,'pviera', 4);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Muñeco', 19, FALSE, 8,'pviera', 5);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 25, FALSE, 7,'pviera', 6);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 24, FALSE, 7,'pviera', 6);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 23, FALSE, 7,'pviera', 6);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit)
VALUES ('Postal', 33, TRUE, 7,'pviera', NULL);

INSERT INTO reposiciones (cantidad, costo, fecha, nom_usuario, id_movas) VALUES (10, 10, '27/10/2020', 'pviera', 32);


INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 32, FALSE, 7,'pviera', 7);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 31, FALSE, 7,'pviera', 8);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 30, FALSE, 7,'pviera', 9);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Postal', 29, FALSE, 7,'pviera', 9);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Remera', 24, FALSE, 4,'pviera', 11);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Vaso', 34, FALSE, 9,'pviera', 11);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Vaso', 33, FALSE, 9,'pviera', 11);
INSERT INTO mov_art_sti (nombre, stock, repos, id_precio, nom_usuario, id_visit) VALUES ('Gorra', 27, FALSE, 3,'pviera', 12);