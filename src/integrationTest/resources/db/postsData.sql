INSERT INTO PUBLIC.POSTS (TITLE, PREVIEW, CONTENT, VIDEO_URL, PREVIEW_IMAGE_URL, AUTHOR_ID, TYPE_ID,
                          CREATED_AT, MODIFIED_AT, PUBLISHED_AT, STATUS, IMPORTANT)
VALUES ('Title', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 1, 1,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332',
        'PUBLISHED', TRUE),
       ('Another title', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 2, 2,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332',
        'PUBLISHED', FALSE),
       ('Third title', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 1, 2,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332',
        'PUBLISHED', NULL),
       ('Fourth title MODERATION_SECOND_SIGN', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 1, 2,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', NULL,
        'MODERATION_SECOND_SIGN', TRUE),
       ('Fifth title MODERATION_FIRST_SIGN', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 1, 2,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', NULL,
        'MODERATION_FIRST_SIGN', FALSE),
       ('Sixth title DRAFT', 'Preview', 'Content', NULL, 'https://i.imgur.com/1VU2fe5.png', 1, 1,
        '06.06.2021 03:56:37.700332', '06.06.2021 03:56:37.700332', NULL,
        'DRAFT', TRUE);


INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (1, 2),
       (2, 1),
       (2, 3),
       (3, 3),
       (3, 5),
       (3, 6),
       (4, 1),
       (4, 2),
       (4, 4),
       (5, 1),
       (5, 2),
       (5, 4),
       (6, 1),
       (6, 2),
       (6, 4);

INSERT INTO public.posts_origins(post_id, origin_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 3),
       (5, 1),
       (6, 3);

INSERT INTO public.posts_tags(post_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (2, 2),
       (4, 6),
       (4, 5);
