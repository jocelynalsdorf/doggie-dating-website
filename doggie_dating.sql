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
    owner_id integer,
    password character varying
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
-- Name: dogs_interests; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE dogs_interests (
    id integer NOT NULL,
    dog_id integer,
    interest_id integer
);


ALTER TABLE dogs_interests OWNER TO "Guest";

--
-- Name: dogs_interests_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE dogs_interests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dogs_interests_id_seq OWNER TO "Guest";

--
-- Name: dogs_interests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE dogs_interests_id_seq OWNED BY dogs_interests.id;


--
-- Name: interests; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE interests (
    id integer NOT NULL,
    name character varying
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
    interest_score integer,
    i_like boolean
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

ALTER TABLE ONLY dogs_interests ALTER COLUMN id SET DEFAULT nextval('dogs_interests_id_seq'::regclass);


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

COPY dogs (id, name, profile_pic, summary, owner_id, password) FROM stdin;
11	Koru	http://i.imgur.com/vEQbf9I.jpg	Rescue dog with a great attitude. Loves to eat cat poop. High cute factor	9	1234
13	Sasha	http://i.imgur.com/kVF5i5o.jpg	Sasha is sassy and likes to chew on ropes	11	1234
14	Sergio	http://i.imgur.com/SPaeXZV.jpg	Sergio is a bad boy turned good. He is looking for all sorts of new friends, including cats.	12	1234
15	Bobaque	http://i.imgur.com/ceCPhmZ.jpg	Bobaque knows his way around the street. He likes frisbee 	13	1234
16	Goon Dog	http://i.imgur.com/gPObsyf.jpg	I am totally a dog!!	14	alko33
17	Cujo	http://i.imgur.com/PoG2ZBV.jpg	My bark is not worse than my bite.	15	never
18	Wishbone	http://i.imgur.com/7uIF77r.jpg	I love to read, travel through time and solve mysteries.	16	Book
19	Frankenweenie	http://i.imgur.com/P8vF1En.jpg	I love all things spooky and long walks at night in the graveyard.	17	spooks
20	Scooby Doo	http://i.imgur.com/QuCPVEy.jpg	I love eating with my pal Shaggy and solving mysteries with the gang.	18	teresa
21	Aqua Fina	http://i.imgur.com/SHZPisD.jpg	I love swimming and making goofy faces.	19	swim
22	Dogald Trump	http://i.imgur.com/dfFnmEc.jpg	I love taking over the world and looking fly in the process.	20	alko33
23	Nina	http://i.imgur.com/UOBayYa.jpg	I love taking naps!	21	12345
24	Hero	http://i.imgur.com/tpzn5ND.jpg	I like to chase things.	22	12345
\.


--
-- Name: dogs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('dogs_id_seq', 24, true);


--
-- Data for Name: dogs_interests; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY dogs_interests (id, dog_id, interest_id) FROM stdin;
1	0	3
2	0	3
3	0	3
4	6	1
5	6	2
6	6	4
7	7	3
8	7	5
9	7	3
10	8	4
11	8	3
12	8	4
13	9	1
14	9	3
15	9	4
16	10	3
17	10	4
18	10	1
19	11	2
20	11	5
21	11	3
32	13	5
33	13	4
34	13	1
35	14	5
36	14	2
37	14	3
38	15	2
39	15	3
40	15	4
41	16	2
42	16	4
43	16	5
44	17	2
45	18	2
46	18	3
47	18	5
48	19	5
49	19	4
50	19	2
51	20	2
52	20	4
53	21	1
54	21	2
55	21	5
56	22	5
57	22	4
58	22	2
59	23	3
60	23	4
61	23	5
62	24	2
63	24	4
64	24	3
\.


--
-- Name: dogs_interests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('dogs_interests_id_seq', 64, true);


--
-- Data for Name: interests; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY interests (id, name) FROM stdin;
1	swimming
2	eating
3	frisbee
4	running
5	barking
\.


--
-- Name: interests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('interests_id_seq', 5, true);


--
-- Data for Name: match; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY match (id, dog_id, dog_friend_id, interest_score, i_like) FROM stdin;
2	1	3	4	t
3	2	3	4	t
1	1	2	3	f
4	3	2	5	\N
5	11	3	\N	t
6	16	21	\N	t
\.


--
-- Name: match_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('match_id_seq', 6, true);


--
-- Data for Name: owners; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY owners (id, owner_name, email, profile_pic) FROM stdin;
1	Sam	sam@gmail.com	url
2	jlkjdfjl	jljkljgjl	ljkjkglsdfjg
3	Maggie	jlkajdflkaj	nowhere
4	jklajdkflaj	jlkajdflkaj	nowhere
5	jklajdkflaj	jlkajdflkaj	kjljakldjfa
6	Maggie 2	jlkajdflkaj	nowhere
7	Maggie6	ilikebugs@maggie.com	nowhere
8	Maggie3	jlkajdflkaj	kjljakldjfa
9	Jake	jake@fake.com	http://i.imgur.com/YE14ZcB.jpg
10	Ariel	ariel@faker.com	http://i.imgur.com/36xL1Mm.jpg
11	Ariel	ariel@fake.com	http://i.imgur.com/36xL1Mm.jpg
12	Kallen	Kallen@faker.com	http://i.imgur.com/rCGi2SZ.jpg
13	Fredrick	fredrick@faker.com	http://i.imgur.com/45UDfuf.jpg
14	Ryback	Ryback_sucks@gmail.com	http://i.imgur.com/wpkta0j.png
15	Stephen King	Stephen.king@gmail.com	http://i.imgur.com/ZMgwknR.jpg
16	Joe	joe@gmai.com 	http://i.imgur.com/iNNqzE2.png
17	Tim Burton	Spooks@creeps.com	http://i.imgur.com/G72naDZ.jpg
18	Shaggy	Shaggy@mysteryinc.com	http://i.imgur.com/FdO4Mxa.jpg
19	Michael Phelps	swim@water.com	http://i.imgur.com/vYWcLSY.jpg
20	Donald Trump	politics@joke.com	http://i.imgur.com/AziwZT1.jpg
21	Morgan	morgan@morgan.com	http://www.roytanck.com/wp-content/uploads/2010/08/avatar-150x150.png
22	Travis	travis@toggleable.com	http://lzmarieauthor.com/wp-content/uploads/2013/03/african-lion-male_436_600x450-150x150.jpg
\.


--
-- Name: owners_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('owners_id_seq', 22, true);


--
-- Name: dogs_interests_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY dogs_interests
    ADD CONSTRAINT dogs_interests_pkey PRIMARY KEY (id);


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

