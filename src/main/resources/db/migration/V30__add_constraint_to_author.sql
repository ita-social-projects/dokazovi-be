ALTER TABLE authors
    ADD COLUMN city_id INTEGER NOT NULL DEFAULT 190
    CONSTRAINT authors_city_id_fkey REFERENCES cities (city_id);

ALTER TABLE authors
    ADD COLUMN main_working_place VARCHAR