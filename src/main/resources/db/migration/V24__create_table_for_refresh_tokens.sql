create table REFRESHTOKEN(
    REFRESHTOKEN_ID serial not null
                    constraint REFRESHTOKEN_PKEY
                        primary key,
    EXPIRY_DATE     timestamp not null,
    TOKEN           varchar(255) not null,
    USER_ID         integer
                    constraint REFRESHTOKEN_USER_ID_FKEY
                        references USERS
);