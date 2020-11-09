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
  "user_id" SERIAL PRIMARY KEY,
  "email" varchar,
  "password" varchar,
  "status" varchar,
  "first_name" varchar,
  "last_name" varchar,
  "qualification" varchar,
  "phone" varchar,
  "bio" text,
  "created_at" TIMESTAMP DEFAULT (now())
);

ALTER TABLE public.users OWNER TO dokazovi;

CREATE TABLE public.roles (
  "role_id" SERIAL PRIMARY KEY,
  "role_name" varchar
);

ALTER TABLE public.roles OWNER TO dokazovi;

CREATE TABLE public.roles_users (
  "role_id" int,
  "user_id" int
);

ALTER TABLE public.roles_users OWNER TO dokazovi;

CREATE TABLE public.regions (
  "region_id" SERIAL PRIMARY KEY,
  "name" varchar
);

ALTER TABLE public.regions OWNER TO dokazovi;

CREATE TABLE public.institutions (
  "institution_id" SERIAL PRIMARY KEY,
  "name" varchar,
  "region_id" int,
  "address" varchar
);

ALTER TABLE public.institutions OWNER TO dokazovi;

CREATE TABLE public.users_institutions (
  "user_id" int,
  "institution_id" int,
  "is_primary" boolean
);

ALTER TABLE public.users_institutions OWNER TO dokazovi;

CREATE TABLE public.tags (
  "tag_id" SERIAL PRIMARY KEY,
  "tag" varchar
);

ALTER TABLE public.tags OWNER TO dokazovi;

CREATE TABLE public.posts_tags (
  "post_id" int,
  "tag_id" int
);

ALTER TABLE public.posts_tags OWNER TO dokazovi;

CREATE TABLE public.posts (
  "post_id" SERIAL PRIMARY KEY,
  "author_id" int,
  "direction_id" int,
  "type_id" int,
  "title" varchar,
  "content" text,
  "status" varchar,
  "important" boolean,
  "tags" varchar,
  "created_at" TIMESTAMP DEFAULT (now()),
  "modified_at" TIMESTAMP DEFAULT (now())
);

ALTER TABLE public.posts OWNER TO dokazovi;

CREATE TABLE public.charities (
  "charity_id" SERIAL PRIMARY KEY,
  "body" text,
  "author_id" INT,
  "created_at" TIMESTAMP DEFAULT (now()),
  "modified_at" TIMESTAMP DEFAULT (now())
);

ALTER TABLE public.charities OWNER TO dokazovi;

CREATE TABLE public.sources (
  "source_id" SERIAL PRIMARY KEY,
  "type" varchar,
  "value" varchar
);

ALTER TABLE public.sources OWNER TO dokazovi;

CREATE TABLE public.users_sources (
  "user_id" int,
  "source_id" int
);

ALTER TABLE public.users_sources OWNER TO dokazovi;

CREATE TABLE public.posts_sources (
  "post_id" int,
  "source_id" int
);

ALTER TABLE public.posts_sources OWNER TO dokazovi;

CREATE TABLE public.users_directions (
  "user_id" int,
  "direction_id" int
);

ALTER TABLE public.users_directions OWNER TO dokazovi;

CREATE TABLE public.directions (
  "direction_id" SERIAL PRIMARY KEY,
  "name" varchar
);

ALTER TABLE public.directions OWNER TO dokazovi;

CREATE TABLE public.posts_directions (
  "post_id" int,
  "direction_id" int
);

ALTER TABLE public.posts_directions OWNER TO dokazovi;

CREATE TABLE public.post_types (
  "type_id" SERIAL PRIMARY KEY,
  "name" varchar
);

ALTER TABLE public.post_types OWNER TO dokazovi;

ALTER TABLE public.roles_users ADD FOREIGN KEY (role_id) REFERENCES public.roles(role_id);

ALTER TABLE public.roles_users ADD FOREIGN KEY (user_id) REFERENCES public.users(user_id);

ALTER TABLE public.institutions ADD FOREIGN KEY (region_id) REFERENCES public.regions(region_id);

ALTER TABLE public.users_institutions ADD FOREIGN KEY (user_id) REFERENCES public.users (user_id);

ALTER TABLE public.users_institutions ADD FOREIGN KEY (institution_id) REFERENCES public.institutions (institution_id);

ALTER TABLE public.posts_tags ADD FOREIGN KEY (post_id) REFERENCES public.posts (post_id);

ALTER TABLE public.posts_tags ADD FOREIGN KEY (tag_id) REFERENCES public.tags (tag_id);

ALTER TABLE public.posts ADD FOREIGN KEY (author_id) REFERENCES public.users (user_id);

ALTER TABLE public.posts ADD FOREIGN KEY (type_id) REFERENCES public.post_types (type_id);

ALTER TABLE public.charities ADD FOREIGN KEY (author_id) REFERENCES public.users (user_id);

ALTER TABLE public.users_sources ADD FOREIGN KEY (user_id) REFERENCES public.users (user_id);

ALTER TABLE public.users_sources ADD FOREIGN KEY (source_id) REFERENCES public.sources (source_id);

ALTER TABLE public.posts_sources ADD FOREIGN KEY (post_id) REFERENCES public.posts (post_id);

ALTER TABLE public.posts_sources ADD FOREIGN KEY (source_id) REFERENCES public.sources (source_id);

ALTER TABLE public.users_directions ADD FOREIGN KEY (user_id) REFERENCES public.users (user_id);

ALTER TABLE public.users_directions ADD FOREIGN KEY (direction_id) REFERENCES public.directions (direction_id);

ALTER TABLE public.posts_directions ADD FOREIGN KEY (post_id) REFERENCES public.posts (post_id);

ALTER TABLE public.posts_directions ADD FOREIGN KEY (direction_id) REFERENCES public.directions (direction_id);