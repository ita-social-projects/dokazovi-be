UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=19'
WHERE user_id = 16;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=14'
WHERE user_id = 12;

UPDATE public.users
SET avatar = null
WHERE user_id = 9;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=10'
WHERE user_id = 7;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=9'
WHERE user_id = 5;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=3'
WHERE user_id = 1;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=13'
WHERE user_id = 11;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=5'
WHERE user_id = 8;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=33'
WHERE user_id = 19;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=56'
WHERE user_id = 26;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=16'
WHERE user_id = 13;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=12'
WHERE user_id = 10;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=55'
WHERE user_id = 25;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=18'
WHERE user_id = 17;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=52'
WHERE user_id = 20;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=15'
WHERE user_id = 14;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=51'
WHERE user_id = 21;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=11'
WHERE user_id = 6;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=20'
WHERE user_id = 18;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=54'
WHERE user_id = 23;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=27'
WHERE user_id = 24;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=1'
WHERE user_id = 3;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=17'
WHERE user_id = 15;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=57'
WHERE user_id = 27;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=8'
WHERE user_id = 4;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=53'
WHERE user_id = 22;

UPDATE public.users
SET avatar = 'https://i.pravatar.cc/300?img=7'
WHERE user_id = 2;

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (10, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (11, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (12, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (13, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (14, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (15, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (16, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (17, 4);

INSERT INTO public.users_directions (user_id, direction_id)
VALUES (18, 4);

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'First therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-06 11:59:53.000000', '2020-12-06 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Second therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-07 11:59:53.000000', '2020-12-07 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Third therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-08 11:59:53.000000', '2020-12-08 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Fourth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-09 11:59:53.000000', '2020-12-09 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Fifth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-10 11:59:53.000000', '2020-12-10 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Sixth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-11 11:59:53.000000', '2020-12-11 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Seventh therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-12 11:59:53.000000', '2020-12-12 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Eighth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-13 11:59:53.000000', '2020-12-13 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Ninth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-14 11:59:53.000000', '2020-12-14 11:59:53.000000');

INSERT INTO public.posts (post_id, author_id, direction_id, type_id, title, content, status, important, created_at,
                          modified_at)
VALUES (DEFAULT, 10, 4, 1, 'Tenth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-15 11:59:53.000000', '2020-12-15 11:59:53.000000');

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'ShtukaNovitskaya261@mail.com', 'FXV1l9vjhNl9', 'ACTIVE', 'Щука', 'Новицкая', 'Кандидат медичних наук',
        '+380632405186',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=26', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'FadiStalin333@mail.com', 'O5Y63BXGSrlQ', 'ACTIVE', 'Фади', 'Сталин', 'Кандидат медичних наук',
        '+380636962951',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=58', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'PamfiyTihomirov752@mail.com', 'MiYzEtTKvA4S', 'ACTIVE', 'Памфий', 'Тихомиров',
        'Кандидат медичних наук', '+380635701294',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=59', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'FekletinyaShashkova782@mail.com', '2Y8NjPD2P7gq', 'ACTIVE', 'Феклетинья', 'Шашкова',
        'Кандидат медичних наук', '+380639220084',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=48', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'PalanaLitvinova514@mail.com', 'okEJno95BedG', 'ACTIVE', 'Палана', 'Литвинова',
        'Кандидат медичних наук', '+380633484351',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=44', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'GigranAlekseev32@mail.com', 'BzSvSAukYNp0', 'ACTIVE', 'Гигран', 'Алексеев', 'Лікар-спеціаліст',
        '+380634724237',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=65', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'KukuriChapko624@mail.com', 'k8qMzT5eU3CG', 'ACTIVE', 'Кукури', 'Чапко', 'Лікар-спеціаліст',
        '+380631793743',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=66', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'NezdaUshakov899@mail.com', 'JRX2J9JNwTsQ', 'ACTIVE', 'Незда', 'Ушаков', 'Лікар вищої категорії',
        '+380637687420',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=67', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'MiletiyChernyshov229@mail.com', 'lTphvTDi40Sh', 'ACTIVE', 'Милетий', 'Чернышов',
        'Лікар вищої категорії', '+380637931441',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=68', 4, 5);

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, qualification, phone, bio,
                          created_at, avatar, direction_id, institution_id)
VALUES (DEFAULT, 'TarzhemanSokolov585@mail.com', 'fH63ikLlKB58', 'ACTIVE', 'Таржеман', 'Соколов',
        'Лікар вищої категорії', '+380631434712',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        DEFAULT, 'https://i.pravatar.cc/300?img=70', 4, 5);