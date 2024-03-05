--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2022-11-16 22:59:18

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 58064)
-- Name: deposito; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deposito (
    letra character varying(15) NOT NULL,
    numero integer NOT NULL
);


ALTER TABLE public.deposito OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 58067)
-- Name: emaile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emaile (
    email character varying(128) NOT NULL,
    razonsocial character varying(50) NOT NULL,
    direccion character varying(50) NOT NULL,
    numero integer NOT NULL,
    telefono character varying(9) NOT NULL
);


ALTER TABLE public.emaile OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 58070)
-- Name: emailef; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emailef (
    email character varying(128) NOT NULL,
    usuario character varying(30) NOT NULL
);


ALTER TABLE public.emailef OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 58073)
-- Name: emailf; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emailf (
    email character varying(128) NOT NULL,
    nombre character varying(15) NOT NULL,
    apellido character varying(15) NOT NULL,
    calle character varying(50) NOT NULL,
    numero integer NOT NULL
);


ALTER TABLE public.emailf OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 58076)
-- Name: emp_contactos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emp_contactos (
    rut character varying(12) NOT NULL,
    contactos character varying(64) NOT NULL
);


ALTER TABLE public.emp_contactos OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 58079)
-- Name: sec_emplfirma; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_emplfirma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE public.sec_emplfirma OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 58080)
-- Name: empl_firma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empl_firma (
    id integer DEFAULT nextval('public.sec_emplfirma'::regclass) NOT NULL,
    email character varying(128) NOT NULL
);


ALTER TABLE public.empl_firma OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 58084)
-- Name: empresas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresas (
    rut character varying(12) NOT NULL,
    email character varying(128) NOT NULL
);


ALTER TABLE public.empresas OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 58087)
-- Name: equipos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipos (
    nroserie character varying(32) NOT NULL,
    modelo character varying(64) NOT NULL,
    descripcion character varying(256) NOT NULL,
    rut character varying(12) NOT NULL
);


ALTER TABLE public.equipos OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 58090)
-- Name: funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionarios (
    ci character varying(8) NOT NULL,
    email character varying(128) NOT NULL
);


ALTER TABLE public.funcionarios OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 58093)
-- Name: sec_idkit; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_idkit
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE public.sec_idkit OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 58094)
-- Name: kits; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kits (
    id integer DEFAULT nextval('public.sec_idkit'::regclass) NOT NULL,
    rut character varying(12) NOT NULL
);


ALTER TABLE public.kits OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 58098)
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modelo (
    modelo character varying(64) NOT NULL,
    marca character varying(64) NOT NULL
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 58101)
-- Name: orden_envio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orden_envio (
    nroremito character varying(15) NOT NULL,
    empreparto character varying(64) NOT NULL,
    fecha date NOT NULL,
    ci character varying(8) NOT NULL,
    nroserie character varying(32) NOT NULL,
    idkit integer NOT NULL,
    idempleado integer NOT NULL,
    fechasolicitud date
);


ALTER TABLE public.orden_envio OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 58104)
-- Name: precios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.precios (
    id integer NOT NULL,
    fecha date NOT NULL,
    montodia integer NOT NULL,
    mantenimiento boolean NOT NULL
);


ALTER TABLE public.precios OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 58107)
-- Name: sec_idadministrae; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_idadministrae
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE public.sec_idadministrae OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 58108)
-- Name: r_administrae; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_administrae (
    id integer DEFAULT nextval('public.sec_idadministrae'::regclass) NOT NULL,
    nroserie character varying(12) NOT NULL,
    idempleado integer NOT NULL,
    tipo character varying(64) NOT NULL
);


ALTER TABLE public.r_administrae OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 58112)
-- Name: sec_idadministrak; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_idadministrak
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE public.sec_idadministrak OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 58113)
-- Name: r_administrak; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_administrak (
    idempleado integer NOT NULL,
    idkit integer NOT NULL,
    tipo character varying(32) NOT NULL,
    id integer DEFAULT nextval('public.sec_idadministrak'::regclass) NOT NULL
);


ALTER TABLE public.r_administrak OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 58117)
-- Name: r_almacenae; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_almacenae (
    nroserie character varying(12) NOT NULL,
    letra character varying(15) NOT NULL,
    numero integer NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL
);


ALTER TABLE public.r_almacenae OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 58120)
-- Name: r_almacenak; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_almacenak (
    id integer NOT NULL,
    letra character varying(15) NOT NULL,
    numero integer NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL
);


ALTER TABLE public.r_almacenak OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 58123)
-- Name: r_costoe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_costoe (
    id integer NOT NULL,
    nroserie character varying(12) NOT NULL
);


ALTER TABLE public.r_costoe OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 58126)
-- Name: r_costok; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_costok (
    id integer NOT NULL,
    idkit integer NOT NULL
);


ALTER TABLE public.r_costok OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 58129)
-- Name: r_devuelven; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_devuelven (
    ci character varying(8) NOT NULL,
    nroserie character varying(12) NOT NULL,
    fecha date NOT NULL,
    hora time(4) without time zone NOT NULL,
    mantenimiento boolean NOT NULL
);


ALTER TABLE public.r_devuelven OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 58132)
-- Name: sec_idprecio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_idprecio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE public.sec_idprecio OWNER TO postgres;

--
-- TOC entry 3472 (class 0 OID 0)
-- Dependencies: 233
-- Name: sec_idprecio; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sec_idprecio OWNED BY public.precios.id;


--
-- TOC entry 234 (class 1259 OID 58133)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    usuario character varying(30) NOT NULL,
    nombre character varying(15) NOT NULL,
    apellido character varying(15) NOT NULL,
    nacimiento date NOT NULL,
    genero character varying(15),
    "contraseña" character varying(30) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 58315)
-- Name: vista_kpi; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vista_kpi AS
 SELECT avg((orden_envio.fecha - orden_envio.fechasolicitud)) AS tiempo_ciclo_interno,
    ((( SELECT count(*) AS count
           FROM public.orden_envio orden_envio_1
          WHERE ((orden_envio_1.fecha - orden_envio_1.fechasolicitud) < 2)) * 100) / ( SELECT count(*) AS count
           FROM public.orden_envio orden_envio_1)) AS porcentaje_entregadas_a_tiempo
   FROM public.orden_envio;


ALTER TABLE public.vista_kpi OWNER TO postgres;

--
-- TOC entry 3254 (class 2604 OID 58140)
-- Name: precios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.precios ALTER COLUMN id SET DEFAULT nextval('public.sec_idprecio'::regclass);


--
-- TOC entry 3294 (class 2606 OID 58142)
-- Name: r_costoe costoe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT costoe_pkey PRIMARY KEY (id, nroserie);


--
-- TOC entry 3296 (class 2606 OID 58144)
-- Name: r_costok costok_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT costok_pkey PRIMARY KEY (idkit, id);


--
-- TOC entry 3262 (class 2606 OID 58146)
-- Name: emaile emaile_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emaile
    ADD CONSTRAINT emaile_pk PRIMARY KEY (email) INCLUDE (email);


--
-- TOC entry 3264 (class 2606 OID 58148)
-- Name: emailef emailef_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emailef
    ADD CONSTRAINT emailef_pkey PRIMARY KEY (email);


--
-- TOC entry 3276 (class 2606 OID 58150)
-- Name: funcionarios emailf_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT emailf_pkey PRIMARY KEY (ci);


--
-- TOC entry 3268 (class 2606 OID 58152)
-- Name: emp_contactos emp_contactos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emp_contactos
    ADD CONSTRAINT emp_contactos_pkey PRIMARY KEY (rut, contactos);


--
-- TOC entry 3300 (class 2606 OID 58154)
-- Name: usuarios empl_firma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT empl_firma_pkey PRIMARY KEY (usuario) INCLUDE (usuario);


--
-- TOC entry 3270 (class 2606 OID 58156)
-- Name: empl_firma empl_firma_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empl_firma
    ADD CONSTRAINT empl_firma_pkey1 PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 58158)
-- Name: empresas empresas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (rut);


--
-- TOC entry 3274 (class 2606 OID 58160)
-- Name: equipos equipos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT equipos_pkey PRIMARY KEY (nroserie);


--
-- TOC entry 3266 (class 2606 OID 58162)
-- Name: emailf funcionarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emailf
    ADD CONSTRAINT funcionarios_pkey PRIMARY KEY (email);


--
-- TOC entry 3288 (class 2606 OID 58164)
-- Name: r_administrak id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 3278 (class 2606 OID 58166)
-- Name: kits kits_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits
    ADD CONSTRAINT kits_pkey PRIMARY KEY (id);


--
-- TOC entry 3280 (class 2606 OID 58168)
-- Name: modelo modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (modelo);


--
-- TOC entry 3282 (class 2606 OID 58170)
-- Name: orden_envio orden_envio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT orden_envio_pkey PRIMARY KEY (nroremito);


--
-- TOC entry 3298 (class 2606 OID 58172)
-- Name: r_devuelven pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT pk PRIMARY KEY (ci, nroserie, fecha, hora) INCLUDE (nroserie, ci, fecha);


--
-- TOC entry 3258 (class 2606 OID 58174)
-- Name: deposito pk_ubicacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deposito
    ADD CONSTRAINT pk_ubicacion PRIMARY KEY (letra, numero) INCLUDE (letra, numero);


--
-- TOC entry 3284 (class 2606 OID 58176)
-- Name: precios precios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.precios
    ADD CONSTRAINT precios_pkey PRIMARY KEY (id);


--
-- TOC entry 3286 (class 2606 OID 58178)
-- Name: r_administrae r_administrae_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT r_administrae_pkey PRIMARY KEY (id, nroserie, idempleado) INCLUDE (id, nroserie, idempleado);


--
-- TOC entry 3290 (class 2606 OID 58180)
-- Name: r_almacenae r_almacenae_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT r_almacenae_pkey PRIMARY KEY (nroserie, letra, numero, fecha, hora) INCLUDE (nroserie, letra, numero, fecha, hora);


--
-- TOC entry 3292 (class 2606 OID 58182)
-- Name: r_almacenak r_almacenak_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT r_almacenak_pkey PRIMARY KEY (id, letra, numero, fecha, hora) INCLUDE (id, letra, numero, fecha, hora);


--
-- TOC entry 3260 (class 2606 OID 58184)
-- Name: deposito ubicacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deposito
    ADD CONSTRAINT ubicacion UNIQUE (numero, letra) INCLUDE (letra, numero);


--
-- TOC entry 3309 (class 2606 OID 58185)
-- Name: orden_envio ci; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT ci FOREIGN KEY (ci) REFERENCES public.funcionarios(ci) NOT VALID;


--
-- TOC entry 3325 (class 2606 OID 58190)
-- Name: r_devuelven ci; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT ci FOREIGN KEY (ci) REFERENCES public.funcionarios(ci) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3303 (class 2606 OID 58195)
-- Name: empl_firma email; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empl_firma
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emailef(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3304 (class 2606 OID 58200)
-- Name: empresas email; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emaile(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3307 (class 2606 OID 58205)
-- Name: funcionarios email; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT email FOREIGN KEY (email) REFERENCES public.emailf(email) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3321 (class 2606 OID 58210)
-- Name: r_costoe id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT id FOREIGN KEY (id) REFERENCES public.precios(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3310 (class 2606 OID 58215)
-- Name: orden_envio idempleado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) NOT VALID;


--
-- TOC entry 3313 (class 2606 OID 58220)
-- Name: r_administrae idempleado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3315 (class 2606 OID 58225)
-- Name: r_administrak idempleado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT idempleado FOREIGN KEY (idempleado) REFERENCES public.empl_firma(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3311 (class 2606 OID 58230)
-- Name: orden_envio idkit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) NOT VALID;


--
-- TOC entry 3316 (class 2606 OID 58235)
-- Name: r_administrak idkit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrak
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3319 (class 2606 OID 58240)
-- Name: r_almacenak idkit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT idkit FOREIGN KEY (id) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3323 (class 2606 OID 58245)
-- Name: r_costok idkit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT idkit FOREIGN KEY (idkit) REFERENCES public.kits(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3324 (class 2606 OID 58250)
-- Name: r_costok idprecio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costok
    ADD CONSTRAINT idprecio FOREIGN KEY (id) REFERENCES public.precios(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3305 (class 2606 OID 58255)
-- Name: equipos modelo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT modelo FOREIGN KEY (modelo) REFERENCES public.modelo(modelo) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3312 (class 2606 OID 58260)
-- Name: orden_envio nroserie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_envio
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie);


--
-- TOC entry 3314 (class 2606 OID 58265)
-- Name: r_administrae nroserie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_administrae
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3317 (class 2606 OID 58270)
-- Name: r_almacenae nroserie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3322 (class 2606 OID 58275)
-- Name: r_costoe nroserie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_costoe
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3326 (class 2606 OID 58280)
-- Name: r_devuelven nroserie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_devuelven
    ADD CONSTRAINT nroserie FOREIGN KEY (nroserie) REFERENCES public.equipos(nroserie) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3302 (class 2606 OID 58285)
-- Name: emp_contactos rut; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emp_contactos
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3306 (class 2606 OID 58290)
-- Name: equipos rut; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipos
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3308 (class 2606 OID 58295)
-- Name: kits rut; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits
    ADD CONSTRAINT rut FOREIGN KEY (rut) REFERENCES public.empresas(rut) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3318 (class 2606 OID 58300)
-- Name: r_almacenae ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenae
    ADD CONSTRAINT ubicacion FOREIGN KEY (letra, numero) REFERENCES public.deposito(letra, numero) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3320 (class 2606 OID 58305)
-- Name: r_almacenak ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_almacenak
    ADD CONSTRAINT ubicacion FOREIGN KEY (letra, numero) REFERENCES public.deposito(letra, numero) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3301 (class 2606 OID 58310)
-- Name: emailef usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emailef
    ADD CONSTRAINT usuario FOREIGN KEY (usuario) REFERENCES public.usuarios(usuario) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


-- Completed on 2022-11-16 22:59:18

--
-- PostgreSQL database dump complete
--

