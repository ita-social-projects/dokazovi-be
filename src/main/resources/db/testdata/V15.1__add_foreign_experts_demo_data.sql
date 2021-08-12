-- This data was used for testing before

--INSERT INTO public.foreign_experts (full_name, bio)
--VALUES ('John Doe', 'Bio #1');
--
--INSERT INTO public.foreign_experts (full_name, bio)
--VALUES ('Zhong Cun', 'Bio #2');
--
--INSERT INTO public.foreign_experts (full_name, bio)
--VALUES ('Foo Bar', 'Bio #3');

-- This pl/pgsql block is used to convert translated posts to a new format,
-- which utilizes FOREIGN_EXPERTS table.
-- This is done to not edit previous migrations, and thus not break the database.
-- WARNING This might and most probably WILL get slow on a system that already has many posts.
--         This code was written only because we haven't deployed the complete product yet.
DO $$
DECLARE
    VAR_ORIGIN_ID INTEGER;
    VAR_POST_ID INTEGER;
    VAR_USER_ID INTEGER;
    VAR_FIRST_NAME VARCHAR;
    VAR_LAST_NAME VARCHAR;
    VAR_AVATAR VARCHAR;
    VAR_BIO VARCHAR;
    VAR_FULL_NAME VARCHAR;
    VAR_FOREIGN_EXPERT_ID INTEGER;
BEGIN
    -- Get translation origin id
    SELECT *
        FROM public.origins
        WHERE name = 'Переклад'
        LIMIT 1
        INTO VAR_ORIGIN_ID;

    -- For each translation...
    FOR VAR_POST_ID IN
        SELECT post_id FROM public.posts_origins WHERE origin_id = VAR_ORIGIN_ID
    LOOP
        -- Post author is our user
        SELECT author_id
            FROM posts
            WHERE post_id = VAR_POST_ID
            LIMIT 1
            INTO VAR_USER_ID;

        -- Find it's author
        SELECT first_name, last_name, avatar
            FROM public.users
            WHERE user_id = VAR_USER_ID
            LIMIT 1
            INTO VAR_FIRST_NAME, VAR_LAST_NAME, VAR_AVATAR;

        -- If we have a corresponding doctor entry...
        IF (SELECT EXISTS(SELECT * FROM public.doctors WHERE user_id = VAR_USER_ID)) THEN
            -- Get it's bio
            SELECT bio
                FROM public.doctors
                WHERE user_id = VAR_USER_ID
                LIMIT 1
                INTO VAR_BIO;
        ELSE -- Otherwise...
            -- Set bio to a blank value
            VAR_BIO := '';
        END IF;

        -- Generate a full name for foreign_expert entry
        VAR_FULL_NAME := VAR_FIRST_NAME || ' ' || VAR_LAST_NAME;

        -- If we DO NOT have a corresponding foreign_expert entry...
        IF NOT (SELECT EXISTS(SELECT * FROM public.foreign_experts WHERE full_name = VAR_FULL_NAME)) THEN
            -- Create it
            INSERT INTO public.foreign_experts (full_name, bio, avatar)
            VALUES (VAR_FULL_NAME, VAR_BIO, VAR_AVATAR);
        END IF;

        -- Get the foreign_expert id
        SELECT foreign_expert_id
            FROM public.foreign_experts
            WHERE full_name = VAR_FULL_NAME
            LIMIT 1
            INTO VAR_FOREIGN_EXPERT_ID;

        -- Set post's foreign expert id
        UPDATE public.posts
            SET foreign_expert_id = VAR_FOREIGN_EXPERT_ID
            WHERE post_id = VAR_POST_ID;
    END LOOP;
END $$;