PGDMP         ;            
    z            laboratorio final    14.5    14.5 Q    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    58063    laboratorio final    DATABASE     q   CREATE DATABASE "laboratorio final" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Uruguay.1252';
 #   DROP DATABASE "laboratorio final";
                postgres    false            �            1259    58064    deposito    TABLE     h   CREATE TABLE public.deposito (
    letra character varying(15) NOT NULL,
    numero integer NOT NULL
);
    DROP TABLE public.deposito;
       public         heap    postgres    false            �            1259    58067    emaile    TABLE     �   CREATE TABLE public.emaile (
    email character varying(128) NOT NULL,
    razonsocial character varying(50) NOT NULL,
    direccion character varying(50) NOT NULL,
    numero integer NOT NULL,
    telefono character varying(9) NOT NULL
);
    DROP TABLE public.emaile;
       public         heap    postgres    false            �            1259    58070    emailef    TABLE     w   CREATE TABLE public.emailef (
    email character varying(128) NOT NULL,
    usuario character varying(30) NOT NULL
);
    DROP TABLE public.emailef;
       public         heap    postgres    false            �            1259    58073    emailf    TABLE     �   CREATE TABLE public.emailf (
    email character varying(128) NOT NULL,
    nombre character varying(15) NOT NULL,
    apellido character varying(15) NOT NULL,
    calle character varying(50) NOT NULL,
    numero integer NOT NULL
);
    DROP TABLE public.emailf;
       public         heap    postgres    false            �            1259    58076    emp_contactos    TABLE     |   CREATE TABLE public.emp_contactos (
    rut character varying(12) NOT NULL,
    contactos character varying(64) NOT NULL
);
 !   DROP TABLE public.emp_contactos;
       public         heap    postgres    false            �            1259    58079    sec_emplfirma    SEQUENCE     |   CREATE SEQUENCE public.sec_emplfirma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;
 $   DROP SEQUENCE public.sec_emplfirma;
       public          postgres    false            �            1259    58080 
   empl_firma    TABLE     �   CREATE TABLE public.empl_firma (
    id integer DEFAULT nextval('public.sec_emplfirma'::regclass) NOT NULL,
    email character varying(128) NOT NULL
);
    DROP TABLE public.empl_firma;
       public         heap    postgres    false    214            �            1259    58084    empresas    TABLE     t   CREATE TABLE public.empresas (
    rut character varying(12) NOT NULL,
    email character varying(128) NOT NULL
);
    DROP TABLE public.empresas;
       public         heap    postgres    false            �            1259    58087    equipos    TABLE     �   CREATE TABLE public.equipos (
    nroserie character varying(32) NOT NULL,
    modelo character varying(64) NOT NULL,
    descripcion character varying(256) NOT NULL,
    rut character varying(12) NOT NULL
);
    DROP TABLE public.equipos;
       public         heap    postgres    false            �            1259    58090    funcionarios    TABLE     v   CREATE TABLE public.funcionarios (
    ci character varying(8) NOT NULL,
    email character varying(128) NOT NULL
);
     DROP TABLE public.funcionarios;
       public         heap    postgres    false            �            1259    58093 	   sec_idkit    SEQUENCE     x   CREATE SEQUENCE public.sec_idkit
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;
     DROP SEQUENCE public.sec_idkit;
       public          postgres    false            �            1259    58094    kits    TABLE     �   CREATE TABLE public.kits (
    id integer DEFAULT nextval('public.sec_idkit'::regclass) NOT NULL,
    rut character varying(12) NOT NULL
);
    DROP TABLE public.kits;
       public         heap    postgres    false    219            �            1259    58098    modelo    TABLE     t   CREATE TABLE public.modelo (
    modelo character varying(64) NOT NULL,
    marca character varying(64) NOT NULL
);
    DROP TABLE public.modelo;
       public         heap    postgres    false            �            1259    58101    orden_envio    TABLE     C  CREATE TABLE public.orden_envio (
    nroremito character varying(15) NOT NULL,
    empreparto character varying(64) NOT NULL,
    fecha date NOT NULL,
    ci character varying(8) NOT NULL,
    nroserie character varying(32) NOT NULL,
    idkit integer NOT NULL,
    idempleado integer NOT NULL,
    fechasolicitud date
);
    DROP TABLE public.orden_envio;
       public         heap    postgres    false            �            1259    58104    precios    TABLE     �   CREATE TABLE public.precios (
    id integer NOT NULL,
    fecha date NOT NULL,
    montodia integer NOT NULL,
    mantenimiento boolean NOT NULL
);
    DROP TABLE public.precios;
       public         heap    postgres    false            �            1259    58107    sec_idadministrae    SEQUENCE     �   CREATE SEQUENCE public.sec_idadministrae
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;
 (   DROP SEQUENCE public.sec_idadministrae;
       public          postgres    false            �            1259    58108    r_administrae    TABLE     �   CREATE TABLE public.r_administrae (
    id integer DEFAULT nextval('public.sec_idadministrae'::regclass) NOT NULL,
    nroserie character varying(12) NOT NULL,
    idempleado integer NOT NULL,
    tipo character varying(64) NOT NULL
);
 !   DROP TABLE public.r_administrae;
       public         heap    postgres    false    224            �            1259    58112    sec_idadministrak    SEQUENCE     �   CREATE SEQUENCE public.sec_idadministrak
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;
 (   DROP SEQUENCE public.sec_idadministrak;
       public          postgres    false            �            1259    58113    r_administrak    TABLE     �   CREATE TABLE public.r_administrak (
    idempleado integer NOT NULL,
    idkit integer NOT NULL,
    tipo character varying(32) NOT NULL,
    id integer DEFAULT nextval('public.sec_idadministrak'::regclass) NOT NULL
);
 !   DROP TABLE public.r_administrak;
       public         heap    postgres    false    226            �            1259    58117    r_almacenae    TABLE     �   CREATE TABLE public.r_almacenae (
    nroserie character varying(12) NOT NULL,
    letra character varying(15) NOT NULL,
    numero integer NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL
);
    DROP TABLE public.r_almacenae;
       public         heap    postgres    false            �            1259    58120    r_almacenak    TABLE     �   CREATE TABLE public.r_almacenak (
    id integer NOT NULL,
    letra character varying(15) NOT NULL,
    numero integer NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL
);
    DROP TABLE public.r_almacenak;
       public         heap    postgres    false            �            1259    58123    r_costoe    TABLE     g   CREATE TABLE public.r_costoe (
    id integer NOT NULL,
    nroserie character varying(12) NOT NULL
);
    DROP TABLE public.r_costoe;
       public         heap    postgres    false            �            1259    58126    r_costok    TABLE     V   CREATE TABLE public.r_costok (
    id integer NOT NULL,
    idkit integer NOT NULL
);
    DROP TABLE public.r_costok;
       public         heap    postgres    false            �            1259    58129    r_devuelven    TABLE     �   CREATE TABLE public.r_devuelven (
    ci character varying(8) NOT NULL,
    nroserie character varying(12) NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL,
    mantenimiento boolean NOT NULL
);
    DROP TABLE public.r_devuelven;
       public         heap    postgres    false            �            1259    58132    sec_idprecio    SEQUENCE     {   CREATE SEQUENCE public.sec_idprecio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;
 #   DROP SEQUENCE public.sec_idprecio;
       public          postgres    false    223            �           0    0    sec_idprecio    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.sec_idprecio OWNED BY public.precios.id;
          public          postgres    false    233            �            1259    58133    usuarios    TABLE       CREATE TABLE public.usuarios (
    usuario character varying(30) NOT NULL,
    nombre character varying(15) NOT NULL,
    apellido character varying(15) NOT NULL,
    nacimiento date NOT NULL,
    genero character varying(15),
    "contraseña" character varying(30) NOT NULL
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false            �            1259    58315 	   vista_kpi    VIEW     �  CREATE VIEW public.vista_kpi AS
 SELECT avg((orden_envio.fecha - orden_envio.fechasolicitud)) AS tiempo_ciclo_interno,
    ((( SELECT count(*) AS count
           FROM public.orden_envio orden_envio_1
          WHERE ((orden_envio_1.fecha - orden_envio_1.fechasolicitud) < 2)) * 100) / ( SELECT count(*) AS count
           FROM public.orden_envio orden_envio_1)) AS porcentaje_entregadas_a_tiempo
   FROM public.orden_envio;
    DROP VIEW public.vista_kpi;
       public          postgres    false    222    222            �           2604    58140 
   precios id    DEFAULT     f   ALTER TABLE ONLY public.precios ALTER COLUMN id SET DEFAULT nextval('public.sec_idprecio'::regclass);
 9   ALTER TABLE public.precios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    223            �           2606    58142    r_costoe costoe_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT costoe_pkey PRIMARY KEY (id, nroserie);
 >   ALTER TABLE ONLY public.r_costoe DROP CONSTRAINT costoe_pkey;
       public            postgres    false    230    230            �           2606    58144    r_costok costok_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT costok_pkey PRIMARY KEY (idkit, id);
 >   ALTER TABLE ONLY public.r_costok DROP CONSTRAINT costok_pkey;
       public            postgres    false    231    231            �           2606    58146    emaile emaile_pk 
   CONSTRAINT     a   ALTER TABLE ONLY public.emaile
    ADD CONSTRAINT emaile_pk PRIMARY KEY (email) INCLUDE (email);
 :   ALTER TABLE ONLY public.emaile DROP CONSTRAINT emaile_pk;
       public            postgres    false    210            �           2606    58148    emailef emailef_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.emailef
    ADD CONSTRAINT emailef_pkey PRIMARY KEY (email);
 >   ALTER TABLE ONLY public.emailef DROP CONSTRAINT emailef_pkey;
       public            postgres    false    211            �           2606    58150    funcionarios emailf_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT emailf_pkey PRIMARY KEY (ci);
 B   ALTER TABLE ONLY public.funcionarios DROP CONSTRAINT emailf_pkey;
       public            postgres    false    218            �           2606    58152     emp_contactos emp_contactos_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.emp_contactos
    ADD CONSTRAINT emp_contactos_pkey PRIMARY KEY (rut, contactos);
 J   ALTER TABLE ONLY public.emp_contactos DROP CONSTRAINT emp_contactos_pkey;
       public            postgres    false    213    213            �           2606    58154    usuarios empl_firma_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT empl_firma_pkey PRIMARY KEY (usuario) INCLUDE (usuario);
 B   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT empl_firma_pkey;
       public            postgres    false    234            �           2606    58156    empl_firma empl_firma_pkey1 
   CONSTRAINT     Y   ALTER TABLE ONLY public.empl_firma
    ADD CONSTRAINT empl_firma_pkey1 PRIMARY KEY (id);
 E   ALTER TABLE ONLY public.empl_firma DROP CONSTRAINT empl_firma_pkey1;
       public            postgres    false    215            �           2606    58158    empresas empresas_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (rut);
 @   ALTER TABLE ONLY public.empresas DROP CONSTRAINT empresas_pkey;
       public            postgres    false    216            �           2606    58160    equipos equipos_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT equipos_pkey PRIMARY KEY (nroserie);
 >   ALTER TABLE ONLY public.equipos DROP CONSTRAINT equipos_pkey;
       public            postgres    false    217            �           2606    58162    emailf funcionarios_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.emailf
    ADD CONSTRAINT funcionarios_pkey PRIMARY KEY (email);
 B   ALTER TABLE ONLY public.emailf DROP CONSTRAINT funcionarios_pkey;
       public            postgres    false    212            �           2606    58164    r_administrak id 
   CONSTRAINT     N   ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT id PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.r_administrak DROP CONSTRAINT id;
       public            postgres    false    227            �           2606    58166    kits kits_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.kits
    ADD CONSTRAINT kits_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.kits DROP CONSTRAINT kits_pkey;
       public            postgres    false    220            �           2606    58168    modelo modelo_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (modelo);
 <   ALTER TABLE ONLY public.modelo DROP CONSTRAINT modelo_pkey;
       public            postgres    false    221            �           2606    58170    orden_envio orden_envio_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT orden_envio_pkey PRIMARY KEY (nroremito);
 F   ALTER TABLE ONLY public.orden_envio DROP CONSTRAINT orden_envio_pkey;
       public            postgres    false    222            �           2606    58172    r_devuelven pk 
   CONSTRAINT     �   ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT pk PRIMARY KEY (ci, nroserie, fecha, hora) INCLUDE (nroserie, ci, fecha);
 8   ALTER TABLE ONLY public.r_devuelven DROP CONSTRAINT pk;
       public            postgres    false    232    232    232    232            �           2606    58174    deposito pk_ubicacion 
   CONSTRAINT     v   ALTER TABLE ONLY public.deposito
    ADD CONSTRAINT pk_ubicacion PRIMARY KEY (letra, numero) INCLUDE (letra, numero);
 ?   ALTER TABLE ONLY public.deposito DROP CONSTRAINT pk_ubicacion;
       public            postgres    false    209    209            �           2606    58176    precios precios_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.precios
    ADD CONSTRAINT precios_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.precios DROP CONSTRAINT precios_pkey;
       public            postgres    false    223            �           2606    58178     r_administrae r_administrae_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT r_administrae_pkey PRIMARY KEY (id, nroserie, idempleado) INCLUDE (id, nroserie, idempleado);
 J   ALTER TABLE ONLY public.r_administrae DROP CONSTRAINT r_administrae_pkey;
       public            postgres    false    225    225    225            �           2606    58180    r_almacenae r_almacenae_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT r_almacenae_pkey PRIMARY KEY (nroserie, letra, numero, fecha, hora) INCLUDE (nroserie, letra, numero, fecha, hora);
 F   ALTER TABLE ONLY public.r_almacenae DROP CONSTRAINT r_almacenae_pkey;
       public            postgres    false    228    228    228    228    228            �           2606    58182    r_almacenak r_almacenak_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT r_almacenak_pkey PRIMARY KEY (id, letra, numero, fecha, hora) INCLUDE (id, letra, numero, fecha, hora);
 F   ALTER TABLE ONLY public.r_almacenak DROP CONSTRAINT r_almacenak_pkey;
       public            postgres    false    229    229    229    229    229            �           2606    58184    deposito ubicacion 
   CONSTRAINT     n   ALTER TABLE ONLY public.deposito
    ADD CONSTRAINT ubicacion UNIQUE (numero, letra) INCLUDE (letra, numero);
 <   ALTER TABLE ONLY public.deposito DROP CONSTRAINT ubicacion;
       public            postgres    false    209    209            �           2606    58185    orden_envio ci    FK CONSTRAINT     y   ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT ci FOREIGN KEY (ci) REFERENCES public.funcionarios(ci) NOT VALID;
 8   ALTER TABLE ONLY public.orden_envio DROP CONSTRAINT ci;
       public          postgres    false    222    3276    218            �           2606    58190    r_devuelven ci    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT ci FOREIGN KEY (ci) REFERENCES public.funcionarios(ci) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 8   ALTER TABLE ONLY public.r_devuelven DROP CONSTRAINT ci;
       public          postgres    false    232    3276    218            �           2606    58195    empl_firma email    FK CONSTRAINT     �   ALTER TABLE ONLY public.empl_firma
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emailef(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 :   ALTER TABLE ONLY public.empl_firma DROP CONSTRAINT email;
       public          postgres    false    211    3264    215            �           2606    58200    empresas email    FK CONSTRAINT     �   ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emaile(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 8   ALTER TABLE ONLY public.empresas DROP CONSTRAINT email;
       public          postgres    false    3262    210    216            �           2606    58205    funcionarios email    FK CONSTRAINT     �   ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emailf(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 <   ALTER TABLE ONLY public.funcionarios DROP CONSTRAINT email;
       public          postgres    false    218    212    3266            �           2606    58210    r_costoe id    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT id FOREIGN KEY (id) REFERENCES public.precios(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 5   ALTER TABLE ONLY public.r_costoe DROP CONSTRAINT id;
       public          postgres    false    3284    223    230            �           2606    58215    orden_envio idempleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) NOT VALID;
 @   ALTER TABLE ONLY public.orden_envio DROP CONSTRAINT idempleado;
       public          postgres    false    3270    222    215            �           2606    58220    r_administrae idempleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 B   ALTER TABLE ONLY public.r_administrae DROP CONSTRAINT idempleado;
       public          postgres    false    225    3270    215            �           2606    58225    r_administrak idempleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 B   ALTER TABLE ONLY public.r_administrak DROP CONSTRAINT idempleado;
       public          postgres    false    3270    227    215            �           2606    58230    orden_envio idkit    FK CONSTRAINT     w   ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) NOT VALID;
 ;   ALTER TABLE ONLY public.orden_envio DROP CONSTRAINT idkit;
       public          postgres    false    222    220    3278            �           2606    58235    r_administrak idkit    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 =   ALTER TABLE ONLY public.r_administrak DROP CONSTRAINT idkit;
       public          postgres    false    220    3278    227            �           2606    58240    r_almacenak idkit    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT idkit FOREIGN KEY (id) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 ;   ALTER TABLE ONLY public.r_almacenak DROP CONSTRAINT idkit;
       public          postgres    false    229    220    3278            �           2606    58245    r_costok idkit    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 8   ALTER TABLE ONLY public.r_costok DROP CONSTRAINT idkit;
       public          postgres    false    220    3278    231            �           2606    58250    r_costok idprecio    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT idprecio FOREIGN KEY (id) REFERENCES public.precios(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 ;   ALTER TABLE ONLY public.r_costok DROP CONSTRAINT idprecio;
       public          postgres    false    3284    231    223            �           2606    58255    equipos modelo    FK CONSTRAINT     �   ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT modelo FOREIGN KEY (modelo) REFERENCES public.modelo(modelo) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 8   ALTER TABLE ONLY public.equipos DROP CONSTRAINT modelo;
       public          postgres    false    3280    217    221            �           2606    58260    orden_envio nroserie    FK CONSTRAINT     |   ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie);
 >   ALTER TABLE ONLY public.orden_envio DROP CONSTRAINT nroserie;
       public          postgres    false    222    3274    217            �           2606    58265    r_administrae nroserie    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 @   ALTER TABLE ONLY public.r_administrae DROP CONSTRAINT nroserie;
       public          postgres    false    217    225    3274            �           2606    58270    r_almacenae nroserie    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 >   ALTER TABLE ONLY public.r_almacenae DROP CONSTRAINT nroserie;
       public          postgres    false    3274    228    217            �           2606    58275    r_costoe nroserie    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 ;   ALTER TABLE ONLY public.r_costoe DROP CONSTRAINT nroserie;
       public          postgres    false    3274    230    217            �           2606    58280    r_devuelven nroserie    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 >   ALTER TABLE ONLY public.r_devuelven DROP CONSTRAINT nroserie;
       public          postgres    false    3274    232    217            �           2606    58285    emp_contactos rut    FK CONSTRAINT     �   ALTER TABLE ONLY public.emp_contactos
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 ;   ALTER TABLE ONLY public.emp_contactos DROP CONSTRAINT rut;
       public          postgres    false    3272    213    216            �           2606    58290    equipos rut    FK CONSTRAINT     �   ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 5   ALTER TABLE ONLY public.equipos DROP CONSTRAINT rut;
       public          postgres    false    3272    217    216            �           2606    58295    kits rut    FK CONSTRAINT     �   ALTER TABLE ONLY public.kits
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 2   ALTER TABLE ONLY public.kits DROP CONSTRAINT rut;
       public          postgres    false    3272    220    216            �           2606    58300    r_almacenae ubicacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT ubicacion FOREIGN KEY (letra, numero) REFERENCES public.deposito(letra, numero) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 ?   ALTER TABLE ONLY public.r_almacenae DROP CONSTRAINT ubicacion;
       public          postgres    false    209    209    3258    228    228            �           2606    58305    r_almacenak ubicacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT ubicacion FOREIGN KEY (letra, numero) REFERENCES public.deposito(letra, numero) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 ?   ALTER TABLE ONLY public.r_almacenak DROP CONSTRAINT ubicacion;
       public          postgres    false    229    209    209    3258    229            �           2606    58310    emailef usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.emailef
    ADD CONSTRAINT usuario FOREIGN KEY (usuario) REFERENCES public.usuarios(usuario) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 9   ALTER TABLE ONLY public.emailef DROP CONSTRAINT usuario;
       public          postgres    false    234    211    3300           