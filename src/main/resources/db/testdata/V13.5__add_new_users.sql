-- Adding a new institution located in Chernihiv, Chernihiv oblast. Primary_key with value #11 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Чернігівська міська лікарня', 'вул. 1 Травня, 161', 437);
-- The institution in Chernihiv was added

-- Adding 6 new users (authors) with the "Doctor" role from Chernihiv oblast
-- Adding the 1st user. User_id with value #61 and Doctor_id with value #57 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('kimkwan@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Kim', 'Kwan', '+380637144441', '2021-02-16 03:56:31.444571', 'https://i.pravatar.cc/300?img=1', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'kimkwan@mail.com', '61', 61);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Главрач',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        61, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (57, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (57, 1);
-- The 1st author was added

-- Adding the 2nd user. User_id with value #62 and Doctor_id with value #58 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('cixitaihou@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Cixi', 'Taihou', '+380637244441', '2021-02-16 03:56:32.444571', 'https://i.pravatar.cc/300?img=2', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'cixitaihou@mail.com', '62', 62);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Замглаврача',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        62, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (58, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (58, 2);
-- The 2nd author was added

-- Adding the 3rd user. User_id with value #63 and Doctor_id with value #59 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('chiangkaishi@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Chiang', 'Kaishi', '+380637344441', '2021-02-16 03:56:33.444571', 'https://i.pravatar.cc/300?img=3', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'chiangkaishi@mail.com', '63', 63);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Хірург',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        63, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (59, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (59, 3);
-- The 3rd author was added

-- Adding the 4th user. User_id with value #64 and Doctor_id with value #60 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('yojiyamamoto@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Yoji', 'Yamamoto', '+380637444441', '2021-02-16 03:56:34.444571', 'https://i.pravatar.cc/300?img=4', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'yojiyamamoto@mail.com', '64', 64);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Стоматолог',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        64, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (60, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (60, 4);
-- The 4th author was added

-- Adding the 5th user. User_id with value #65 and Doctor_id with value #61 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('togoakira@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Akira', 'Togo', '+380637544441', '2021-02-16 03:56:35.444571', 'https://i.pravatar.cc/300?img=5', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'togoakira@mail.com', '65', 65);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Педіатр',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        65, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (61, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (61, 5);
-- The 5th author was added

-- Adding the 6th user. User_id with value #66 and Doctor_id with value #62 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('takedakano@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Kano', 'Takeda', '+380637644441', '2021-02-16 03:56:35.444571', 'https://i.pravatar.cc/300?img=6', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'takedakano@mail.com', '66', 66);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Терапевт',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        66, 11, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (62, 11);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (62, 6);
-- The 6th author was added
-- 6 new users (authors) with the "Doctor" role from Chernihiv oblast were added