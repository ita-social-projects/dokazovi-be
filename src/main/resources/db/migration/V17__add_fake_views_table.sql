CREATE TABLE public.post_fake_views (
    id SERIAL PRIMARY KEY,
    post_id INTEGER
        CONSTRAINT FAKE_VIEWS_POST_ID_FKEY REFERENCES public.posts,
    views INTEGER DEFAULT 0
);