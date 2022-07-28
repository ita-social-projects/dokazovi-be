alter table dokazovi.public.doctors
add column CITY_ID INTEGER
    CONSTRAINT DOCTORS_CITIES_ID_FKEY
        REFERENCES CITIES;

alter table users
add column MODIFIED_AT timestamp not null default now() ;

update doctors
set city_id = 1;
