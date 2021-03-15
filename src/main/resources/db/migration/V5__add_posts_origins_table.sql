create table posts_origins
(
    post_id   integer
        constraint posts_origins_post_id_fkey
            references posts,
    origin_id integer
        constraint posts_origins_origin_id_fkey
            references origins
);

alter table posts_origins
    owner to postgres;


