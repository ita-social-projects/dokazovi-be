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

CREATE TABLE public.users (
    id integer NOT NULL,
    created_at timestamp without time zone,
    name character varying(255),
    email character varying(255),
    password character varying(255),
    status character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    qualification character varying(255),
    phone character varying(255),
    biography text
);

ALTER TABLE public.users OWNER TO dokazovi;