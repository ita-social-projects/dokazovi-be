create table if not exists SOCIAL_NETWORKS(
    user_id integer not null
            constraint social_networks_user_id_fkey
            references users,
    link varchar
);

insert into SOCIAL_NETWORKS (user_id, link)
select user_id, social_network from DOCTORS;

alter table public.doctors
drop column social_network;