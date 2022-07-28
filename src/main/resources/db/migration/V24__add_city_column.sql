alter table dokazovi.public.doctors
add column CITY_ID INTEGER
    CONSTRAINT DOCTORS_CITIES_ID_FKEY
        REFERENCES CITIES;
