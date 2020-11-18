ALTER TABLE PUBLIC.USERS ADD DIRECTION_ID INT;

ALTER TABLE PUBLIC.USERS ADD INSTITUTION_ID INT;

ALTER TABLE PUBLIC.USERS_INSTITUTIONS DROP COLUMN IS_PRIMARY;

ALTER TABLE PUBLIC.USERS ADD FOREIGN KEY (DIRECTION_ID) REFERENCES PUBLIC.DIRECTIONS (DIRECTION_ID);

ALTER TABLE PUBLIC.USERS ADD FOREIGN KEY (INSTITUTION_ID) REFERENCES PUBLIC.INSTITUTIONS (INSTITUTION_ID);

ALTER TABLE PUBLIC.POSTS ADD FOREIGN KEY (DIRECTION_ID) REFERENCES PUBLIC.DIRECTIONS (DIRECTION_ID);
