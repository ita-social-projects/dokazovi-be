drop table roles_users;

alter table users
    add role_id int;

alter table users
    add constraint users_role_id_fkey
        foreign key (role_id) references roles;

create table role_permission
(
    role_id int
        constraint role_id_role_id_fk
            references roles,
    permissions varchar
);

UPDATE public.roles
SET role_name = 'Administrator'
WHERE role_id = 1;

UPDATE public.roles
SET role_name = 'Moderator'
WHERE role_id = 2;

UPDATE public.roles
SET role_name = 'Doctor'
WHERE "role_id" = 3;

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

