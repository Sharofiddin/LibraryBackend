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

CREATE TYPE book_status AS ENUM (
    'READY',
    'BUSY'
);


ALTER TYPE book_status OWNER TO lib_user;

--
-- Name: author_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE author_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE author_seq OWNER TO lib_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE author (
    id bigint DEFAULT nextval('author_seq'::regclass) NOT NULL,
    first_name character varying(200),
    last_name character varying(200)
);


ALTER TABLE author OWNER TO lib_user;

--
-- Name: book_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE book_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_seq OWNER TO lib_user;

--
-- Name: book; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE book (
    id bigint DEFAULT nextval('book_seq'::regclass) NOT NULL,
    name character varying(300),
    author_id bigint,
    publisher_id bigint,
    status book_status,
    page integer,
    inventor_number character varying(100)
);


ALTER TABLE book OWNER TO lib_user;

--
-- Name: publisher_seq; Type: SEQUENCE; Schema: public; Owner: lib_user
--

CREATE SEQUENCE publisher_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE publisher_seq OWNER TO lib_user;

--
-- Name: publisher; Type: TABLE; Schema: public; Owner: lib_user
--

CREATE TABLE publisher (
    id bigint DEFAULT nextval('publisher_seq'::regclass) NOT NULL,
    name character varying(200)
);


ALTER TABLE publisher OWNER TO lib_user;


ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: book inventor_number_unique_k; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY book
    ADD CONSTRAINT inventor_number_unique_k UNIQUE (inventor_number);


--
-- Name: publisher publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- Name: book author_fk; Type: FK CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY book
    ADD CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES author(id);


--
-- Name: book publisher_fk; Type: FK CONSTRAINT; Schema: public; Owner: lib_user
--

ALTER TABLE ONLY book
    ADD CONSTRAINT publisher_fk FOREIGN KEY (publisher_id) REFERENCES publisher(id);

CREATE TABLE appuser ( 
    id bigserial NOT NULL , 
    email varchar(45) unique NOT NULL, 
    login varchar(45) NOT NULL, 
    password varchar(64) NOT NULL, 
    enabled boolean DEFAULT false, 
   PRIMARY KEY (id) 
 );

 CREATE TABLE role ( 
   id serial NOT NULL, 
   name varchar(45) NOT NULL, 
   PRIMARY KEY (id) 
 );

 create table userrole( user_id bigint REFERENCES appuser(id) , role_id int REFERENCES role(id));
 
 INSERT INTO role (name) values('ADMIN');
 
 INSERT INTO appuser (login, password,email, enabled) VALUES ('admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 'example@mail.ru', true);