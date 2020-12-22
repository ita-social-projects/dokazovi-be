alter table directions rename column name to label;

alter table directions
    add name varchar;

UPDATE public.directions
SET name = 'covid-19'
WHERE direction_id = 1;

UPDATE public.directions
SET name = 'ophthalmology'
WHERE direction_id = 2;

UPDATE public.directions
SET name = 'surgery'
WHERE direction_id = 3;

UPDATE public.directions
SET name = 'therapy'
WHERE direction_id = 4;

UPDATE public.directions
SET name = 'virology'
WHERE direction_id = 5;

UPDATE public.directions
SET name = 'cardiology'
WHERE direction_id = 6;

UPDATE public.directions
SET name = 'pediatrics'
WHERE direction_id = 7;

alter table posts drop constraint posts_direction_id_fkey;

alter table posts drop column direction_id;