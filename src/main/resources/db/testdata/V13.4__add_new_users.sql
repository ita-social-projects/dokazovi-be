-- Adding UNICEF Ukraine into the "institutions" table. Primary_key with value #6 will be assigned to it.
-- Institution_id with value #10 will be assigned to it.
INSERT INTO public.institutions (name, address, city_id)
VALUES ('UNICEF Ukraine', 'вул. Інститутська, 28', 190);
-- UNICEF Ukraine was added

-- Adding the 1th author. User_id with value #59 and Doctor_id with value #55 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('kateryna.bulavinova@gmail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE',
        'Катерина', 'Булавінова', '+380939393939', '2021-02-16 03:56:37.444571', 'https://i.imgur.com/k0j4vVH.png', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'kateryna.bulavinova@gmail.com', '59', 59);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Інфекціоніст', 'медичний експерт ЮНІСЕФ в Україні',59, 10, 1, 0,
        0, 0, 'facebook.com/kateryna.bulavinova');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (55, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (55, 1), (55, 4), (55, 7);
-- The first author was added

-- Adding the 2nd author. User_id with value #60 and Doctor_id with value #56 will be assigned to him.
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('unicef.vaccination@gmail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE',
        'UNICEF', 'Ukraine', '+380939393939', '2021-02-16 03:56:37.444571', 'https://i.imgur.com/OV1iQAD.png', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'unicef.vaccination@gmail.com', '60', 60);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating, social_network)
VALUES ('Інфекціоніст', 'Представництво Дитячого фонду ООН в Україні',60, 10, 1, 0,
        0, 0, 'https://www.facebook.com/UNICEFUkraine/');
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (56, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (56, 1), (56, 4), (56, 7);
-- The 2nd author was added