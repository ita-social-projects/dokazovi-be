-- A new administrator's appendage. Primary_key with value #39 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('admin1@mail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE', 'Lisa',
        'Cuddy', '+380938383838', '2021-02-16 03:56:37.332925', null, true, 1);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'admin1@mail.com', '39', 39);
-- The admin was added

-- Adding four new institutions
-- Adding a new institution located in Lviv oblast. Primary_key with value #6 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Львівська міська косметологічна лікарня', 'вул. Чорновола, 1Д', 236);
-- The institution in Lviv was added

-- Adding a new institution located in Kirovohrad oblast. Primary_key with value #7 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Центральна міська лікарня', 'вул. Паученка, 45', 221);
-- The institution in Kropyvnytskyi was added

-- Adding a new institution located in Luhansk oblast. Primary_key with value #8 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Поліклінічне об’єднання', 'вул. Металургів, 25', 233);
-- The institution in Luhansk was added

-- Adding a new institution located in Ivano-Frankivsk oblast. Primary_key with value #9 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Івано-Франківська обласна дитяча клінічна лікарня', 'вул. Коновальця, 132', 164);
-- The institution in Ivano-Frankivsk was added
-- The institutions were added

-- Adding 18 new users (authors) with the "Doctor" role from four regions (Lviv\Kirovohrad\Luhansk\Ivano-Frankivsk oblasts)
-- Adding a new author from Lviv oblast. User_id with value #40 and Doctor_id with value #37 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('helmuttkoll123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Гельмутт', 'Коль', '+380637944441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=69', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'helmuttkoll123@mail.com', '40', 40);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Фельдшер',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        40, 6, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (37, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (37, 2);
-- The author from Lviv oblast was added

-- Adding a new author from Kirovohrad oblast. User_id with value #41 and Doctor_id with value #38 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('anhelamerk123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Ангела', 'Мерк', '+380632224441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=22', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'anhelamerk123@mail.com', '41', 41);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Фельдшер',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        41, 7, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (38, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (38, 5);
-- The author from Kirovohrad oblast was added

-- Adding 12 authors from Ivano-Frankivsk oblast. All users're gonna work in the same institution with the "institution_id" #9.
-- Adding the 1st author from Ivano-Frankivsk oblast. User_id with value #42 and Doctor_id with value #39 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('yokohifume123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Йоко', 'Хіфуме', '+380632254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=23', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'yokohifume123@mail.com', '42', 42);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Главрач',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        42, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (39, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (39, 1);
-- The 1st author from Ivano-Frankivsk oblast was added

-- Adding the 2nd author from Ivano-Frankivsk oblast. User_id with value #43 and Doctor_id with value #40 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('karinafuku123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Karina', 'Fukuzava', '+380633254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=23', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'karinafuku123@mail.com', '43', 43);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Зам главрача',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        43, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (40, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (40, 4);
-- The 2nd author from Ivano-Frankivsk oblast was added

-- Adding the 3rd author from Ivano-Frankivsk oblast. User_id with value #44 and Doctor_id with value #41 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('karinafuku123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Karina', 'Fukuzawa', '+380634254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=24', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'karinafuku123@mail.com', '44', 44);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Зам главрача',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        44, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (41, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (41, 3);
-- The 3rd author from Ivano-Frankivsk oblast was added

-- Adding the 4th author from Ivano-Frankivsk oblast. User_id with value #45 and Doctor_id with value #42 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('yukichipetrenko123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Yukichi', 'Petrenko', '+380635254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=25', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'yukichipetrenko123@mail.com', '45', 45);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Рентгенолог',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        45, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (42, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (42, 6);
-- The 4th author from Ivano-Frankivsk oblast was added

-- Adding the 5th author from Ivano-Frankivsk oblast. User_id with value #46 and Doctor_id with value #43 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('ivankatanaka123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Ivanka', 'Tanaka', '+380636254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=26', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'ivankatanaka123@mail.com', '46', 46);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Кардіолог',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        46, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (43, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (43, 1);
-- The 5th author from Ivano-Frankivsk oblast was added

-- Adding the 6th author from Ivano-Frankivsk oblast. User_id with value #47 and Doctor_id with value #44 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('lesyatakuboku123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Lesya', 'Takuboku', '+380637254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=27', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'lesyatakuboku123@mail.com', '47', 47);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('ЛОР',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        47, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (44, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (44, 2);
-- The 6th author from Ivano-Frankivsk oblast was added

-- Adding the 7th author from Ivano-Frankivsk oblast. User_id with value #48 and Doctor_id with value #45 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('svitlanafukuyama132@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Svitlana', 'Fukuyama', '+380638254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=28', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'svitlanafukuyama132@mail.com', '48', 48);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Окуліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        48, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (45, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (45, 3);
-- The 7th author from Ivano-Frankivsk oblast was added

-- Adding the 8th author from Ivano-Frankivsk oblast. User_id with value #49 and Doctor_id with value #46 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('margotenshi132@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Margo', 'Tenshi', '+380639254441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=29', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'margotenshi132@mail.com', '49', 49);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Окуліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        49, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (46, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (46, 4);
-- The 8th author from Ivano-Frankivsk oblast was added

-- Adding the 9th author from Ivano-Frankivsk oblast. User_id with value #50 and Doctor_id with value #47 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('valentynalee132@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Valentyna', 'Lee', '+380631354441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=30', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'valentynalee132@mail.com', '50', 50);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Окуліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        50, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (47, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (47, 5);
-- The 9th author from Ivano-Frankivsk oblast was added

-- Adding the 10th author from Ivano-Frankivsk oblast. User_id with value #51 and Doctor_id with value #48 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('helenchan123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Helen', 'Chan', '+380631454441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=31', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'helenchan123@mail.com', '51', 51);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Окуліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        51, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (48, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (48, 1);
-- The 10th author from Ivano-Frankivsk oblast was added

-- Adding the 11th author from Ivano-Frankivsk oblast. User_id with value #52 and Doctor_id with value #49 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('parkgeun123@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Geun-hye', 'Park', '+380631554441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=32', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'parkgeun123@mail.com', '52', 52);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Окуліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        52, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (49, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (49, 2);
-- The 11th author from Ivano-Frankivsk oblast was added

-- Adding the 12th author from Ivano-Frankivsk oblast. User_id with value #53 and Doctor_id with value #50 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('soonsil132@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Soon-sil', 'Choi', '+380631654441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=19', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'soonsil132@mail.com', '53', 53);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Терапевт',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        53, 9, 1, 0, 0, 1, 'https://www.linkedin.com/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (50, 9);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (50, 3);
-- The 12th author from Ivano-Frankivsk oblast was added
-- All 12 authors  from Ivano-Frankivsk oblast were added


/* Adding 4 authors from Luhansk oblast. All of them are gonna be without contact info (e-mail, social_network),
so just authors are created without users -> author's field "user_id" is null, thus an author doesn't refer to a user and
a table "users" isn't populated with data as well as "providers".
NB - Doctor_ids will be present in the comments just in case of necessity to refactor code by adding a user
and further binding it with author. If a user is absent in the script section then just ignore "Doctor_id" part in the comment.
*/
-- Adding the 1st author from Luhansk oblast. User_id with value #54 and Doctor_id with value #51 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (null, '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Dong-wook', 'Kim', '+380631754441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=55', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', null, '54', 54);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Главрач',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        54, 8, 1, 0, 0, 1, null);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (51, 8);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (51, 1);
-- The 1st author from Luhansk oblast was added

-- Adding the 2nd author from Luhansk oblast. User_id with value #55 and Doctor_id with value #52 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (null, '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Sasha', 'Vong', '+380631754441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=56', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', null, '55', 55);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Зам главрача',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        55, 8, 1, 0, 0, 1, null);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (52, 8);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (52, 2);
-- The 2nd author from Luhansk oblast was added

-- Adding the 3rd author from Luhansk oblast. User_id with value #56 and Doctor_id with value #53 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (null, '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Bill', 'Yamamoto', '+380631854441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=57', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', null, '56', 56);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Фельдшер',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        56, 8, 1, 0, 0, 1, null);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (53, 8);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (53, 4);
-- The 3rd author from Luhansk oblast was added

-- Adding the 4th author from Luhansk oblast. User_id with value #57 and Doctor_id with value #54 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (null, '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Ryu', 'Yamashita', '+380631854441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=58', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', null, '57', 57);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Зам главрача',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        57, 8, 1, 0, 0, 1, null);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (54, 8);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (54, 3);
-- The 4th author from Luhansk oblast was added
-- 4 authors from Luhansk oblast were added.

-- Update table "regions"
UPDATE REGIONS
SET USERS_PRESENT= TRUE
WHERE REGION_ID IN (SELECT DISTINCT REGION_ID
                    FROM CITIES
                    WHERE CITY_ID IN (SELECT DISTINCT CITY_ID
                                      FROM INSTITUTIONS
                                      WHERE INSTITUTION_ID IN (SELECT DISTINCT INSTITUTION_ID FROM DOCTORS)))
-- The table "regions" was updated