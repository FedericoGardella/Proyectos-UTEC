--
-- PostgreSQL database dump
--

-- Dumped from database version 14.6
-- Dumped by pg_dump version 15.0

-- Started on 2023-09-12 17:18:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 39837)
-- Name: actividades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actividades (
    nombre character varying(255) NOT NULL,
    ciudad character varying(255),
    costo real,
    descr character varying(255),
    duracion integer,
    fecha date,
    nombre_dep character varying(255),
    nombreprov character varying(255)
);


ALTER TABLE public.actividades OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 39844)
-- Name: at_paq; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.at_paq (
    at_id character varying(255) NOT NULL,
    paq_id character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.at_paq OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 39851)
-- Name: departamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departamentos (
    nombre character varying(255) NOT NULL,
    descr character varying(255),
    url character varying(255)
);


ALTER TABLE public.departamentos OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 39859)
-- Name: inscripciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inscripciones (
    id bigint NOT NULL,
    cant integer,
    fecha date,
    nombresal character varying(255),
    nombretur character varying(255)
);


ALTER TABLE public.inscripciones OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 39858)
-- Name: inscripciones_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inscripciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inscripciones_id_seq OWNER TO postgres;

--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 213
-- Name: inscripciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inscripciones_id_seq OWNED BY public.inscripciones.id;


--
-- TOC entry 215 (class 1259 OID 39867)
-- Name: paquetes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paquetes (
    nombre character varying(255) NOT NULL,
    descr character varying(255),
    descuento real NOT NULL,
    fechaalta date,
    periodovaliez integer
);


ALTER TABLE public.paquetes OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 39874)
-- Name: salidaturistica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salidaturistica (
    nombre character varying(255) NOT NULL,
    cantmaxturistas integer,
    fechaalta date,
    fechasalida date,
    horasalida time without time zone,
    lugarsalida character varying(255),
    nombreact character varying(255)
);


ALTER TABLE public.salidaturistica OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 39881)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
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


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 34836)
-- Name: usuarios_inscripciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_inscripciones (
    turista_nick character varying(255) NOT NULL,
    inscripciones_id bigint NOT NULL
);


ALTER TABLE public.usuarios_inscripciones OWNER TO postgres;

--
-- TOC entry 3192 (class 2604 OID 39862)
-- Name: inscripciones id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscripciones ALTER COLUMN id SET DEFAULT nextval('public.inscripciones_id_seq'::regclass);


--
-- TOC entry 3358 (class 0 OID 39837)
-- Dependencies: 210
-- Data for Name: actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actividades (nombre, ciudad, costo, descr, duracion, fecha, nombre_dep, nombreprov) FROM stdin;
Act1	Sanjo	100	DescAct1	5	2023-09-10	Artigas	t
Act2	Sanjo2	102	DescAct2	52	2023-09-12	Artigas	t2
\.


--
-- TOC entry 3359 (class 0 OID 39844)
-- Dependencies: 211
-- Data for Name: at_paq; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.at_paq (at_id, paq_id, nombre) FROM stdin;
Act1	paq1	paq1
Act2	paq1	paq1
\.


--
-- TOC entry 3360 (class 0 OID 39851)
-- Dependencies: 212
-- Data for Name: departamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departamentos (nombre, descr, url) FROM stdin;
Artigas	123123	me quemo
Canelones	333123	arboles o comida? nunca lo sabremos
Cerro Largo	1234423	Falsos perros
Colonia	1235394	du sacramentu
Durazno	19993123	re duro
Flores	120393	o era florida?
Florida	123245	o era flores?
Lavalleja	222123	Jefe de los 33
Maldonado	9823	o Biendonado
Montevideo	990123	mas video que monte
Paysandu	8865123	Paypalsandu
Rio Negro	1345123	de tanta mugre
Rivera	233123	resistan colorados
Rocha	3563123	o cheta?
Salto	78863123	pero no muy alto
San Jose	9393123	como canelones pero sin agua salada
Soriano	9494123	existo, no me olviden
Tacuarembo	3934123	Ta re cuarembo
Treinta y Tres	123123	Sesenta y Nueve
\.


--
-- TOC entry 3362 (class 0 OID 39859)
-- Dependencies: 214
-- Data for Name: inscripciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inscripciones (id, cant, fecha, nombresal, nombretur) FROM stdin;
\.


--
-- TOC entry 3363 (class 0 OID 39867)
-- Dependencies: 215
-- Data for Name: paquetes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paquetes (nombre, descr, descuento, fechaalta, periodovaliez) FROM stdin;
paq1	desc1	10	2003-10-10	3
paq12	desc1	10	2003-10-10	3
\.


--
-- TOC entry 3364 (class 0 OID 39874)
-- Dependencies: 216
-- Data for Name: salidaturistica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salidaturistica (nombre, cantmaxturistas, fechaalta, fechasalida, horasalida, lugarsalida, nombreact) FROM stdin;
salida1	15	2023-08-22	2024-08-24	20:30:00	MiCasa	Act1
salida2	15	2023-08-30	2023-08-28	21:30:00	TuCasa	Act1
\.


--
-- TOC entry 3365 (class 0 OID 39881)
-- Dependencies: 217
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (tipo_usuario, nick, apellido, email, fechanac, name, nacionalidad, descr, link) FROM stdin;
Proveedor	t	Silva	tiav	2003-09-03	Tiago	\N	Yo	y.com
Proveedor	t2	avliS	tiavo@e	2003-03-09	ogaiT	\N	oY	y.com
Turista	tur	ape	tur1@ape.com	2000-10-26	tur1	EEUU	\N	\N
\.


--
-- TOC entry 3357 (class 0 OID 34836)
-- Dependencies: 209
-- Data for Name: usuarios_inscripciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios_inscripciones (turista_nick, inscripciones_id) FROM stdin;
\.


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 213
-- Name: inscripciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inscripciones_id_seq', 1, false);


--
-- TOC entry 3198 (class 2606 OID 39843)
-- Name: actividades actividades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT actividades_pkey PRIMARY KEY (nombre);


--
-- TOC entry 3200 (class 2606 OID 39850)
-- Name: at_paq at_paq_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT at_paq_pkey PRIMARY KEY (at_id, nombre);


--
-- TOC entry 3202 (class 2606 OID 39857)
-- Name: departamentos departamentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamentos
    ADD CONSTRAINT departamentos_pkey PRIMARY KEY (nombre);


--
-- TOC entry 3204 (class 2606 OID 39866)
-- Name: inscripciones inscripciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT inscripciones_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 39873)
-- Name: paquetes paquetes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paquetes
    ADD CONSTRAINT paquetes_pkey PRIMARY KEY (nombre);


--
-- TOC entry 3208 (class 2606 OID 39880)
-- Name: salidaturistica salidaturistica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salidaturistica
    ADD CONSTRAINT salidaturistica_pkey PRIMARY KEY (nombre);


--
-- TOC entry 3194 (class 2606 OID 34842)
-- Name: usuarios_inscripciones uk_dy43nav37qb4xjbipcymbodu9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_inscripciones
    ADD CONSTRAINT uk_dy43nav37qb4xjbipcymbodu9 UNIQUE (inscripciones_id);


--
-- TOC entry 3196 (class 2606 OID 34840)
-- Name: usuarios_inscripciones usuarios_inscripciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_inscripciones
    ADD CONSTRAINT usuarios_inscripciones_pkey PRIMARY KEY (turista_nick, inscripciones_id);


--
-- TOC entry 3210 (class 2606 OID 39887)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (nick);


--
-- TOC entry 3211 (class 2606 OID 39888)
-- Name: actividades fk2vmk7o17rcicaa9jlnrixupph; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fk2vmk7o17rcicaa9jlnrixupph FOREIGN KEY (nombre_dep) REFERENCES public.departamentos(nombre);


--
-- TOC entry 3215 (class 2606 OID 39913)
-- Name: inscripciones fkcjxf6o171q4613n737i5spqwj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT fkcjxf6o171q4613n737i5spqwj FOREIGN KEY (nombretur) REFERENCES public.usuarios(nick);


--
-- TOC entry 3216 (class 2606 OID 39908)
-- Name: inscripciones fkgqghkti8yfmvdmsi12e82cpba; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscripciones
    ADD CONSTRAINT fkgqghkti8yfmvdmsi12e82cpba FOREIGN KEY (nombresal) REFERENCES public.salidaturistica(nombre);


--
-- TOC entry 3212 (class 2606 OID 39893)
-- Name: actividades fkj9d4lq2syg86qx43c50rbivcx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fkj9d4lq2syg86qx43c50rbivcx FOREIGN KEY (nombreprov) REFERENCES public.usuarios(nick);


--
-- TOC entry 3217 (class 2606 OID 39918)
-- Name: salidaturistica fkm1xqcvqtwhdravu30kpbp5iev; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salidaturistica
    ADD CONSTRAINT fkm1xqcvqtwhdravu30kpbp5iev FOREIGN KEY (nombreact) REFERENCES public.actividades(nombre);


--
-- TOC entry 3213 (class 2606 OID 39898)
-- Name: at_paq fknptlan7v1qp8fxuycuxn4mcqo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT fknptlan7v1qp8fxuycuxn4mcqo FOREIGN KEY (paq_id) REFERENCES public.paquetes(nombre);


--
-- TOC entry 3214 (class 2606 OID 39903)
-- Name: at_paq fkpm0shucsh053ohtb927bfe47b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.at_paq
    ADD CONSTRAINT fkpm0shucsh053ohtb927bfe47b FOREIGN KEY (at_id) REFERENCES public.actividades(nombre);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-09-12 17:18:29

--
-- PostgreSQL database dump complete
--

