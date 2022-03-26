--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

-- Started on 2022-03-24 22:40:11 CET

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

DROP DATABASE users;
--
-- TOC entry 3029 (class 1262 OID 16509)
-- Name: users; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE users WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_ES.UTF-8' LC_CTYPE = 'es_ES.UTF-8';


ALTER DATABASE users OWNER TO postgres;

\connect users

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16752)
-- Name: roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    rolename character varying(255)
);


ALTER TABLE public.roles OWNER TO root;

--
-- TOC entry 202 (class 1259 OID 16750)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO root;

--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 205 (class 1259 OID 16760)
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    surname character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO root;

--
-- TOC entry 204 (class 1259 OID 16758)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO root;

--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 206 (class 1259 OID 16769)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    rol_id bigint NOT NULL
);


ALTER TABLE public.users_roles OWNER TO root;

--
-- TOC entry 2883 (class 2604 OID 16755)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2884 (class 2604 OID 16763)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3020 (class 0 OID 16752)
-- Dependencies: 203
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.roles (id, rolename) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USER
\.


--
-- TOC entry 3022 (class 0 OID 16760)
-- Dependencies: 205
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.users (id, email, password, surname, username) FROM stdin;
1	admin@email.com	$2a$10$vciRXr1VkeL0SvyvlZ6exOneeXmZuFzqLDiPGj0B6DcACzok3/Nlu	admin	admin
2	roberto@email.com	$2a$10$UBbuhVaUdiJEA15qNBKVgev1aYyrA/oArerQ9WhvL10wg5O3nPAue	roberto	roberto
3	pepe@email.com	$2a$10$fLjcThaJPUJE8DjUo2QN3uWpdA6zfy7fETL/xtAGSTPloHwGlevNi	pepe garcia	pepe
4	marta@email.com	$2a$10$JxjnzNVzMUTnRYZk2qDhM.JMV.wn0ejjeWP03U8uD4lbL6vZrRQyW	Marta gomez	marta
5	jose@email.com	$2a$10$S5hLVoqCZ/G7156hBf/GWu3zDcjSeSFBwxLx0qLV0XA6OEi3iHHSS	Jose Nieto	Jose
\.


--
-- TOC entry 3023 (class 0 OID 16769)
-- Dependencies: 206
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.users_roles (user_id, rol_id) FROM stdin;
1	2
1	1
2	2
3	2
4	2
5	2
\.


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.users_id_seq', 5, true);


--
-- TOC entry 2886 (class 2606 OID 16757)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2888 (class 2606 OID 16768)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2890 (class 2606 OID 16773)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, rol_id);


--
-- TOC entry 2892 (class 2606 OID 16779)
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2891 (class 2606 OID 16774)
-- Name: users_roles fkrq701yrxk88ea15i2oesc7fxk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkrq701yrxk88ea15i2oesc7fxk FOREIGN KEY (rol_id) REFERENCES public.roles(id);


-- Completed on 2022-03-24 22:40:11 CET

--
-- PostgreSQL database dump complete
--

