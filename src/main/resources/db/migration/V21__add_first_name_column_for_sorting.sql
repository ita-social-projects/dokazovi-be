alter table public.posts
    add column first_name varchar;

update public.posts set first_name = (select first_name from users where posts.author_id = users.user_id);
