--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-09 03:36:00

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
-- TOC entry 215 (class 1259 OID 24615)
-- Name: anime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.anime (
    anime_id integer NOT NULL,
    name character varying(100) NOT NULL,
    seriescount integer,
    status character varying(20) NOT NULL,
    studio character varying(50) NOT NULL,
    typeofanime character varying(20) NOT NULL,
    sourceofanime character varying(20) NOT NULL,
    avatar character varying(50),
    background character varying(50),
    rating double precision,
    views integer,
    outdate timestamp with time zone
);


ALTER TABLE public.anime OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24618)
-- Name: anime_anime_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.anime_anime_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.anime_anime_id_seq OWNER TO postgres;

--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 216
-- Name: anime_anime_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.anime_anime_id_seq OWNED BY public.anime.anime_id;


--
-- TOC entry 217 (class 1259 OID 24619)
-- Name: animecomment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animecomment (
    animecomment_id integer NOT NULL,
    user_id integer,
    anime_id integer,
    comment character varying(250)
);


ALTER TABLE public.animecomment OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24622)
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animecomments_animecomment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animecomments_animecomment_id_seq OWNER TO postgres;

--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 218
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animecomments_animecomment_id_seq OWNED BY public.animecomment.animecomment_id;


--
-- TOC entry 219 (class 1259 OID 24623)
-- Name: animegenre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animegenre (
    animegenre_id integer NOT NULL,
    genre_id integer,
    anime_id integer
);


ALTER TABLE public.animegenre OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24626)
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animegenres_animegenre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animegenres_animegenre_id_seq OWNER TO postgres;

--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 220
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animegenres_animegenre_id_seq OWNED BY public.animegenre.animegenre_id;


--
-- TOC entry 228 (class 1259 OID 32816)
-- Name: animeseries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animeseries (
    animeseries_id integer NOT NULL,
    animeid integer,
    number integer NOT NULL,
    video character varying(100) NOT NULL
);


ALTER TABLE public.animeseries OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 32815)
-- Name: animeseries_animeseries_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animeseries_animeseries_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animeseries_animeseries_id_seq OWNER TO postgres;

--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 227
-- Name: animeseries_animeseries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animeseries_animeseries_id_seq OWNED BY public.animeseries.animeseries_id;


--
-- TOC entry 221 (class 1259 OID 24627)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    genre_id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24630)
-- Name: genres_genre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genres_genre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.genres_genre_id_seq OWNER TO postgres;

--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 222
-- Name: genres_genre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.genres_genre_id_seq OWNED BY public.genre.genre_id;


--
-- TOC entry 225 (class 1259 OID 24635)
-- Name: userlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userlist (
    userlist_id integer NOT NULL,
    user_id integer,
    anime_id integer,
    listtype character varying(20) NOT NULL
);


ALTER TABLE public.userlist OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24631)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(50),
    ranking character varying(50),
    reg_date timestamp with time zone,
    role character varying(10)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24634)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 226 (class 1259 OID 24638)
-- Name: userslist_userlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.userslist_userlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.userslist_userlist_id_seq OWNER TO postgres;

--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 226
-- Name: userslist_userlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.userslist_userlist_id_seq OWNED BY public.userlist.userlist_id;


--
-- TOC entry 4664 (class 2604 OID 24639)
-- Name: anime anime_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anime ALTER COLUMN anime_id SET DEFAULT nextval('public.anime_anime_id_seq'::regclass);


--
-- TOC entry 4665 (class 2604 OID 24640)
-- Name: animecomment animecomment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment ALTER COLUMN animecomment_id SET DEFAULT nextval('public.animecomments_animecomment_id_seq'::regclass);


--
-- TOC entry 4666 (class 2604 OID 24641)
-- Name: animegenre animegenre_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre ALTER COLUMN animegenre_id SET DEFAULT nextval('public.animegenres_animegenre_id_seq'::regclass);


--
-- TOC entry 4670 (class 2604 OID 32819)
-- Name: animeseries animeseries_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animeseries ALTER COLUMN animeseries_id SET DEFAULT nextval('public.animeseries_animeseries_id_seq'::regclass);


--
-- TOC entry 4667 (class 2604 OID 24642)
-- Name: genre genre_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre ALTER COLUMN genre_id SET DEFAULT nextval('public.genres_genre_id_seq'::regclass);


--
-- TOC entry 4669 (class 2604 OID 24644)
-- Name: userlist userlist_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist ALTER COLUMN userlist_id SET DEFAULT nextval('public.userslist_userlist_id_seq'::regclass);


--
-- TOC entry 4668 (class 2604 OID 24643)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 4837 (class 0 OID 24615)
-- Dependencies: 215
-- Data for Name: anime; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.anime (anime_id, name, seriescount, status, studio, typeofanime, sourceofanime, avatar, background, rating, views, outdate) FROM stdin;
9	name	5	ongoing	studio	ona	source	cf94dd28-c01b-4f11-bca4-bc11dcce65cd.pic1.jpg	6bda0822-0ba9-41a8-88d7-051ec629ae1f.banner2.jpg	0	0	2024-04-08 00:51:28.9+06
10	name2	5	completed	studio	special	source	1b2a5b6c-023c-4c4d-9bad-32a9317ef1a8.pic4.jpg	3b3c9109-1e08-403c-b838-421bce9ab52d.banner2.jpg	0	0	2024-04-08 00:54:54.211+06
11	name3	10	completed	studio	ona	source	208668e8-166b-453c-9095-15802d1925d7.pic8.jpg	a49105c2-a45e-445f-8eee-7b9f08936cc6.banner2.jpg	0	0	2024-04-08 03:12:24.554+06
12	name4	3	completed	studio	ova	source	192d2a52-1e68-49bd-aeb2-8657c9bead2a.pic6.jpg	3c7d1ed0-8067-4f0c-bae3-5526afe1ff91.banner2.jpg	0	0	2024-04-09 04:21:30.616+06
\.


--
-- TOC entry 4839 (class 0 OID 24619)
-- Dependencies: 217
-- Data for Name: animecomment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animecomment (animecomment_id, user_id, anime_id, comment) FROM stdin;
\.


--
-- TOC entry 4841 (class 0 OID 24623)
-- Dependencies: 219
-- Data for Name: animegenre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animegenre (animegenre_id, genre_id, anime_id) FROM stdin;
\.


--
-- TOC entry 4850 (class 0 OID 32816)
-- Dependencies: 228
-- Data for Name: animeseries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animeseries (animeseries_id, animeid, number, video) FROM stdin;
7	9	3	9/3.mp4
8	9	1	9/1.mp4
9	9	2	9/2.mp4
10	9	4	9/4.mp4
12	11	1	11/1.mp4
13	10	1	10/1.mp4
14	10	2	10/2.mp4
15	12	1	12/1.mp4
\.


--
-- TOC entry 4843 (class 0 OID 24627)
-- Dependencies: 221
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.genre (genre_id, name) FROM stdin;
\.


--
-- TOC entry 4847 (class 0 OID 24635)
-- Dependencies: 225
-- Data for Name: userlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userlist (userlist_id, user_id, anime_id, listtype) FROM stdin;
\.


--
-- TOC entry 4845 (class 0 OID 24631)
-- Dependencies: 223
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, login, password, email, ranking, reg_date, role) FROM stdin;
27	meow	123456	abuallaban2002@bk.ru	\N	2024-04-07 00:52:30.269+06	user
26	aha1	123456	ahmad@mail.ru	\N	2024-04-05 04:44:12.814+06	admin
28	aisana200250@gmail.com	aisana12345	aisana200250@gmail.com	\N	2024-04-07 02:10:30.57+06	user
\.


--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 216
-- Name: anime_anime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.anime_anime_id_seq', 12, true);


--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 218
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animecomments_animecomment_id_seq', 1, false);


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 220
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animegenres_animegenre_id_seq', 1, false);


--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 227
-- Name: animeseries_animeseries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animeseries_animeseries_id_seq', 15, true);


--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 222
-- Name: genres_genre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genres_genre_id_seq', 1, false);


--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 28, true);


--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 226
-- Name: userslist_userlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.userslist_userlist_id_seq', 1, false);


--
-- TOC entry 4672 (class 2606 OID 24646)
-- Name: anime anime_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anime
    ADD CONSTRAINT anime_pkey PRIMARY KEY (anime_id);


--
-- TOC entry 4674 (class 2606 OID 24648)
-- Name: animecomment animecomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_pkey PRIMARY KEY (animecomment_id);


--
-- TOC entry 4676 (class 2606 OID 24650)
-- Name: animegenre animegenres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_pkey PRIMARY KEY (animegenre_id);


--
-- TOC entry 4686 (class 2606 OID 32821)
-- Name: animeseries animeseries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animeseries
    ADD CONSTRAINT animeseries_pkey PRIMARY KEY (animeseries_id);


--
-- TOC entry 4678 (class 2606 OID 24652)
-- Name: genre genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genres_pkey PRIMARY KEY (genre_id);


--
-- TOC entry 4680 (class 2606 OID 24654)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4682 (class 2606 OID 24656)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4684 (class 2606 OID 24658)
-- Name: userlist userslist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_pkey PRIMARY KEY (userlist_id);


--
-- TOC entry 4687 (class 2606 OID 24659)
-- Name: animecomment animecomments_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4688 (class 2606 OID 24664)
-- Name: animecomment animecomments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4689 (class 2606 OID 24669)
-- Name: animegenre animegenres_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4690 (class 2606 OID 24674)
-- Name: animegenre animegenres_genre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id);


--
-- TOC entry 4693 (class 2606 OID 32822)
-- Name: animeseries animeseries_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animeseries
    ADD CONSTRAINT animeseries_anime_id_fkey FOREIGN KEY (animeid) REFERENCES public.anime(anime_id);


--
-- TOC entry 4691 (class 2606 OID 24679)
-- Name: userlist userslist_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4692 (class 2606 OID 24684)
-- Name: userlist userslist_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


-- Completed on 2024-04-09 03:36:00

--
-- PostgreSQL database dump complete
--

