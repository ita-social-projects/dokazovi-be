drop table users_sources;

alter table if exists sources add column user_id int4;
alter table if exists sources add constraint sources_user_id_fkey foreign key (user_id) references users;