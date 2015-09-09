--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: dogs; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE dogs (
    id integer NOT NULL,
    name character varying,
    profile_pic character varying,
    summary character varying,
    owner_id integer
);


ALTER TABLE dogs OWNER TO "Guest";

--
-- Name: dogs_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE dogs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dogs_id_seq OWNER TO "Guest";

--
-- Name: dogs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE dogs_id_seq OWNED BY dogs.id;


--
-- Name: interests; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE interests (
    id integer NOT NULL,
    dog_id integer,
    swimming boolean,
    eating boolean,
    fristbee boolean,
    running boolean,
    barking boolean
);


ALTER TABLE interests OWNER TO "Guest";

--
-- Name: interests_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE interests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE interests_id_seq OWNER TO "Guest";

--
-- Name: interests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE interests_id_seq OWNED BY interests.id;


--
-- Name: match; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE match (
    id integer NOT NULL,
    dog_id integer,
    dog_friend_id integer,
    interest_score integer
);


ALTER TABLE match OWNER TO "Guest";

--
-- Name: match_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE match_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE match_id_seq OWNER TO "Guest";

--
-- Name: match_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE match_id_seq OWNED BY match.id;


--
-- Name: owners; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE owners (
    id integer NOT NULL,
    owner_name character varying,
    email character varying,
    profile_pic character varying
);


ALTER TABLE owners OWNER TO "Guest";

--
-- Name: owners_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE owners_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE owners_id_seq OWNER TO "Guest";

--
-- Name: owners_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE owners_id_seq OWNED BY owners.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY dogs ALTER COLUMN id SET DEFAULT nextval('dogs_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY interests ALTER COLUMN id SET DEFAULT nextval('interests_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY match ALTER COLUMN id SET DEFAULT nextval('match_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY owners ALTER COLUMN id SET DEFAULT nextval('owners_id_seq'::regclass);


--
-- Data for Name: dogs; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY dogs (id, name, profile_pic, summary, owner_id) FROM stdin;
\.


--
-- Name: dogs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('dogs_id_seq', 1, false);


--
-- Data for Name: interests; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY interests (id, dog_id, swimming, eating, fristbee, running, barking) FROM stdin;
\.


--
-- Name: interests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('interests_id_seq', 1, false);


--
-- Data for Name: match; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY match (id, dog_id, dog_friend_id, interest_score) FROM stdin;
\.


--
-- Name: match_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('match_id_seq', 1, false);


--
-- Data for Name: owners; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY owners (id, owner_name, email, profile_pic) FROM stdin;
\.


--
-- Name: owners_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('owners_id_seq', 1, false);


--
-- Name: dogs_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY dogs
    ADD CONSTRAINT dogs_pkey PRIMARY KEY (id);


--
-- Name: interests_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY interests
    ADD CONSTRAINT interests_pkey PRIMARY KEY (id);


--
-- Name: match_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY match
    ADD CONSTRAINT match_pkey PRIMARY KEY (id);


--
-- Name: owners_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY owners
    ADD CONSTRAINT owners_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

