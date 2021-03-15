create table origins
(
    origin_id  serial not null
        constraint origins_pkey
            primary key,
    name       varchar,
    parameters varchar
);

alter table origins
    owner to postgres;


