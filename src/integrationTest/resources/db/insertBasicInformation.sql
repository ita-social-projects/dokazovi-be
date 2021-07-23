INSERT INTO public.tags (tag)
VALUES ('Профілактика');
INSERT INTO public.tags (tag)
VALUES ('Комплекс');
INSERT INTO public.tags (tag)
VALUES ('Ковід');
INSERT INTO public.tags (tag)
VALUES ('Допомога');
INSERT INTO public.tags (tag)
VALUES ('Міокард');
INSERT INTO public.tags (tag)
VALUES ('ЕКГ');

INSERT INTO public.origins (name, parameters)
VALUES ('Думка експерта', null);
INSERT INTO public.origins (name, parameters)
VALUES ('Медитека', null);
INSERT INTO public.origins (name, parameters)
VALUES ('Переклад', null);

INSERT INTO public.post_types (name)
VALUES ('Стаття');
INSERT INTO public.post_types (name)
VALUES ('Відео');
INSERT INTO public.post_types (name)
VALUES ('Допис');
INSERT INTO public.post_types (name)
VALUES ('Переклад');

INSERT INTO public.roles (role_name)
VALUES ('Administrator');
INSERT INTO public.roles (role_name)
VALUES ('Moderator');
INSERT INTO public.roles (role_name)
VALUES ('Doctor');

INSERT INTO public.directions (label, color, name)
VALUES ('Covid-19', '#ef5350', 'covid-19');
INSERT INTO public.directions (label, color, name)
VALUES ('Офтальмологія', '#98ef50', 'ophthalmology');
INSERT INTO public.directions (label, color, name)
VALUES ('Хірургія', '#7aebbf', 'surgery');
INSERT INTO public.directions (label, color, name)
VALUES ('Терапія', '#ffee58', 'therapy');
INSERT INTO public.directions (label, color, name)
VALUES ('Вірусологія', '#da80e8', 'virology');
INSERT INTO public.directions (label, color, name)
VALUES ('Кардіологія', '#00ffff', 'cardiology');
INSERT INTO public.directions (label, color, name)
VALUES ('Педіатрія', '#993333', 'pediatrics');

INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_TAG');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'SAVE_TAG');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'SAVE_TAG');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'DELETE_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'DELETE_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'DELETE_OWN_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'UPDATE_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'UPDATE_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'UPDATE_OWN_POST');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SET_IMPORTANCE');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_PLATFORM_INFORMATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'UPDATE_PLATFORM_INFORMATION');

INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('ivan@mail.com', '$2y$10$ishgf6hBdlEQwE8Ld1ktkOOPsINMgE7CviFi1qxRaiOgvUdg3RCTy', 'ACTIVE', 'Іван', 'Іванов',
        '+380969696969', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=3', true, 3),
       ('fedot@mail.com', '$2y$10$.oKUlohR31I8wni/Qi8nwu9cJti4P5ddg6oq6FIK4H7r/jmmK44sG', 'ACTIVE', 'Федот',
        'Федотенко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=55', true, 3),
       ('admin@mail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE', 'Gregory',
        'House', '+380939393939', '2021-02-16 03:56:37.332925', null, true, 1);


INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'ivan@mail.com', '1', 1),
       ('LOCAL', 'fedot@mail.com', '2', 2),
       ('LOCAL', 'admin@mail.com', '3', 3);
