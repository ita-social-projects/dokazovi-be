--
-- Visible
--

-- Add the column
ALTER TABLE public.doctor_post_directions
    ADD COLUMN visible BOOLEAN DEFAULT FALSE;

-- Fill the column data
UPDATE public.doctor_post_directions dpd
    SET visible = (SELECT status = 'PUBLISHED' FROM public.posts WHERE post_id = dpd.post_id)
    WHERE TRUE; -- we update everything on intent

-- Handle inserting entries to our table
CREATE OR REPLACE FUNCTION dpdv_handle_insert_check_visible() RETURNS TRIGGER
AS $$
BEGIN
    NEW.visible = (SELECT status = 'PUBLISHED' FROM public.posts WHERE post_id = NEW.post_id);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Register the trigger
CREATE TRIGGER dpdv_handle_insert_check_visible_trigger
    BEFORE INSERT
    ON public.doctor_post_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_insert_check_visible();

-- Handle updating post status
CREATE OR REPLACE FUNCTION dpdv_handle_update_post_status_for_visible() RETURNS TRIGGER
AS $$
BEGIN
    UPDATE public.doctor_post_directions
        SET visible = (NEW.status = 'PUBLISHED')
        WHERE post_id = NEW.post_id;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Register the trigger
CREATE TRIGGER dpdv_handle_update_post_status_for_visible_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_status_for_visible();

-- Make sure we don't have any heavily broken values
ALTER TABLE public.doctor_post_directions
    ALTER COLUMN visible
        SET NOT NULL;
ALTER TABLE public.doctor_post_directions
    ALTER COLUMN visible
        SET DEFAULT FALSE;

--
-- User id
--

-- Add user_id to the table
ALTER TABLE public.doctor_post_directions
    ADD COLUMN user_id INTEGER;

-- Add ON DELETE CASCADE
ALTER TABLE public.doctor_post_directions
    ADD CONSTRAINT user_fk
        FOREIGN KEY (user_id)
            REFERENCES public.users (user_id)
            ON DELETE CASCADE;

-- Fill the column properly
UPDATE public.doctor_post_directions dpd
    SET user_id = (SELECT user_id FROM public.doctors WHERE doctor_id = dpd.doctor_id LIMIT 1)
    WHERE TRUE; -- we update everything on intent

-- Handle inserting entries to our table
CREATE OR REPLACE FUNCTION dpdv_handle_insert_set_user_id() RETURNS TRIGGER
AS $$
BEGIN
    NEW.user_id = (SELECT user_id FROM public.doctors WHERE doctor_id = NEW.doctor_id LIMIT 1);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Register the trigger
CREATE TRIGGER dpdv_handle_insert_set_user_id_trigger
    BEFORE INSERT
    ON public.doctor_post_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_insert_set_user_id();

-- Handle updating post author id
CREATE OR REPLACE FUNCTION dpdv_handle_update_post_author_for_user_id() RETURNS TRIGGER
AS $$
BEGIN
    -- Do not do anything if the author id is unchanged
    IF NOT (OLD.author_id = NEW.author_id) THEN
        UPDATE public.doctor_post_directions dpd
            SET user_id = NEW.author_id
            WHERE dpd.post_id = NEW.post_id;
    END IF;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Register the trigger
CREATE TRIGGER dpdv_handle_update_post_author_for_user_id_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_author_for_user_id();

-- Make sure we don't have any broken values and have a not-so-broken fallback value.
ALTER TABLE public.doctor_post_directions
    ALTER COLUMN user_id
        SET NOT NULL;
