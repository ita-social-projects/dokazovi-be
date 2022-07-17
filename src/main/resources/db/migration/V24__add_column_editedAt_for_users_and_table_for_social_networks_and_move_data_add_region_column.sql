alter table public.users
    add column EDITED_AT timestamp;

create table if not exists USERS_SOCIAL_NETWORKS(
    user_id integer not null
    constraint social_networks_user_id_fkey
        references users,
    link varchar
);

insert into USERS_SOCIAL_NETWORKS (user_id, link)
select user_id, social_network from DOCTORS;

alter table public.doctors
    drop column social_network;

alter table public.users
    add column REGION varchar;