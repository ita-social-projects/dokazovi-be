delete from posts_tags where post_id in (select post_id from posts where type_id=4);
delete from posts_directions where post_id in (select post_id from posts where type_id=4);
delete from posts_origins where post_id in (select post_id from posts where type_id=4);
delete from posts where type_id=4;
delete from post_types where type_id=4;

