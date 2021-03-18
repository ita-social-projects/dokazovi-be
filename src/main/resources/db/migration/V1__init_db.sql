CREATE TABLE ROLES
(
    ROLE_ID   SERIAL NOT NULL
        CONSTRAINT ROLES_PKEY
            PRIMARY KEY,
    ROLE_NAME VARCHAR
);

CREATE TABLE USERS
(
    USER_ID    SERIAL NOT NULL
        CONSTRAINT USERS_PKEY
            PRIMARY KEY,
    EMAIL      VARCHAR,
    PASSWORD   VARCHAR,
    STATUS     VARCHAR,
    FIRST_NAME VARCHAR,
    LAST_NAME  VARCHAR,
    PHONE      VARCHAR,
    CREATED_AT TIMESTAMP DEFAULT NOW(),
    AVATAR     VARCHAR,
    ENABLED    BOOLEAN,
    ROLE_ID    INTEGER
        CONSTRAINT USERS_ROLE_ID_FKEY
            REFERENCES ROLES
);

CREATE TABLE REGIONS
(
    REGION_ID SERIAL NOT NULL
        CONSTRAINT REGIONS_PKEY
            PRIMARY KEY,
    NAME      VARCHAR
);

CREATE TABLE TAGS
(
    TAG_ID SERIAL NOT NULL
        CONSTRAINT TAGS_PKEY
            PRIMARY KEY,
    TAG    VARCHAR
        CONSTRAINT UNIQUE_NAME
            UNIQUE
);


CREATE TABLE CHARITIES
(
    CHARITY_ID  SERIAL NOT NULL
        CONSTRAINT CHARITIES_PKEY
            PRIMARY KEY,
    BODY        TEXT,
    AUTHOR_ID   INTEGER
        CONSTRAINT CHARITIES_AUTHOR_ID_FKEY
            REFERENCES USERS,
    CREATED_AT  TIMESTAMP DEFAULT NOW(),
    MODIFIED_AT TIMESTAMP DEFAULT NOW()
);


CREATE TABLE SOURCES
(
    SOURCE_ID SERIAL NOT NULL
        CONSTRAINT SOURCES_PKEY
            PRIMARY KEY,
    TYPE      VARCHAR,
    VALUE     VARCHAR,
    USER_ID   INTEGER
        CONSTRAINT SOURCES_USER_ID_FKEY
            REFERENCES USERS
);

CREATE TABLE DIRECTIONS
(
    DIRECTION_ID SERIAL NOT NULL
        CONSTRAINT DIRECTIONS_PKEY
            PRIMARY KEY,
    LABEL        VARCHAR,
    COLOR        VARCHAR(7) DEFAULT NULL::CHARACTER VARYING,
    NAME         VARCHAR
);

CREATE TABLE POST_TYPES
(
    TYPE_ID SERIAL NOT NULL
        CONSTRAINT POST_TYPES_PKEY
            PRIMARY KEY,
    NAME    VARCHAR
);

CREATE TABLE POSTS
(
    POST_ID     SERIAL NOT NULL
        CONSTRAINT POSTS_PKEY
            PRIMARY KEY,
    AUTHOR_ID   INTEGER
        CONSTRAINT POSTS_AUTHOR_ID_FKEY
            REFERENCES USERS,
    TYPE_ID     INTEGER
        CONSTRAINT POSTS_TYPE_ID_FKEY
            REFERENCES POST_TYPES,
    TITLE       VARCHAR,
    CONTENT     TEXT,
    STATUS      VARCHAR,
    IMPORTANT   BOOLEAN,
    CREATED_AT  TIMESTAMP DEFAULT NOW(),
    MODIFIED_AT TIMESTAMP DEFAULT NOW(),
    PREVIEW     TEXT
);

CREATE TABLE POSTS_TAGS
(
    POST_ID INTEGER
        CONSTRAINT POSTS_TAGS_POST_ID_FKEY
            REFERENCES POSTS,
    TAG_ID  INTEGER
        CONSTRAINT POSTS_TAGS_TAG_ID_FKEY
            REFERENCES TAGS
);

CREATE TABLE POSTS_SOURCES
(
    POST_ID   INTEGER
        CONSTRAINT POSTS_SOURCES_POST_ID_FKEY
            REFERENCES POSTS,
    SOURCE_ID INTEGER
        CONSTRAINT POSTS_SOURCES_SOURCE_ID_FKEY
            REFERENCES SOURCES
);

CREATE TABLE POSTS_DIRECTIONS
(
    POST_ID      INTEGER
        CONSTRAINT POSTS_DIRECTIONS_POST_ID_FKEY
            REFERENCES POSTS,
    DIRECTION_ID INTEGER
        CONSTRAINT POSTS_DIRECTIONS_DIRECTION_ID_FKEY
            REFERENCES DIRECTIONS
);

CREATE TABLE CITIES
(
    CITY_ID   INTEGER GENERATED BY DEFAULT AS IDENTITY
        CONSTRAINT CITIES_PKEY
            PRIMARY KEY,
    NAME      VARCHAR(255),
    REGION_ID INTEGER
        CONSTRAINT CITIES_REGION_ID_FKEY
            REFERENCES REGIONS
);

CREATE TABLE INSTITUTIONS
(
    INSTITUTION_ID SERIAL NOT NULL
        CONSTRAINT INSTITUTIONS_PKEY
            PRIMARY KEY,
    NAME           VARCHAR,
    ADDRESS        VARCHAR,
    CITY_ID        INTEGER
        CONSTRAINT INSTITUTIONS_CITY_ID_FKEY
            REFERENCES CITIES
);

CREATE TABLE VERIFICATION_TOKENS
(
    ID      SERIAL NOT NULL
        CONSTRAINT VERIFICATION_TOKENS_PKEY
            PRIMARY KEY,
    TOKEN   VARCHAR,
    USER_ID INTEGER
        CONSTRAINT VERIFICATION_TOKENS_USER_ID_FKEY
            REFERENCES USERS
);

CREATE TABLE PROVIDERS
(
    PROVIDER_ID         SERIAL NOT NULL
        CONSTRAINT PROVIDERS_PKEY
            PRIMARY KEY,
    PROVIDER_NAME       VARCHAR,
    EMAIL               VARCHAR,
    USER_ID_BY_PROVIDER VARCHAR,
    USER_ID             INTEGER
        CONSTRAINT PROVIDERS_USER_ID_FKEY
            REFERENCES USERS
);

CREATE TABLE DOCTORS
(
    DOCTOR_ID       SERIAL NOT NULL
        CONSTRAINT DOCTORS_PKEY
            PRIMARY KEY,
    QUALIFICATION   VARCHAR,
    BIO             VARCHAR,
    USER_ID         INTEGER
        CONSTRAINT DOCTORS_USER_ID_FKEY
            REFERENCES USERS,
    INSTITUTION_ID  INTEGER
        CONSTRAINT DOCTORS_INSTITUTION_ID_FKEY
            REFERENCES INSTITUTIONS,
    PROMOTION_SCALE REAL    DEFAULT 1.0,
    PROMOTION_LEVEL INTEGER DEFAULT 0,
    PUBLISHED_POSTS BIGINT  DEFAULT 0,
    RATING          BIGINT  DEFAULT 0
);

CREATE TABLE DOCTORS_INSTITUTIONS
(
    DOCTOR_ID      INTEGER
        CONSTRAINT DOCTORS_INSTITUTIONS_DOCTOR_ID_FKEY
            REFERENCES DOCTORS,
    INSTITUTION_ID INTEGER
        CONSTRAINT DOCTORS_INSTITUTIONS_INSTITUTION_ID_FKEY
            REFERENCES INSTITUTIONS
);

CREATE TABLE DOCTORS_DIRECTIONS
(
    DOCTOR_ID    INTEGER
        CONSTRAINT DOCTORS_DIRECTIONS_DOCTOR_ID_FKEY
            REFERENCES DOCTORS,
    DIRECTION_ID INTEGER
        CONSTRAINT DOCTORS_DIRECTIONS_DIRECTION_ID_FKEY
            REFERENCES DIRECTIONS
);

CREATE TABLE ROLE_PERMISSION
(
    ROLE_ID     INTEGER
        CONSTRAINT ROLE_ID_ROLE_ID_FK
            REFERENCES ROLES,
    PERMISSIONS VARCHAR
);
