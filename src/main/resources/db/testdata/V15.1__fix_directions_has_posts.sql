UPDATE public.directions d
    SET has_posts = (
        SELECT EXISTS (
            SELECT 1 FROM public.doctor_post_directions dpd WHERE dpd.direction_id = d.direction_id));
