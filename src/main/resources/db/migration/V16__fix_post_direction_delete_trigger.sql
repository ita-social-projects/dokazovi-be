--
-- Handle deleting post direction entries
--

CREATE OR REPLACE FUNCTION dpdv_handle_post_direction_delete()
    RETURNS TRIGGER
AS $$
DECLARE
    VAR_USER_ID INTEGER;
    VAR_DOCTOR_ID INTEGER;
BEGIN
    -- Get author of the post referenced by the direction
    SELECT author_id
    FROM public.posts p
    WHERE p.post_id = OLD.post_id INTO VAR_USER_ID;

    -- If there is a doctor with such a user_id
    IF (SELECT EXISTS(SELECT 1 FROM public.doctors d WHERE d.user_id = VAR_USER_ID)) THEN
        -- Get his doctor_id
        SELECT doctor_id
        FROM public.doctors d
        WHERE d.user_id = VAR_USER_ID INTO VAR_DOCTOR_ID;

        -- And remove the corresponding entry from our table
        DELETE
        FROM public.doctor_post_directions dpd
        WHERE dpd.doctor_id = VAR_DOCTOR_ID
          AND dpd.post_id = OLD.post_id
          AND dpd.direction_id = OLD.direction_id;
    END IF;
    RETURN OLD;
END;
$$
LANGUAGE plpgsql;
