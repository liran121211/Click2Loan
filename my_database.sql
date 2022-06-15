--
-- PostgreSQL database dump
--

-- Dumped from database version 12.11 (Ubuntu 12.11-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.11 (Ubuntu 12.11-0ubuntu0.20.04.1)

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
-- Name: bankers; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.bankers (
    id integer NOT NULL,
    user_id integer,
    last_name character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    street character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    city character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    country character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    zipcode character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    phone character varying DEFAULT 'NOT SET YET'::character varying NOT NULL,
    first_name character varying DEFAULT 'NOT SET YET'::character varying NOT NULL
);


ALTER TABLE public.bankers OWNER TO liransm;

--
-- Name: banker_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.banker_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.banker_id_seq OWNER TO liransm;

--
-- Name: banker_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.banker_id_seq OWNED BY public.bankers.id;


--
-- Name: clients; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    first_name character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    last_name character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    street character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    city character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    country character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    zipcode character varying DEFAULT '00000'::character varying,
    phone character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    user_id integer NOT NULL,
    bank_number integer DEFAULT 0 NOT NULL,
    credits double precision DEFAULT 0.0 NOT NULL
);


ALTER TABLE public.clients OWNER TO liransm;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO liransm;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: loan_form_data; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.loan_form_data (
    id integer NOT NULL,
    user_id integer NOT NULL,
    code_gender character varying NOT NULL,
    flag_own_car character varying NOT NULL,
    flag_own_realty character varying NOT NULL,
    cnt_children character varying NOT NULL,
    amt_income_total character varying NOT NULL,
    amt_credit character varying NOT NULL,
    amt_goods_price character varying NOT NULL,
    name_education_type character varying NOT NULL,
    name_family_status character varying NOT NULL,
    name_housing_type character varying NOT NULL,
    region_population_relative character varying NOT NULL,
    days_employed character varying NOT NULL,
    own_car_age character varying,
    flag_mobil character varying NOT NULL,
    flag_emp_phone character varying NOT NULL,
    flag_work_phone character varying NOT NULL,
    flag_phone character varying NOT NULL,
    flag_email character varying NOT NULL,
    occupation_type character varying NOT NULL,
    cnt_fam_members character varying,
    region_rating_client character varying,
    organization_type character varying NOT NULL,
    initial_status character varying NOT NULL,
    first_name character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    last_name character varying DEFAULT 'UNKNOWN'::character varying NOT NULL,
    address character varying DEFAULT 'NA'::character varying NOT NULL,
    city character varying DEFAULT 'NA'::character varying NOT NULL,
    zipcode character varying DEFAULT 0 NOT NULL,
    county character varying DEFAULT 'NA'::character varying NOT NULL,
    country character varying DEFAULT 'NA'::character varying NOT NULL,
    phone character varying DEFAULT 'NA'::character varying NOT NULL,
    reference_id character varying DEFAULT 'NA'::character varying,
    age_days integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.loan_form_data OWNER TO liransm;

--
-- Name: loans; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.loans (
    id integer NOT NULL,
    user_id integer NOT NULL,
    request_date date,
    loan_amount double precision DEFAULT 0.0 NOT NULL,
    remaining_amount double precision DEFAULT 0.0 NOT NULL,
    status character varying DEFAULT 'PENDING'::character varying NOT NULL
);


ALTER TABLE public.loans OWNER TO liransm;

--
-- Name: loans1_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.loans1_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loans1_id_seq OWNER TO liransm;

--
-- Name: loans1_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.loans1_id_seq OWNED BY public.loans.id;


--
-- Name: loans_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.loans_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loans_id_seq OWNER TO liransm;

--
-- Name: loans_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.loans_id_seq OWNED BY public.loan_form_data.id;


--
-- Name: mailbox; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.mailbox (
    id integer NOT NULL,
    sender integer NOT NULL,
    receiver integer NOT NULL,
    subject character varying DEFAULT 'EMPTY SUBJECT'::character varying NOT NULL,
    body character varying DEFAULT 'EMPTY MESSAGE'::character varying NOT NULL,
    date_sent date NOT NULL,
    read bit(1)
);


ALTER TABLE public.mailbox OWNER TO liransm;

--
-- Name: mailbox_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.mailbox_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mailbox_id_seq OWNER TO liransm;

--
-- Name: mailbox_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.mailbox_id_seq OWNED BY public.mailbox.id;


--
-- Name: todolist; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.todolist (
    id integer NOT NULL,
    user_id integer,
    item character varying NOT NULL,
    status character varying DEFAULT 'NEW'::character varying NOT NULL
);


ALTER TABLE public.todolist OWNER TO liransm;

--
-- Name: todolist_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.todolist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.todolist_id_seq OWNER TO liransm;

--
-- Name: todolist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.todolist_id_seq OWNED BY public.todolist.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying,
    password character varying NOT NULL,
    email character varying NOT NULL,
    role integer,
    last_logged_in date DEFAULT '2022-02-02'::date NOT NULL
);


ALTER TABLE public.users OWNER TO liransm;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO liransm;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: withdraw_requests; Type: TABLE; Schema: public; Owner: liransm
--

CREATE TABLE public.withdraw_requests (
    id integer NOT NULL,
    user_id integer NOT NULL,
    amount double precision DEFAULT 0.0 NOT NULL,
    bank_name character varying DEFAULT 'NOT_AVAILABLE'::character varying NOT NULL,
    branch_code integer DEFAULT 0 NOT NULL,
    bank_number integer DEFAULT 0 NOT NULL,
    full_name character varying DEFAULT 'NOT_AVAILABLE'::character varying NOT NULL,
    date date
);


ALTER TABLE public.withdraw_requests OWNER TO liransm;

--
-- Name: withdraw_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: liransm
--

CREATE SEQUENCE public.withdraw_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.withdraw_requests_id_seq OWNER TO liransm;

--
-- Name: withdraw_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liransm
--

ALTER SEQUENCE public.withdraw_requests_id_seq OWNED BY public.withdraw_requests.id;


--
-- Name: bankers id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.bankers ALTER COLUMN id SET DEFAULT nextval('public.banker_id_seq'::regclass);


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: loan_form_data id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loan_form_data ALTER COLUMN id SET DEFAULT nextval('public.loans_id_seq'::regclass);


--
-- Name: loans id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loans ALTER COLUMN id SET DEFAULT nextval('public.loans1_id_seq'::regclass);


--
-- Name: mailbox id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.mailbox ALTER COLUMN id SET DEFAULT nextval('public.mailbox_id_seq'::regclass);


--
-- Name: todolist id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.todolist ALTER COLUMN id SET DEFAULT nextval('public.todolist_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: withdraw_requests id; Type: DEFAULT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.withdraw_requests ALTER COLUMN id SET DEFAULT nextval('public.withdraw_requests_id_seq'::regclass);


--
-- Data for Name: bankers; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.bankers (id, user_id, last_name, street, city, country, zipcode, phone, first_name) FROM stdin;
1	1	Smadja	NOT SET YET	NOT SET YET	NOT SET YET	NOT SET YET	050-4444444	Liran
\.


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.clients (id, first_name, last_name, street, city, country, zipcode, phone, user_id, bank_number, credits) FROM stdin;
14	Lionel	B. Hudgens	2143 Taylor St	Haifa	Israel	8508651	054-5342124	22	0	0
13	Charles	J. Quintanilla	Yitzhak Sadeh St 17	Tel Aviv-Yafo	Israel	6777517	054-4508895	21	0	0
16	demo	a	dfg	dfg	Bolivia	5456	052-6576453	26	0	0
1	Yonatan	Avrahamov	Jerusalem St 7	Tel-Aviv	Israel	4066911	054-8069904	6	31458060	18449
17	Israel	Israeli	Street 123	Beer Sheva	Israel	845569041	054-9056690	27	4056904	2080979
2	Eli	Siman-Tov	Ha-Zayit St 1	Tzafria	Israel	6093200	050-5736671	8	31245906	170000
4	Nathan Lee	Boks	Arik Einstein St 4	Ashdod	Israel	7777322	08-6897766	10	24590588	115000
3	Rivka	Peretz	Shlomo ha-Melekh St 2	Be'er Sheva	Israel	8454902	08-6497676	9	14056997	90000
\.


--
-- Data for Name: loan_form_data; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.loan_form_data (id, user_id, code_gender, flag_own_car, flag_own_realty, cnt_children, amt_income_total, amt_credit, amt_goods_price, name_education_type, name_family_status, name_housing_type, region_population_relative, days_employed, own_car_age, flag_mobil, flag_emp_phone, flag_work_phone, flag_phone, flag_email, occupation_type, cnt_fam_members, region_rating_client, organization_type, initial_status, first_name, last_name, address, city, zipcode, county, country, phone, reference_id, age_days) FROM stdin;
24	21	Male	false	false	0	22128.0	2050200.0	0.0	Incomplete higher Education	Single	With Parents	0.03	27	0	false	false	false	false	false	Unknown	0	1	Self Employed	rejected	Charles	J. Quintanilla	Yitzhak Sadeh St 17	Tel Aviv-Yafo	6777517	North	Israel	972-54-4508895	6gTbgA4v	17520
25	22	Male	false	false	1	127000.0	2100200.0	0.0	Higher Education	Married	House or Apartment	0.07	27	0	true	true	true	false	true	Sales	2	1	Insurance	rejected	Lionel	B. Hudgens	2143 Taylor St	Haifa	8508651	Center	Israel	054-5342124	pOp4ri3E	17520
22	19	Male	false	false	0	13000.0	157500.0	157500.0	Academic Degree	Single	With Parents	0.07	1933	2018	false	false	false	false	false	Accountants	6	1	School	approved	Charles	J. Quintanilla	Yitzhak Sadeh St 17	Tel Aviv-Yafo	6777517	Center	Israel	972-54-4508895	fmPxIYMs	17520
27	26	Male	false	false	4	456.0	345345.0	0.0	Academic Degree	Married	Co-Op Apartment	0.07	19	0	true	true	true	false	true	Accountants	8	1	Police	rejected	demo	a	dfg	dfg	5456	Center	Bolivia	576453	vW2TDnCF	0
28	27	Male	true	false	0	150000.0	2100000.0	0.0	Higher Education	Single	House or Apartment	0.03	1122	2014	false	false	false	false	false	Security	6	1	Self Employed	approved	Israel	Israeli	Street 123	Beer Sheva	845569041	South	Israel	054-9056690	qqQLZ6cP	0
13	8	0	1	1	2	170000	13000	2700000	1	2	1	0	1500	2015	1	1	1	1	1	6	2	1	13	1	Eli	Siman-Tov	Ha-Zayit St	Tzafria	6093200	South	Israel	050-5736671	G6F9T566	8760
15	10	0	0	0	0	115000	7000	0	3	2	3	1	3400	0	1	1	1	1	1	4	4	1	18	2	Nathan Lee	Boks	Arik Einstein St 4	Ashdod	7777322	Center	Israel	08-6897766	TG66DDA1	17520
14	9	1	1	0	0	90000	50000	0	3	4	2	2	340	2012	1	0	1	1	0	3	0	2	7	0	Rivka	Peretz	Shlomo ha-Melekh St 2	Be'er Sheva	8454902	South	Israel	072-3307400	P30FKVVS	10585
3	6	1	0	0	1	63000	19000	0	1	1	1	1	265	0	1	1	1	1	1	1	1	1	1	2	Yonatan	Avrahamov	Jerusalem 7	Tel-Aviv	6040669	Center	Israel	054-8069904	50F699DD	16060
\.


--
-- Data for Name: loans; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.loans (id, user_id, request_date, loan_amount, remaining_amount, status) FROM stdin;
14	9	2022-05-07	90000	90000	REJECTED
15	10	2022-05-07	115000	115000	REJECTED
13	8	2022-05-07	170000	170000	APPROVED
22	19	2022-05-17	157500	157500	APPROVED
24	21	2022-05-17	2050200	2050200	PENDING
25	22	2022-05-22	2100200	2100200	APPROVED
27	26	2022-05-23	345345	345345	PENDING
28	27	2022-05-28	2100000	2100000	REJECTED
3	6	2021-03-02	19000	19000	APPROVED
\.


--
-- Data for Name: mailbox; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.mailbox (id, sender, receiver, subject, body, date_sent, read) FROM stdin;
58	6	1	TEST SUBJECT	TEST MESSAGE	2022-05-02	0
63	6	2	Banker Report::assd	asdasd	2022-05-12	0
66	6	2	Bug :: bug in program	report bug	2022-05-13	1
68	27	2	Banker Report :: URGENT!	need to report banker!	2022-05-28	0
60	6	1	TEST SUBJECT	TEST MESSAGE	2022-05-03	1
19	1	3	What is Lorem Ipsum?	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.	2022-04-06	0
21	1	2	Where can I get some?	There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.	2022-04-11	1
22	1	3	Why do we use it?	Resize the image by percentage, or resize it to be exactly the size you specified, for example: 1366x768 pixels. 	2022-04-06	0
23	1	3	Where can I get some?	There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.	2022-04-20	0
33	2	1	Reply: Why do we use it?	ok	2022-04-30	0
34	2	1	Reply: Why do we use it?	 OK\n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
64	6	2	Banker Report::liran Smjosses is a bug 	sdgfdfsgdfgfdgdfg	2022-05-12	0
67	22	1	vvxcvxc	vxcvxcvxc	2022-05-22	0
48	2	2	TEST MESSAGE PLEASE IGNORE #9801456456	TEST MESSAGE PLEASE IGNORE #9801456456	2022-04-30	0
51	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
69	1	6	Reply: TEST SUBJECT	 this is a reply\n--------------------------------------------------------------------------------------------\nTEST MESSAGE	2022-05-28	0
49	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
55	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
52	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
56	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
47	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
50	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
54	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
57	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	0
20	1	2	Why do we use it?	It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-08	1
62	6	2	bug	buggg5341564231	2022-05-12	0
65	6	2	Bug :: TEST SUBJECT	TEST MESSAGE	2022-05-12	0
59	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-05-03	1
53	2	1	TEST MESSAGE PLEASE IGNORE #9801456456	 \n--------------------------------------------------------------------------------------------\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	2022-04-30	1
\.


--
-- Data for Name: todolist; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.todolist (id, user_id, item, status) FROM stdin;
6	1	Write Your Task Here...dddd dddd ok	DONE
8	1	I just modifed it!	NEW
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.users (id, username, password, email, role, last_logged_in) FROM stdin;
19	josh	1234	josh@gmail.com	0	2022-05-17
3	batheav	PM2022	batheav@ac.sce.ac.il	2	2022-03-19
4	adeelke	PM2022	adeelke@ac.sce.ac.il	2	2022-03-19
27	israel123	12345	israel@ac.sce.ac.il	0	2022-05-28
6	yonatan	1234	david@ac.sce.ac.il	0	2022-05-29
2	tamaram	PM2022	tamaram@ac.sce.ac.il 	2	2022-05-29
1	liransm	PM2022	liransm@ac.sce.ac.il	1	2022-05-29
21	michael123	1234	michael123@gmail.com	0	2022-05-19
8	elicohen	1234	eli@tzfaria.co.il	0	2022-01-05
9	rivkami	1234	rivkami@walla.co.il	0	2022-04-06
10	natanli	1234	natanli@books.com	0	2022-05-07
26	demo	1234	demo@gmail.com	0	2022-05-23
22	test11	test11	test11@gmail.com	0	2022-05-23
\.


--
-- Data for Name: withdraw_requests; Type: TABLE DATA; Schema: public; Owner: liransm
--

COPY public.withdraw_requests (id, user_id, amount, bank_name, branch_code, bank_number, full_name, date) FROM stdin;
4	10	100	Mizrahi Tfahot	13	40369951	Nathan Lee Boks	2022-05-12
3	9	1000	Hapoalim	9	40569004	Rivka Peretz	2022-05-12
2	6	100	Leumi	10	50690354	Yonatan Avrahamov	2022-05-12
8	6	150	Leumi	10	50630146	Yonatan	2022-05-15
7	6	100	discont	1	31091234	Tamar	2022-05-13
9	27	19000	Leumi	10	5069054	Israel Israeli	2022-05-28
10	27	10	Leumi	10	5069870	Israel Israeli	2022-05-28
11	27	11	Leuni	10	4059061	Israel Israeli	2022-05-28
\.


--
-- Name: banker_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.banker_id_seq', 1, true);


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.clients_id_seq', 17, true);


--
-- Name: loans1_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.loans1_id_seq', 6, true);


--
-- Name: loans_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.loans_id_seq', 28, true);


--
-- Name: mailbox_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.mailbox_id_seq', 69, true);


--
-- Name: todolist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.todolist_id_seq', 11, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.users_id_seq', 27, true);


--
-- Name: withdraw_requests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liransm
--

SELECT pg_catalog.setval('public.withdraw_requests_id_seq', 11, true);


--
-- Name: clients clients_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pk PRIMARY KEY (id);


--
-- Name: loans loans1_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans1_pk PRIMARY KEY (id);


--
-- Name: loan_form_data loans_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loan_form_data
    ADD CONSTRAINT loans_pk PRIMARY KEY (id);


--
-- Name: mailbox mailbox_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.mailbox
    ADD CONSTRAINT mailbox_pk PRIMARY KEY (id);


--
-- Name: todolist todolist_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.todolist
    ADD CONSTRAINT todolist_pk PRIMARY KEY (id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: withdraw_requests withdraw_requests_pk; Type: CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.withdraw_requests
    ADD CONSTRAINT withdraw_requests_pk PRIMARY KEY (id);


--
-- Name: banker_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX banker_id_uindex ON public.bankers USING btree (id);


--
-- Name: banker_user_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX banker_user_id_uindex ON public.bankers USING btree (user_id);


--
-- Name: clients_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX clients_id_uindex ON public.clients USING btree (id);


--
-- Name: clients_user_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX clients_user_id_uindex ON public.clients USING btree (user_id);


--
-- Name: loans1_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX loans1_id_uindex ON public.loans USING btree (id);


--
-- Name: loans_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX loans_id_uindex ON public.loan_form_data USING btree (id);


--
-- Name: loans_user_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX loans_user_id_uindex ON public.loan_form_data USING btree (user_id);


--
-- Name: mailbox_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX mailbox_id_uindex ON public.mailbox USING btree (id);


--
-- Name: todolist_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX todolist_id_uindex ON public.todolist USING btree (id);


--
-- Name: users_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);


--
-- Name: users_username_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX users_username_uindex ON public.users USING btree (username);


--
-- Name: withdraw_requests_id_uindex; Type: INDEX; Schema: public; Owner: liransm
--

CREATE UNIQUE INDEX withdraw_requests_id_uindex ON public.withdraw_requests USING btree (id);


--
-- Name: bankers banker_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.bankers
    ADD CONSTRAINT banker_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: clients clients_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: loans loans_loan_form_data_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_loan_form_data_id_fk FOREIGN KEY (id) REFERENCES public.loan_form_data(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: loans loans_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: loan_form_data loans_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.loan_form_data
    ADD CONSTRAINT loans_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: mailbox mailbox_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.mailbox
    ADD CONSTRAINT mailbox_users_id_fk FOREIGN KEY (sender) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: todolist todolist_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.todolist
    ADD CONSTRAINT todolist_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: withdraw_requests withdraw_requests_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: liransm
--

ALTER TABLE ONLY public.withdraw_requests
    ADD CONSTRAINT withdraw_requests_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

