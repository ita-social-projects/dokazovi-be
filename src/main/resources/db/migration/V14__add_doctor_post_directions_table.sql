--
-- Create a new table for storing doctors' by-post directions.
--

-- might be removed in migration
DROP TABLE IF EXISTS doctor_post_directions;

-- Here we create a new table, based on all directions from all posts written by all doctors.
CREATE TABLE doctor_post_directions AS
    SELECT DISTINCT d.doctor_id, p.post_id, pd.direction_id
    FROM public.posts p
         JOIN public.posts_directions pd ON pd.post_id = p.post_id
         JOIN public.doctors d ON d.user_id = p.author_id;

-- Here we make sure that we will not have any duplicate values in the table
ALTER TABLE doctor_post_directions
    ADD CONSTRAINT doctor_post_directions_distinct
        UNIQUE (doctor_id, post_id, direction_id);

-- Make sure values in the table cannot be null, and that entries get deleted properly (cascade)
ALTER TABLE doctor_post_directions
    ALTER COLUMN doctor_id
        SET NOT NULL;
ALTER TABLE doctor_post_directions
    ADD CONSTRAINT doctor_fk
        FOREIGN KEY (doctor_id)
            REFERENCES public.doctors (doctor_id)
            ON DELETE CASCADE;

ALTER TABLE doctor_post_directions
    ALTER COLUMN post_id
        SET NOT NULL;
ALTER TABLE doctor_post_directions
    ADD CONSTRAINT post_fk
        FOREIGN KEY (post_id)
            REFERENCES public.posts (post_id)
            ON DELETE CASCADE;

ALTER TABLE doctor_post_directions
    ALTER COLUMN direction_id
        SET NOT NULL;
ALTER TABLE doctor_post_directions
    ADD CONSTRAINT direction_fk
        FOREIGN KEY (direction_id)
            REFERENCES public.directions (direction_id)
            ON DELETE CASCADE;

--
-- Handle creating post direction entries
--

CREATE OR REPLACE FUNCTION dpdv_handle_post_direction_insert()
RETURNS TRIGGER
AS $$
DECLARE
    VAR_USER_ID INTEGER;
	VAR_DOCTOR_ID
INTEGER;
BEGIN
	-- Get author of the post referenced by the direction
    SELECT author_id
        FROM public.posts p
        WHERE p.post_id = NEW.post_id INTO VAR_USER_ID;

    -- If there is a doctor with such a user_id
    IF (SELECT EXISTS(SELECT 1 FROM public.doctors d WHERE d.user_id = VAR_USER_ID)) THEN
        -- Get his doctor_id
        SELECT doctor_id
            FROM public.doctors d
            WHERE d.user_id = VAR_USER_ID INTO VAR_DOCTOR_ID;
        -- Add a corresponding entry to our table
        INSERT INTO public.doctor_post_directions (doctor_id, post_id, direction_id)
            VALUES (VAR_DOCTOR_ID, NEW.post_id, NEW.direction_id) ON CONFLICT DO NOTHING;
    END IF;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- might be removed in the migration
DROP TRIGGER IF EXISTS dpdv_handle_post_direction_insert_trigger
	ON public.posts_directions;
-- make sure the function above gets executed when we get a new post direction
CREATE TRIGGER dpdv_handle_post_direction_insert_trigger
    AFTER INSERT
    ON public.posts_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_post_direction_insert();

--
-- Handle deleting post direction entries
--

CREATE OR REPLACE FUNCTION dpdv_handle_post_direction_delete()
RETURNS TRIGGER
AS $$
DECLARE
    VAR_USER_ID INTEGER;
	VAR_DOCTOR_ID
INTEGER;
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
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- feel free to remove this when making a migration
DROP TRIGGER IF EXISTS dpdv_handle_post_direction_delete
	ON public.posts_directions;

-- make sure the function above gets executed when a post direction is deleted
CREATE TRIGGER dpdv_handle_post_direction_delete
    BEFORE DELETE
    ON public.posts_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_post_direction_delete();

--
-- Handle changing post author
--

CREATE OR REPLACE FUNCTION dpdv_handle_update_post_author()
RETURNS TRIGGER
AS $$
DECLARE
	VAR_NEW_DOCTOR_ID INTEGER;
BEGIN
	-- Do not do anything if the author id is unchanged
	IF NOT (OLD.author_id = NEW.author_id) THEN
	    -- Get the doctor_id
        SELECT doctor_id
            FROM public.doctors d
            WHERE d.user_id = NEW.author_id INTO VAR_NEW_DOCTOR_ID;

        -- Update all entries in our table with our post_id with the new doctor_id
        UPDATE public.doctor_post_directions dpd
            SET doctor_id = VAR_NEW_DOCTOR_ID
            WHERE dpd.post_id = NEW.post_id;
    END IF;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- This isn't needed in the migration
DROP TRIGGER IF EXISTS dpdv_handle_update_post_author_trigger
	ON public.posts;

-- Make sure the function above gets executed when a post gets updated
CREATE TRIGGER dpdv_handle_update_post_author_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_author();