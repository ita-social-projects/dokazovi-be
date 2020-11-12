ALTER TABLE public.users ADD direction_id int;

ALTER TABLE public.users ADD institution_id int;

ALTER TABLE public.users_institutions DROP COLUMN is_primary;

ALTER TABLE public.users ADD FOREIGN KEY (direction_id) REFERENCES public.directions (direction_id);

ALTER TABLE public.users ADD FOREIGN KEY (institution_id) REFERENCES public.institutions (institution_id);

ALTER TABLE public.posts ADD FOREIGN KEY (direction_id) REFERENCES public.directions (direction_id);

