alter table users
    add column MODIFIED_AT timestamp not null default now() ;
