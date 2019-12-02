--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: book_status; Type: TYPE; Schema: public; Owner: lib_user
--

CREATE TYPE public.book_status AS ENUM (
    'READY',
    'BUSY'
);


ALTER TYPE public.book_status OWNER TO lib_user;

--
-- Name: author_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE public.author_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.author_seq OWNER TO lib_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE public.author (
    id bigint DEFAULT nextval('public.author_seq'::regclass) NOT NULL,
    first_name character varying(200),
    last_name character varying(200)
);


ALTER TABLE public.author OWNER TO lib_user;

--
-- Name: book_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE public.book_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_seq OWNER TO lib_user;

--
-- Name: book; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE public.book (
    id bigint DEFAULT nextval('public.book_seq'::regclass) NOT NULL,
    name character varying(300),
    author_id bigint,
    publisher_id bigint,
    status public.book_status,
    page integer,
    inventor_number character varying(100)
);


ALTER TABLE public.book OWNER TO lib_user;

--
-- Name: publisher_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE public.publisher_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.publisher_seq OWNER TO lib_user;

--
-- Name: publisher; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE public.publisher (
    id bigint DEFAULT nextval('public.publisher_seq'::regclass) NOT NULL,
    name character varying(200)
);


ALTER TABLE public.publisher OWNER TO lib_user;

--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: lib_user
--

COPY public.author (id, first_name, last_name) FROM stdin;
1	author 1	authorov
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: lib_user
--

COPY public.book (id, name, author_id, publisher_id, status, page, inventor_number) FROM stdin;
1	Nashriyot 1	1	1	READY	100	1212-212-121
\.


--
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: lib_user
--

COPY public.publisher (id, name) FROM stdin;
1	Nashriyot 1
\.


--
-- Name: author_seq; Type: SEQUENCE SET; Schema: public; Owner: lib_user
--

SELECT pg_catalog.setval('public.author_seq', 1, true);


--
-- Name: book_seq; Type: SEQUENCE SET; Schema: public; Owner: lib_user
--

SELECT pg_catalog.setval('public.book_seq', 1, true);


--
-- Name: publisher_seq; Type: SEQUENCE SET; Schema: public; Owner: lib_user
--

SELECT pg_catalog.setval('public.publisher_seq', 1, true);


--
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: book inventor_number_unique_k; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT inventor_number_unique_k UNIQUE (inventor_number);


--
-- Name: publisher publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- Name: book author_fk; Type: FK CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: book publisher_fk; Type: FK CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT publisher_fk FOREIGN KEY (publisher_id) REFERENCES public.publisher(id);


--
-- PostgreSQL database dump complete
--

