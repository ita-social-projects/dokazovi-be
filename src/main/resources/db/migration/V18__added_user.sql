INSERT INTO PUBLIC.USERS (EMAIL, PASSWORD, STATUS, FIRST_NAME, LAST_NAME, QUALIFICATION, PHONE, BIO,
                          CREATED_AT, AVATAR, DIRECTION_ID, INSTITUTION_ID,ENABLED)
VALUES('qwerty@gmail.com', '$2a$10$X4EQn5nF69c7Jo8fUI/d3eBYB7aPtdogcolEGwVSYcvcG9kgk5GSO', 'ACTIVE', 'Іван', 'Іванов', 'Лікар вищої категорії', '+380969696969'
          , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
            'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
            'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
            'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
       NOW(), 'https://i.imgur.com/I80W1Q0.png', '3', '1',true);

update users set enabled=true;
