CREATE TABLE ORIGINS
(
    ORIGIN_ID  SERIAL NOT NULL
        CONSTRAINT ORIGINS_PKEY
            PRIMARY KEY,
    NAME       VARCHAR,
    PARAMETERS VARCHAR
);

