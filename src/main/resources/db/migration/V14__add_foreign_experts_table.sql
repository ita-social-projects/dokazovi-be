CREATE TABLE FOREIGN_EXPERTS
(
    FOREIGN_EXPERT_ID SERIAL NOT NULL
        CONSTRAINT FOREIGN_EXPERTS_PKEY
             PRIMARY KEY,

    FULL_NAME VARCHAR,
    BIO VARCHAR
);

-- This index is used to speed up simple `LIKE 'someSearchPrefix%' queries.

CREATE INDEX FOREIGN_EXPERTS_FULL_NAME_LIKE_IDX
    ON FOREIGN_EXPERTS(FULL_NAME text_pattern_ops);

-- Here we have to add a Ukrainian fts dictionary file to make the index work properly.
-- Making it work will allow proper full text search.
-- Yes, Ukrainian fts is not supported out of the box in postgres.

-- CREATE INDEX FOREIGN_EXPERTS_FULL_NAME_FTS_IDX
--     ON FOREIGN_EXPERTS
--     USING GIN (to_tsvector('ukrainian', FULL_NAME));