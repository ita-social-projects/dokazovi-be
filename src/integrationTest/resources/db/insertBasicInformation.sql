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
