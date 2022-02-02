--
-- Handle creating post direction entries to add doctor_post_directions entries
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

DROP TRIGGER IF EXISTS dpdv_handle_post_direction_insert_trigger
    ON public.posts_directions;
CREATE TRIGGER dpdv_handle_post_direction_insert_trigger
    AFTER INSERT
        ON public.posts_directions
        FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_post_direction_insert();

--
-- Handle deleting post direction entries so that we can delete entries from doctor_post_directions
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

DROP TRIGGER IF EXISTS dpdv_handle_post_direction_delete
    ON public.posts_directions;
CREATE TRIGGER dpdv_handle_post_direction_delete
    BEFORE DELETE
    ON public.posts_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_post_direction_delete();

--
-- Handle changing post author to set doctor_id
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

DROP TRIGGER IF EXISTS dpdv_handle_update_post_author_trigger
    ON public.posts;
CREATE TRIGGER dpdv_handle_update_post_author_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_author();

--
-- Handle inserting entries to doctor_post_directions to set visible field
--

CREATE OR REPLACE FUNCTION dpdv_handle_insert_check_visible() RETURNS TRIGGER
AS $$
BEGIN
    NEW.visible = (SELECT status = 'PUBLISHED' FROM public.posts WHERE post_id = NEW.post_id);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS dpdv_handle_insert_check_visible_trigger
    ON public.doctor_post_directions;
CREATE TRIGGER dpdv_handle_insert_check_visible_trigger
    BEFORE INSERT
    ON public.doctor_post_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_insert_check_visible();

--
-- Handle updating post status to set doctor_post_directions.visible field
--

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

DROP TRIGGER IF EXISTS dpdv_handle_update_post_status_for_visible_trigger
    ON public.posts;
CREATE TRIGGER dpdv_handle_update_post_status_for_visible_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_status_for_visible();

--
-- Handle inserting entries to doctor_post_directions to set user_id based on passed doctor_id
--

CREATE OR REPLACE FUNCTION dpdv_handle_insert_set_user_id() RETURNS TRIGGER
AS $$
BEGIN
    NEW.user_id = (SELECT user_id FROM public.doctors WHERE doctor_id = NEW.doctor_id LIMIT 1);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS dpdv_handle_insert_set_user_id_trigger
    ON public.doctor_post_directions;
CREATE TRIGGER dpdv_handle_insert_set_user_id_trigger
    BEFORE INSERT
    ON public.doctor_post_directions
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_insert_set_user_id();

--
-- Handle updating post author id to update user_id in doctor_post_directions
--

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

DROP TRIGGER IF EXISTS dpdv_handle_update_post_author_for_user_id_trigger
    ON public.posts;
CREATE TRIGGER dpdv_handle_update_post_author_for_user_id_trigger
    AFTER UPDATE
    ON public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_handle_update_post_author_for_user_id();

--
-- Updating views in posts which is sum of fake and real views
--

CREATE OR REPLACE FUNCTION dpdv_update_views_for_post() RETURNS TRIGGER
AS $$
BEGIN
        UPDATE public.posts
            SET views = fake_views + real_views
            WHERE post_id = NEW.post_id;
        return NEW;
end;
$$
LANGUAGE plpgsql;
DROP TRIGGER IF EXISTS dpdv_update_views_for_post_trigger
    on public.posts;
CREATE TRIGGER dpdv_update_views_for_post_trigger
    AFTER UPDATE OF real_views, fake_views
    on public.posts
    FOR EACH ROW EXECUTE PROCEDURE dpdv_update_views_for_post();

CREATE OR REPLACE FUNCTION update_first_name_for_post() RETURNS TRIGGER
AS $$
BEGIN
        UPDATE public.posts
            SET first_name = (select first_name from users where posts.author_id = users.user_id);
end;
$$
LANGUAGE plpgsql;
DROP TRIGGER IF EXISTS update_first_name_for_post_trigger
    on public.posts;
CREATE TRIGGER update_first_name_for_post_trigger
    AFTER UPDATE OR INSERT OR DELETE
    on public.users
    for each row execute procedure update_first_name_for_post();
