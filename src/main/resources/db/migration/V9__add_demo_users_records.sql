INSERT INTO PUBLIC.USERS (EMAIL, PASSWORD, STATUS, FIRST_NAME, LAST_NAME, QUALIFICATION, PHONE, BIO,
                          CREATED_AT, AVATAR, DIRECTION_ID, INSTITUTION_ID)
VALUES
('eva@mail.com', '$2y$10$9whP9V3MfAs/G9imeF9g4e6UMVotHZzp6cqo9bMleSNXNbwPESbm.', 'ACTIVE', 'Єва', 'Євенко', 'Лікар вищої категорії', '+380969123969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
    '1', '5'),

('zahar@mail.com', '$2y$10$hVu0ysEa3BNNi4ZAzcvMxe2A0qb5szLLM2Md/XltMIg54jrVbJ1Ay', 'ACTIVE', 'Захар', 'Захаренко', 'Лікар-спеціаліст', '+380961233969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '2'),

('nazar@mail.com', '$2y$10$4jMtf0eEjVaV0bJWLEb2OOpzEI8md27557Ei/z9eVqugk4Ueg3LLm', 'ACTIVE', 'Назар', 'Назаренко', 'Лікар-спеціаліст', '+380961456969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '3'),

('zhan@mail.com', '$2y$10$Dx6cNJ3AGW9xzin3xYLpKuQbDO85WM7zy8t.CIJ7JZJFUY3R.8UKe', 'ACTIVE', 'Жан', 'Жанко', 'Кандидат медичних наук', '+380956456969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '1'),

('nikon@mail.com', '$2y$10$EdUEHquCl7OyAqTtfoT1suL1QYZ/68yH36TNWOFnFj/gU.9TjvZAS', 'ACTIVE', 'Нікон', 'Ніконенко', 'Кандидат медичних наук', '+380956412369'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '5'),

('matviy@mail.com', '$2y$10$dGfbNZAFW5vAkr50kTJBEO3rUDWJuj8B4dfFkmOS/kBlnGqiKqLHS', 'ACTIVE', 'Матвій', 'Матвійчук', 'Лікар-спеціаліст', '+380956098369'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '2'),

('regina@mail.com', '$2y$10$SxF6UMIS1cn7Wi76GwMBFedgGukB9pe3XeL8Qz9pRfUNw3BtD.CDG', 'ACTIVE', 'Регіна', 'Регіненко', 'Лікар вищої категорії', '+380956765669'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '2'),

('fedot@mail.com', '$2y$10$.oKUlohR31I8wni/Qi8nwu9cJti4P5ddg6oq6FIK4H7r/jmmK44sG', 'ACTIVE', 'Федот', 'Федотенко', 'Лікар вищої категорії', '+380956761119'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '1'),

('terentiy@mail.com', '$2y$10$NhzfYhZemyRhR61aPJmtnO1WFv06y2ENyEctQCePx.Ymn6wL6P2h.', 'ACTIVE', 'Терентій', 'Терентієнко', 'Лікар вищої категорії', '+380956761119'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '1'),

('platon@mail.com', '$2y$10$pZm26FgFAgGbMna/ut0vu.jtR0PZI9kRWCbF.6HshU8wQ32/rMpH2', 'ACTIVE', 'Платон', 'Платоненко', 'Лікар вищої категорії', '+380956761119'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '1');