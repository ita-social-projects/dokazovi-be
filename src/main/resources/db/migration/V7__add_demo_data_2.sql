ALTER TABLE PUBLIC.POSTS DROP COLUMN TAGS;

DELETE FROM PUBLIC.ROLES_USERS;

DELETE FROM PUBLIC.ROLES;

INSERT INTO PUBLIC.ROLES (ROLE_NAME) VALUES
('Admin'), ('Doctor'), ('Trusted Doctor');

INSERT INTO PUBLIC.ROLES_USERS (ROLE_ID, USER_ID) VALUES
('1', '9'), ('2', '4'), ('3', '2'), ('2', '1'), ('3', '3'), ('2', '5'), ('3', '7'), ('2', '6'), ('3', '8');

INSERT INTO PUBLIC.USERS (EMAIL, PASSWORD, STATUS, FIRST_NAME, LAST_NAME, QUALIFICATION, PHONE, BIO,
                          CREATED_AT, AVATAR, DIRECTION_ID, INSTITUTION_ID)
VALUES
('stepan@mail.com', 'stepan', 'ACTIVE', 'Степан', 'Степанов', 'Лікар вищої категорії', '+380969123969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
    '5', '5'),

('danylo@mail.com', 'danylo', 'ACTIVE', 'Данило', 'Даниленко', 'Лікар-спеціаліст', '+380961233969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '2'),

('petro@mail.com', 'petro', 'ACTIVE', 'Петро', 'Петренко', 'Лікар-спеціаліст', '+380961456969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '2', '3'),

('masha@mail.com', 'masha', 'ACTIVE', 'Марія', 'Марієнко', 'Кандидат медичних наук', '+380956456969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '4', '1'),

('mykola@mail.com', 'mykola', 'ACTIVE', 'Микола', 'Миколенко', 'Кандидат медичних наук', '+380956412369'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '4', '5'),

('taras@mail.com', 'taras', 'ACTIVE', 'Тарас', 'Шевченко', 'Лікар-спеціаліст', '+380956098369'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '3', '2'),

('lesia@mail.com', 'lesia', 'ACTIVE', 'Леся', 'Українка', 'Лікар вищої категорії', '+380956765669'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '2', '2'),

('bohdan@mail.com', 'bohdan', 'ACTIVE', 'Богдан', 'Хмельницький', 'Лікар вищої категорії', '+380956761119'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cdn.humanitycrew.org/wp-content/uploads/media/2019/06/113634857-person-gray-photo-placeholder-man-in-t-shirt-on-gray-background.jpg',
 '1', '1');



INSERT INTO PUBLIC.POSTS (CONTENT, CREATED_AT, IMPORTANT, MODIFIED_AT, STATUS, TITLE, AUTHOR_ID, DIRECTION_ID, TYPE_ID)
VALUES

('Aenean non lobortis arcu. Sed ac enim in metus tincidunt vestibulum ut feugiat purus. Fusce eu velit auctor, facilisis ' ||
 'urna sit amet, venenatis magna. Mauris lacinia ultricies pharetra. Phasellus vulputate sodales nulla, quis maximus risus' ||
 ' pulvinar viverra. Cras et erat id tellus congue rhoncus nec nec ipsum. Mauris ultrices, neque quis lobortis hendrerit,' ||
 ' turpis ipsum porttitor tortor, eget dignissim ante sapien sed sapien. Maecenas quis purus rutrum, tempor neque ut, imperdiet' ||
 ' enim. Aenean bibendum dui ac ligula ultricies, vitae pulvinar massa tincidunt.', '2019-11-25 13:42:34.609474', FALSE,
 '2019-11-26 13:42:34.609474', 'PUBLISHED', 'Fermentum iaculis', '2', '3', '1'),

('Duis lacus nisl, dictum at auctor et, dignissim ac diam. Morbi laoreet sit amet ante quis vehicula. Proin sodales sem ac lacus' ||
 ' sodales, id pellentesque nibh tempus. Phasellus porttitor, leo ac pulvinar malesuada, est mauris dignissim nibh, ac interdum' ||
 ' enim lorem quis justo. Nam porttitor vitae odio ut posuere. In hac habitasse platea dictumst. Interdum et malesuada fames ac' ||
 ' ante ipsum primis in faucibus. Etiam ultricies lacus in dapibus egestas. Quisque at neque condimentum, auctor ante consectetur,' ||
 ' ullamcorper ex. Cras dolor diam, imperdiet sit amet dui eu, ultricies dapibus velit. Duis odio dolor, sagittis a libero sit amet,' ||
 ' tincidunt gravida quam. Nunc congue ultrices tortor vitae hendrerit. Proin aliquam posuere leo, nec feugiat diam. Nunc efficitur' ||
 ' porttitor auctor. Morbi bibendum, risus in dapibus mattis, nunc massa malesuada massa, scelerisque pharetra purus lorem sit amet velit.',
 '2019-10-25 12:42:34.609474', FALSE, '2019-11-26 13:44:34.609474', 'PUBLISHED', 'Fermentum iaculis', '1', '2', '1'),

('Vestibulum lacinia efficitur felis eget dapibus. Curabitur ut ante ante. Etiam tincidunt enim nunc, non tempus tortor mollis vitae.' ||
 ' Sed bibendum risus quis faucibus pharetra. Praesent at lacus nibh. Donec mollis pharetra dolor quis commodo. Nunc eget dui luctus,' ||
 ' volutpat velit vel, egestas enim. Morbi semper congue ante, ut tincidunt urna efficitur at. Nunc vulputate est sed mi hendrerit porttitor.' ||
 ' Nulla ut nibh vitae augue malesuada commodo vitae vitae magna. Fusce sollicitudin faucibus nisl, ac sollicitudin orci tincidunt ut.' ||
 ' Donec aliquet faucibus elit, quis consectetur tortor pulvinar et. Nunc facilisis ante ante, vitae viverra ligula semper non.',
 '2019-11-20 14:42:34.609474', TRUE, '2019-12-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis', '3', '4', '1'),

('Ut posuere accumsan diam, non vestibulum mauris aliquet non. Cras sed nisl id orci imperdiet pellentesque. Duis facilisis porttitor' ||
 ' varius. Mauris quis leo facilisis, scelerisque massa non, ornare turpis. Donec ultricies eget massa vitae pellentesque. Nunc rhoncus' ||
 ' faucibus hendrerit. Pellentesque et maximus leo, ac posuere nunc. Cras vitae tincidunt nibh. Ut non erat vel felis congue iaculis.',
 '2020-01-20 14:42:34.609474', TRUE, '2020-02-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis', '4', '5', '1'),

('Suspendisse vel ex venenatis, euismod augue at, auctor velit. Sed fringilla, arcu in pulvinar condimentum, urna nulla iaculis felis,' ||
 ' in ornare orci turpis non nibh. Pellentesque turpis ante, hendrerit sed tempor elementum, tristique quis justo. Ut non dignissim orci.' ||
 ' Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut vel augue nec magna cursus bibendum at' ||
 ' eu eros. Phasellus dui ligula, accumsan sit amet tincidunt nec, ornare vel metus. Proin justo mauris, ullamcorper ut euismod malesuada,' ||
 ' iaculis a lectus. Aliquam maximus laoreet justo vel viverra. Curabitur at lacus nisl. Nunc tincidunt faucibus dolor eu mollis. Mauris id' ||
 ' sem id lorem luctus pretium nec sit amet turpis. Maecenas eu leo sit amet metus commodo aliquet sit amet vitae lectus. Cras finibus luctus' ||
 ' urna a ullamcorper.', '2020-02-20 14:42:34.609474', TRUE, '2020-03-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis', '5', '1', '1'),

('Quisque sollicitudin orci sollicitudin sapien finibus tempus. Nulla sapien urna, viverra quis enim posuere, consectetur laoreet elit.' ||
 ' Nulla a pretium diam. Nulla facilisi. Sed ipsum ante, dictum non justo sit amet, commodo ultricies dolor. Etiam sed nunc vel justo' ||
 ' tincidunt varius. Etiam in pretium lectus. Donec porttitor lobortis sem, eget consequat metus accumsan ac. Ut ex dolor, efficitur' ||
 ' sit amet sodales in, vulputate quis tortor. Nunc sit amet nisl tortor. Sed finibus feugiat quam, eget tincidunt turpis vestibulum id.' ||
 ' Mauris maximus ex leo, a egestas mauris sollicitudin in. Aenean ut arcu pellentesque eros efficitur posuere. Nam lobortis accumsan' ||
 ' suscipit. Sed velit est, aliquam nec odio in, sodales facilisis ex.', '2020-03-20 14:42:34.609474', TRUE, '2020-04-26 13:45:34.609474',
 'PUBLISHED', 'Fermentum iaculis', '6', '2', '1'),

('Cras at est ex. Aliquam erat volutpat. Donec ultricies dolor nec justo euismod pretium. Donec elementum vestibulum feugiat. Fusce' ||
 ' ullamcorper sapien sit amet venenatis convallis. Sed sagittis interdum sapien ac interdum. Praesent porttitor condimentum lacus,' ||
 ' ac aliquam dui pulvinar accumsan.', '2020-04-20 14:42:34.609474', TRUE, '2020-05-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis',
 '7', '3', '1'),

('Praesent nec maximus sem. Ut imperdiet, libero sit amet porta vulputate, dolor erat pretium neque, in efficitur eros enim in enim.' ||
 ' In non pretium urna. Suspendisse quis ante ut erat pulvinar accumsan. Integer molestie ex eget libero tincidunt, sit amet ullamcorper' ||
 ' mauris fermentum. Donec porta posuere ornare. Etiam congue quis lacus id volutpat. Integer scelerisque a dolor non tristique. ' ||
 'Sed nisl sapien, tincidunt sit amet posuere ut, bibendum at velit. Proin eget risus in risus accumsan accumsan pharetra rutrum leo.' ||
 ' Vivamus porta lectus leo, vel tincidunt libero rhoncus vitae. Sed et dolor nec tellus ornare bibendum at vel mi. Donec pellentesque' ||
 ' placerat fringilla.', '2020-05-20 14:42:34.609474', TRUE, '2020-06-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis',
 '8', '4', '1'),

('Duis finibus risus et hendrerit fringilla. Donec varius nisi massa, eu facilisis velit iaculis id. Sed porta lectus eget lectus auctor,' ||
 ' at iaculis risus consequat. Morbi lacinia pretium est, pulvinar porttitor lorem facilisis ut. Etiam in nisi justo. Vivamus luctus' ||
 ' efficitur mauris, ut placerat lorem imperdiet a. Phasellus condimentum ante quis leo tempor semper. Nam sit amet faucibus orci, ' ||
 'et egestas tortor. Maecenas ornare a lorem nec mattis. Etiam ac dapibus quam. Proin consequat laoreet rutrum. Cras et congue ligula.',
 '2020-06-20 14:42:34.609474', TRUE, '2020-07-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis', '9', '5', '1'),

('Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Proin orci lectus, sodales sed varius non,' ||
 ' fringilla non metus. Nam tincidunt libero eu tincidunt lacinia. Sed scelerisque metus at purus pellentesque, eu elementum mauris tempor.' ||
 ' Mauris metus mauris, convallis in porttitor sit amet, fermentum vel ipsum. Sed hendrerit volutpat eros, quis commodo ligula aliquet vitae.' ||
 ' Nullam gravida quam et ligula facilisis, a volutpat sapien viverra. Integer non porta enim, in blandit leo. Etiam tempor elit lacus, ut' ||
 ' venenatis massa rhoncus at. Etiam eu massa rutrum, aliquet arcu at, lobortis ligula. In sit amet nisi nec ex congue lobortis vitae at' ||
 ' risus. Praesent elementum accumsan magna, eget auctor ex scelerisque non. Fusce posuere, metus varius malesuada varius, sem velit' ||
 ' varius mauris, eget scelerisque mi turpis sit amet dui. Cras pellentesque pulvinar tincidunt. Lorem ipsum dolor sit amet, consectetur' ||
 ' adipiscing elit.', '2020-07-20 14:42:34.609474', TRUE, '2020-08-26 13:45:34.609474', 'PUBLISHED', 'Fermentum iaculis', '10', '1', '1');