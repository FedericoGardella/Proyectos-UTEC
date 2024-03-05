PGDMP                         {            LabPA    14.6    15.0 *    (           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            )           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            *           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            +           1262    18527    LabPA    DATABASE     |   CREATE DATABASE "LabPA" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Uruguay.1252';
    DROP DATABASE "LabPA";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            ,           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    39837    actividades    TABLE       CREATE TABLE public.actividades (
    nombre character varying(255) NOT NULL,
    ciudad character varying(255),
    costo real,
    descr character varying(255),
    duracion integer,
    fecha date,
    nombre_dep character varying(255),
    nombreprov character varying(255)
);
    DROP TABLE public.actividades;
       public         heap    postgres    false    4            �            1259    39844    at_paq    TABLE     �   CREATE TABLE public.at_paq (
    at_id character varying(255) NOT NULL,
    paq_id character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL
);
    DROP TABLE public.at_paq;
       public         heap    postgres    false    4            �            1259    39851    departamentos    TABLE     �   CREATE TABLE public.departamentos (
    nombre character varying(255) NOT NULL,
    descr character varying(255),
    url character varying(255)
);
 !   DROP TABLE public.departamentos;
       public         heap    postgres    false    4            �            1259    39859    inscripciones    TABLE     �   CREATE TABLE public.inscripciones (
    id bigint NOT NULL,
    cant integer,
    fecha date,
    nombresal character varying(255),
    nombretur character varying(255)
);
 !   DROP TABLE public.inscripciones;
       public         heap    postgres    false    4            �            1259    39858    inscripciones_id_seq    SEQUENCE     }   CREATE SEQUENCE public.inscripciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.inscripciones_id_seq;
       public          postgres    false    214    4            -           0    0    inscripciones_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.inscripciones_id_seq OWNED BY public.inscripciones.id;
          public          postgres    false    213            �            1259    39867    paquetes    TABLE     �   CREATE TABLE public.paquetes (
    nombre character varying(255) NOT NULL,
    descr character varying(255),
    descuento real NOT NULL,
    fechaalta date,
    periodovaliez integer
);
    DROP TABLE public.paquetes;
       public         heap    postgres    false    4            �            1259    39874    salidaturistica    TABLE       CREATE TABLE public.salidaturistica (
    nombre character varying(255) NOT NULL,
    cantmaxturistas integer,
    fechaalta date,
    fechasalida date,
    horasalida time without time zone,
    lugarsalida character varying(255),
    nombreact character varying(255)
);
 #   DROP TABLE public.salidaturistica;
       public         heap    postgres    false    4            �            1259    39881    usuarios    TABLE     c  CREATE TABLE public.usuarios (
    tipo_usuario character varying(31) NOT NULL,
    nick character varying(255) NOT NULL,
    apellido character varying(255),
    email character varying(255),
    fechanac date,
    name character varying(255),
    nacionalidad character varying(255),
    descr character varying(255),
    link character varying(255)
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false    4            �            1259    34836    usuarios_inscripciones    TABLE     �   CREATE TABLE public.usuarios_inscripciones (
    turista_nick character varying(255) NOT NULL,
    inscripciones_id bigint NOT NULL
);
 *   DROP TABLE public.usuarios_inscripciones;
       public         heap    postgres    false    4            x           2604    39862    inscripciones id    DEFAULT     t   ALTER TABLE ONLY public.inscripciones ALTER COLUMN id SET DEFAULT nextval('public.inscripciones_id_seq'::regclass);
 ?   ALTER TABLE public.inscripciones ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214                      0    39837    actividades 
   TABLE DATA           l   COPY public.actividades (nombre, ciudad, costo, descr, duracion, fecha, nombre_dep, nombreprov) FROM stdin;
    public          postgres    false    210   J4                 0    39844    at_paq 
   TABLE DATA           7   COPY public.at_paq (at_id, paq_id, nombre) FROM stdin;
    public          postgres    false    211   �4                  0    39851    departamentos 
   TABLE DATA           ;   COPY public.departamentos (nombre, descr, url) FROM stdin;
    public          postgres    false    212   �4       "          0    39859    inscripciones 
   TABLE DATA           N   COPY public.inscripciones (id, cant, fecha, nombresal, nombretur) FROM stdin;
    public          postgres    false    214   {6       #          0    39867    paquetes 
   TABLE DATA           V   COPY public.paquetes (nombre, descr, descuento, fechaalta, periodovaliez) FROM stdin;
    public          postgres    false    215   �6       $          0    39874    salidaturistica 
   TABLE DATA           ~   COPY public.salidaturistica (nombre, cantmaxturistas, fechaalta, fechasalida, horasalida, lugarsalida, nombreact) FROM stdin;
    public          postgres    false    216   �6       %          0    39881    usuarios 
   TABLE DATA           r   COPY public.usuarios (tipo_usuario, nick, apellido, email, fechanac, name, nacionalidad, descr, link) FROM stdin;
    public          postgres    false    217   47                 0    34836    usuarios_inscripciones 
   TABLE DATA           P   COPY public.usuarios_inscripciones (turista_nick, inscripciones_id) FROM stdin;
    public          postgres    false    209   �7       .           0    0    inscripciones_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.inscripciones_id_seq', 1, false);
          public          postgres    false    213            ~           2606    39843    actividades actividades_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT actividades_pkey PRIMARY KEY (nombre);
 F   ALTER TABLE ONLY public.actividades DROP CONSTRAINT actividades_pkey;
       public            postgres    false    210            �           2606    39850    at_paq at_paq_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT at_paq_pkey PRIMARY KEY (at_id, nombre);
 <   ALTER TABLE ONLY public.at_paq DROP CONSTRAINT at_paq_pkey;
       public            postgres    false    211    211            �           2606    39857     departamentos departamentos_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.departamentos
    ADD CONSTRAINT departamentos_pkey PRIMARY KEY (nombre);
 J   ALTER TABLE ONLY public.departamentos DROP CONSTRAINT departamentos_pkey;
       public            postgres    false    212            �           2606    39866     inscripciones inscripciones_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT inscripciones_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.inscripciones DROP CONSTRAINT inscripciones_pkey;
       public            postgres    false    214            �           2606    39873    paquetes paquetes_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.paquetes
    ADD CONSTRAINT paquetes_pkey PRIMARY KEY (nombre);
 @   ALTER TABLE ONLY public.paquetes DROP CONSTRAINT paquetes_pkey;
       public            postgres    false    215            �           2606    39880 $   salidaturistica salidaturistica_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.salidaturistica
    ADD CONSTRAINT salidaturistica_pkey PRIMARY KEY (nombre);
 N   ALTER TABLE ONLY public.salidaturistica DROP CONSTRAINT salidaturistica_pkey;
       public            postgres    false    216            z           2606    34842 3   usuarios_inscripciones uk_dy43nav37qb4xjbipcymbodu9 
   CONSTRAINT     z   ALTER TABLE ONLY public.usuarios_inscripciones
    ADD CONSTRAINT uk_dy43nav37qb4xjbipcymbodu9 UNIQUE (inscripciones_id);
 ]   ALTER TABLE ONLY public.usuarios_inscripciones DROP CONSTRAINT uk_dy43nav37qb4xjbipcymbodu9;
       public            postgres    false    209            |           2606    34840 2   usuarios_inscripciones usuarios_inscripciones_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.usuarios_inscripciones
    ADD CONSTRAINT usuarios_inscripciones_pkey PRIMARY KEY (turista_nick, inscripciones_id);
 \   ALTER TABLE ONLY public.usuarios_inscripciones DROP CONSTRAINT usuarios_inscripciones_pkey;
       public            postgres    false    209    209            �           2606    39887    usuarios usuarios_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (nick);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    217            �           2606    39888 '   actividades fk2vmk7o17rcicaa9jlnrixupph    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fk2vmk7o17rcicaa9jlnrixupph FOREIGN KEY (nombre_dep) REFERENCES public.departamentos(nombre);
 Q   ALTER TABLE ONLY public.actividades DROP CONSTRAINT fk2vmk7o17rcicaa9jlnrixupph;
       public          postgres    false    210    3202    212            �           2606    39913 )   inscripciones fkcjxf6o171q4613n737i5spqwj    FK CONSTRAINT     �   ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT fkcjxf6o171q4613n737i5spqwj FOREIGN KEY (nombretur) REFERENCES public.usuarios(nick);
 S   ALTER TABLE ONLY public.inscripciones DROP CONSTRAINT fkcjxf6o171q4613n737i5spqwj;
       public          postgres    false    3210    217    214            �           2606    39908 )   inscripciones fkgqghkti8yfmvdmsi12e82cpba    FK CONSTRAINT     �   ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT fkgqghkti8yfmvdmsi12e82cpba FOREIGN KEY (nombresal) REFERENCES public.salidaturistica(nombre);
 S   ALTER TABLE ONLY public.inscripciones DROP CONSTRAINT fkgqghkti8yfmvdmsi12e82cpba;
       public          postgres    false    3208    216    214            �           2606    39893 '   actividades fkj9d4lq2syg86qx43c50rbivcx    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fkj9d4lq2syg86qx43c50rbivcx FOREIGN KEY (nombreprov) REFERENCES public.usuarios(nick);
 Q   ALTER TABLE ONLY public.actividades DROP CONSTRAINT fkj9d4lq2syg86qx43c50rbivcx;
       public          postgres    false    3210    217    210            �           2606    39918 +   salidaturistica fkm1xqcvqtwhdravu30kpbp5iev    FK CONSTRAINT     �   ALTER TABLE ONLY public.salidaturistica
    ADD CONSTRAINT fkm1xqcvqtwhdravu30kpbp5iev FOREIGN KEY (nombreact) REFERENCES public.actividades(nombre);
 U   ALTER TABLE ONLY public.salidaturistica DROP CONSTRAINT fkm1xqcvqtwhdravu30kpbp5iev;
       public          postgres    false    3198    216    210            �           2606    39898 "   at_paq fknptlan7v1qp8fxuycuxn4mcqo    FK CONSTRAINT     �   ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT fknptlan7v1qp8fxuycuxn4mcqo FOREIGN KEY (paq_id) REFERENCES public.paquetes(nombre);
 L   ALTER TABLE ONLY public.at_paq DROP CONSTRAINT fknptlan7v1qp8fxuycuxn4mcqo;
       public          postgres    false    211    215    3206            �           2606    39903 "   at_paq fkpm0shucsh053ohtb927bfe47b    FK CONSTRAINT     �   ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT fkpm0shucsh053ohtb927bfe47b FOREIGN KEY (at_id) REFERENCES public.actividades(nombre);
 L   ALTER TABLE ONLY public.at_paq DROP CONSTRAINT fkpm0shucsh053ohtb927bfe47b;
       public          postgres    false    211    210    3198               O   x�sL.1�N����440�tI-Nv	�r�X�p:�d�'s�p� ʍ��`�8M����b���� a��            x�sL.1�,H,�\��%FH�=... ��	@          �  x�MQ�N�0|��b?�B4���j�x@�*�y�K����R;>q���} U�C4�3�����y�l�v�Y��Y��p��Q�q�1�$�Ҡ�<�5���������FRR��4i5�{�n9d��V)L(Lg����ތ�!�"q-�gI�7B�o�&��$��A�Ԑ��;�$��X��H�TϮ���J���|��7���坼�T�;�s��è�G5~�U���3b4�r�G�/[=���%Z*o�)s��n�6u����}��eJ������+Ǖi)S�t�c�<g����!�'^ٸ�U@���b��U�7��pԫ�'��Hw��xw�W����y��Sa� ��v�"����U#�H�_�����z�{
��5�n�{&����$s]�D���Z��d9��E�b�/��� ��      "      x������ � �      #   ,   x�+H,4�LI-N6�44�4200�54 "Nc����=... ���      $   P   x�+N��LI4�44�4202�5��521M�L ��������7�9�8��1�Đ���Y��� N#C���R��=... �+$      %   �   x�E��
�0���_"�B�7/^E0�\�6ȂeK��z�a�ǎQsO�H�dˌ$�����Z8�U�,�o�З���M�i.W(teq��r9�G������Pn���E���6��G��s�~0�2�� K0�            x������ � �     