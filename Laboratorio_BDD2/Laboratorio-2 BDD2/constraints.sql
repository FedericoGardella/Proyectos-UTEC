ALTER TABLE marcas
ADD CONSTRAINT pk_marcas PRIMARY KEY (marca); 

ALTER TABLE articulos
ADD CONSTRAINT pk_articulos PRIMARY KEY (id_articulo);

ALTER TABLE usuarios
ADD CONSTRAINT pk_usuarios PRIMARY KEY (nom_usuario);

ALTER TABLE modelos
ADD CONSTRAINT pk_modelos PRIMARY KEY (modelo),
ADD CONSTRAINT fk_modelos_marca FOREIGN KEY (marca) REFERENCES marcas(marca);

ALTER TABLE visitantes
ADD CONSTRAINT pk_visitantes PRIMARY KEY (id_visit),
ADD CONSTRAINT fk_visitantes_usuarios FOREIGN KEY (nom_usuario) REFERENCES usuarios(nom_usuario),
ADD CONSTRAINT fk_visitantes_articulos FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo),
ADD CONSTRAINT ch_diasem CHECK (dia_sem IN ('Lunes','Martes','Miercoles','Jueves','Viernes','Sabado','Domingo'));

ALTER TABLE calificaciones
ADD CONSTRAINT pk_calificaciones PRIMARY KEY (id_calif),
ADD CONSTRAINT fk_calificaciones_visitantes FOREIGN KEY (id_visit) REFERENCES visitantes(id_visit),
ADD CONSTRAINT ch_estrellas CHECK (cant_estrellas IN (1,2,3,4,5));

ALTER TABLE mov_art_sti
ADD CONSTRAINT pk_mov_art_sti PRIMARY KEY (id_movas),
ADD CONSTRAINT fk_mov_art_sti_articulos FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo),
ADD CONSTRAINT fk_mov_art_sti_usuarios FOREIGN KEY (nom_usuario) REFERENCES usuarios(nom_usuario);


ALTER TABLE autos
ADD CONSTRAINT pk_autos PRIMARY KEY (matricula),
ADD CONSTRAINT fk_autos_usuarios FOREIGN KEY (nom_usuario) REFERENCES usuarios(nom_usuario),
ADD CONSTRAINT fk_autos_modelos FOREIGN KEY (modelo) REFERENCES modelos(modelo);

ALTER TABLE bajas
ADD CONSTRAINT pk_bajas PRIMARY KEY (id_baja),
ADD CONSTRAINT fk_bajas_usuarios FOREIGN KEY (nom_usuario) REFERENCES usuarios(nom_usuario),
ADD CONSTRAINT fk_bajas_autos FOREIGN KEY (matricula) REFERENCES autos(matricula);

ALTER TABLE fotos
ADD CONSTRAINT pk_fotos PRIMARY KEY (matricula,foto),
ADD CONSTRAINT fk_fotos_autos FOREIGN KEY (matricula) REFERENCES autos(matricula);