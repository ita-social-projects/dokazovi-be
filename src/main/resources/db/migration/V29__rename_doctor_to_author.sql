ALTER TABLE doctors
    RENAME TO authors;

ALTER TABLE authors
    RENAME COLUMN doctor_id TO author_id;

ALTER TABLE directions
    RENAME COLUMN has_doctors TO has_authors;

ALTER TABLE doctor_post_directions
    RENAME TO author_post_directions;

ALTER TABLE author_post_directions
    RENAME COLUMN doctor_id TO author_id;

ALTER TABLE doctors_directions
    RENAME TO authors_directions;

ALTER TABLE authors_directions
    RENAME COLUMN doctor_id TO author_id;

ALTER TABLE doctors_institutions
    RENAME TO authors_institutions;

ALTER TABLE authors_institutions
    RENAME COLUMN doctor_id TO author_id;

ALTER TABLE author_post_directions
    RENAME CONSTRAINT doctor_fk TO author_fk;

ALTER TABLE authors
    RENAME CONSTRAINT doctors_institution_id_fkey TO authors_institution_id_fkey;

ALTER TABLE authors
    RENAME CONSTRAINT doctors_user_id_fkey TO authors_user_id_fkey;

ALTER TABLE authors_directions
    RENAME CONSTRAINT doctors_directions_direction_id_fkey TO authors_directions_direction_id_fkey;

ALTER TABLE authors_directions
    RENAME CONSTRAINT doctors_directions_doctor_id_fkey TO authors_directions_author_id_fkey;

ALTER TABLE authors_institutions
    RENAME CONSTRAINT doctors_institutions_doctor_id_fkey TO authors_institutions_author_id_fkey;

ALTER TABLE authors_institutions
    RENAME CONSTRAINT doctors_institutions_institution_id_fkey TO authors_institutions_institution_id_fkey;

ALTER TABLE author_post_directions
    RENAME CONSTRAINT doctor_post_directions_distinct TO author_post_directions_distinct;

ALTER TABLE authors
    RENAME CONSTRAINT doctors_pkey TO authors_pkey;

