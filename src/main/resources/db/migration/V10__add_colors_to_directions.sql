ALTER TABLE directions ADD color VARCHAR(7) DEFAULT NULL;

UPDATE public.directions
SET color = '#d1c4e9'
WHERE direction_id = 6;

UPDATE public.directions
SET color = '#7aebbf'
WHERE direction_id = 3;

UPDATE public.directions
SET color = '#ef5350'
WHERE direction_id = 1;

UPDATE public.directions
SET color = '#ffee58'
WHERE direction_id = 7;

UPDATE public.directions
SET color = '#ffee58'
WHERE direction_id = 4;

UPDATE public.directions
SET color = '#98ef50'
WHERE direction_id = 2;

UPDATE public.directions
SET color = '#da80e8'
WHERE direction_id = 5;