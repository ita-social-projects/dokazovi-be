INSERT INTO PUBLIC.DIRECTIONS (NAME) VALUES
('Covid-19'), ('Офтальмологія'), ('Хірургія'), ('Терапія'), ('Вірусологія');


INSERT INTO PUBLIC.INSTITUTIONS (NAME, ADDRESS, CITY_ID) VALUES
('Адоніс', 'вул. Дніпровська набережна, 26К', '190'),
('Новий зір', 'вул. Коперника, 12Д', '190'),
('Інномед', 'ш. Хмельницьке, 96Г', '81'),
('Медікум', 'пр-т. Д. Яворницького, 59', '119'),
('Medical Idea', 'вул. Короленка, 54', '55');


INSERT INTO PUBLIC.USERS (EMAIL, PASSWORD, STATUS, FIRST_NAME, LAST_NAME, QUALIFICATION, PHONE, BIO,
                          CREATED_AT, AVATAR, DIRECTION_ID, INSTITUTION_ID)
VALUES
('ivan@mail.com', 'ivan', 'ACTIVE', 'Іван', 'Іванов', 'Лікар вищої категорії', '+380969696969'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://i.imgur.com/I80W1Q0.png', '3', '1'),

('andrii@mail.com', 'andrii', 'ACTIVE', 'Андрій', 'Петров', 'Лікар-спеціаліст', '+380505050505'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png', '5', '1'),

('olena@mail.com', 'olena', 'ACTIVE', 'Олена', 'Шевченко', 'Кандидат медичних наук', '+380939393939'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://html5css.ru/howto/img_avatar2.png', '1', '2'),

('dmytro@mail.com', 'dmytro', 'ACTIVE', 'Дмитро', 'Степаненко', 'Лікар-спеціаліст', '+380444444444'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://www.publicdomainpictures.net/pictures/270000/velka/avatar-people-person-business-u.jpg', '4', '1'),

('kateryna@mail.com', 'kateryna', 'ACTIVE', 'Катерина', 'Кравченко', 'Кандидат медичних наук', '+380989898989'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://cnet2.cbsistatic.com/img/liJ9UZA87zs1viJiuEfVnL7YYfw=/940x0/2020/05/18/5bac8cc1-4bd5-4496-a8c3-66a6cd' ||
        '12d0cb/fb-avatar-2.jpg', '3', '3'),

('oleh@mail.com', 'oleh', 'ACTIVE', 'Олег', 'Петренко', 'Кандидат медичних наук', '+380939393939'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://www.striketwosummit.com/wp-content/uploads/2019/04/avatar-1606916_960_720.png', '2', '4'),

('tetyana@mail.com', 'tetyana', 'ACTIVE', 'Тетяна', 'Коваленко', 'Лікар вищої категорії', '+380444444444'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://www.pinclipart.com/picdir/middle/155-1559325_female-avatar-clipart.png', '1', '5'),

('maryna@mail.com', 'maryna', 'ACTIVE', 'Марина', 'Вовк', 'Лікар-спеціаліст', '+380939393939'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR3F3e5C2_4KVWpSSvmBDVb8iPGyRnDB5DVPA&usqp=CAU', '5', '3'),

('admin@mail.com', 'admin', 'ACTIVE', 'Gregory', 'House', 'Адмін', '+380939393939'
    , 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ' ||
      'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ' ||
      'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur ' ||
      'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
 NOW(), 'https://pickaface.net/gallery/avatar/20130319_083314_1174_admin.png', null, null);


INSERT INTO PUBLIC.USERS_INSTITUTIONS (USER_ID, INSTITUTION_ID) VALUES
('1', '1'), ('1', '2'), ('1', '5'), ('2', '2'), ('2', '5'), ('3', '1'), ('3', '2'), ('4', '1'), ('4', '5'), ('5', '3'),
('6', '4'), ('7', '1'), ('7', '5'), ('8', '3');


INSERT INTO PUBLIC.ROLES (ROLE_ID, ROLE_NAME) VALUES
('1', 'Admin'), ('2', 'Doctor'), ('3', 'Trusted Doctor');


INSERT INTO PUBLIC.ROLES_USERS (ROLE_ID, USER_ID) VALUES
('1', '9'), ('2', '4'), ('3', '2'), ('2', '1'), ('3', '3'), ('2', '5'), ('3', '7'), ('2', '6'), ('3', '8');


INSERT INTO PUBLIC.USERS_DIRECTIONS (USER_ID, DIRECTION_ID) VALUES
('1', '2'), ('1', '3'), ('1', '5'), ('2', '1'), ('2', '3'), ('2', '5'), ('3', '1'), ('3', '2'), ('3', '3'),
('4', '3'), ('4', '4'), ('4', '5'), ('5', '1'), ('5', '3'), ('5', '5'), ('6', '2'), ('6', '3'), ('6', '4'),
('7', '1'), ('7', '4'), ('7', '5'), ('8', '3'), ('8', '4'), ('8', '5');


INSERT INTO PUBLIC.CHARITIES (BODY, CREATED_AT, MODIFIED_AT, AUTHOR_ID) VALUES
('Lorem ipsum - 50шт. Ut enim - 10шт.', NOW(), NOW(), 2),
('Ut enim - 5шт. ipsum - 20шт.', NOW(), NOW(), 4),
('Lorem - 500шт. Ut enim - 100шт.', NOW(), NOW(), 6),
('Ut enim - 10шт. Excepteur sint - 200шт.', NOW(), NOW(), 7);


INSERT INTO PUBLIC.SOURCES (TYPE, VALUE, USER_ID) VALUES
('PROFILE_IMAGE', 'https://i.imgur.com/I80W1Q0.png', '1'),
('PROFILE_IMAGE', 'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png', '2'),
('PROFILE_IMAGE', 'https://html5css.ru/howto/img_avatar2.png', '3'),
('PROFILE_IMAGE', 'https://www.publicdomainpictures.net/pictures/270000/velka/avatar-people-person-business-u.jpg', '4'),
('PROFILE_IMAGE', 'https://cnet2.cbsistatic.com/img/liJ9UZA87zs1viJiuEfVnL7YYfw=/940x0/2020/05/18/5bac8cc1-4bd5-4496-a8c3-66a6cd12d0cb/fb-avatar-2.jpg', '5'),
('PROFILE_IMAGE', 'https://www.striketwosummit.com/wp-content/uploads/2019/04/avatar-1606916_960_720.png', '6'),
('PROFILE_IMAGE', 'https://www.pinclipart.com/picdir/middle/155-1559325_female-avatar-clipart.png', '7'),
('PROFILE_IMAGE', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR3F3e5C2_4KVWpSSvmBDVb8iPGyRnDB5DVPA&usqp=CAU', '8'),
('PROFILE_IMAGE', 'https://pickaface.net/gallery/avatar/20130319_083314_1174_admin.png', '9'),
('POST_IMAGE', 'https://www.afd.fr/sites/afd/files/styles/visuel_principal/public/2019-10-09-27-46/flickr-marco-verch.jpg?itok=XH4x7-Y4', '4'),
('POST_IMAGE', 'https://www.everest.ua/wp-content/uploads/2020/04/6_result-4.jpg', '1'),
('POST_IMAGE', 'https://epsilon.aeon.co/images/afef287f-dd6f-4a6a-b8a6-4f0a09330657/idea_sized-kendal-l4ikccachoc-unsplash.jpg', '1'),
('POST_IMAGE', 'https://blogs.biomedcentral.com/on-medicine/wp-content/uploads/sites/6/2018/06/2017-08-30-07-09-32-1000x667-620x342.jpg', '4'),
('POST_VIDEO', 'https://www.youtube.com/watch?v=YdzH6iIY1PQ&ab_channel=%D0%A2%D0%A1%D0%9D', '7'),
('POST_VIDEO', 'https://www.youtube.com/watch?v=vEabuIRTJ2M&ab_channel=%D0%9C%D0%B0%D0%BC%D0%B5%D0%B4%D1%8B%D1%87-%D0%96%D0%B8%D0%B7%D0%BD%D1%8C%D1%85%D0%B8%D1%80%D1%83%D1%80%D0%B3%D0%B0', '1'),
('POST_VIDEO', 'https://www.youtube.com/watch?v=a24CJXQJdyU&ab_channel=%D0%92%D1%96%D0%BA%D0%BD%D0%B0-%D0%BD%D0%BE%D0%B2%D0%B8%D0%BD%D0%B8', '1'),
('POST_VIDEO', 'https://www.youtube.com/watch?v=03yciZyoVRI&ab_channel=%D0%9B%D1%96%D0%BA%D0%B0%D1%80%D0%AE%D1%80%D1%96%D0%B9%D0%A1%D0%BE%D0%BB%D0%BE%D0%B4%D0%BE%D0%B2', '7');


INSERT INTO PUBLIC.POST_TYPES (NAME) VALUES
('Стаття'), ('Відео'), ('Допис'), ('Переклад');


INSERT INTO PUBLIC.TAGS (TAG) VALUES
('Профілактика'), ('Комплекс'), ('Ковід'), ('Допомога'), ('Міокард'), ('ЕКГ');


INSERT INTO PUBLIC.POSTS (CONTENT, CREATED_AT, IMPORTANT, MODIFIED_AT, STATUS, TITLE, AUTHOR_ID, DIRECTION_ID, TYPE_ID)
VALUES

('Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. ' ||
 'Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget ' ||
 'nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis ' ||
 'scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu ' ||
 'odio ut sem nulla pharetra.', NOW(), TRUE, NOW(), 'PUBLISHED', 'Fermentum iaculis', '2', '3', '1'),

('Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum ' ||
 'facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt ' ||
 'nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.', NOW(), FALSE,
 NOW(), 'MODERATION_SECOND_SIGN', 'Integer eget aliquet', '5', '2', '1'),

('Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum ' ||
 'at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas ' ||
 'fringilla phasellus faucibus scelerisque eleifend.', NOW(), FALSE , NOW(), 'ARCHIVED', 'Ultrices sagittis orci',
 '4', '5', '4'),

('Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur' ||
 '. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.', NOW(), FALSE,
 NOW(), 'PUBLISHED', 'Massa eget egestas', '6', '1', '2'),

('Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies ' ||
 'tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper ' ||
 'a lacus vestibulum. Erat nam at lectus urna.', NOW(), TRUE, NOW(), 'MODERATION_FIRST_SIGN', 'Est ullamcorper eget',
 '1', '3', '1'),

('Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. ' ||
 'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum ' ||
 'facilisis leo.', NOW(), FALSE, NOW(), 'ARCHIVED', 'Sit amet consectetur', '4', '2', '3'),

('Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant ' ||
 'morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.', NOW(), FALSE, NOW(), 'PUBLISHED',
 'Proin nibh nisl condimentum', '1', '1', '2'),

('Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at' ||
 ' varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas ' ||
 'fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
 NOW(), FALSE, NOW(), 'DRAFT', 'Tempus quam pellentesque', '7', '2', '2'),

('Proin nibh nisl condimentum id venenatis a condimentum vitae.', NOW(), FALSE, NOW(), 'DRAFT',
 'Tempus quam pellentesque', '7', '3', '2'),

('Dolor sit amet consectetur adipiscing elit ut aliquam purus.', NOW(), FALSE, NOW(), 'PUBLISHED',
 'Ultrices eros in cursus', '1', '5', '2');


INSERT INTO PUBLIC.POSTS_SOURCES (SOURCE_ID, POST_ID) VALUES
('11', '5'), ('12', '5'), ('13', '6'), ('10', '6'), ('15', '7'), ('14', '8'), ('17', '9'), ('16', '10');


INSERT INTO PUBLIC.POSTS_TAGS (POST_ID, TAG_ID) VALUES
('1', '1'), ('1', '2'), ('1', '4'), ('1', '6'), ('2', '1'), ('2', '2'), ('3', '1'), ('3', '4'),
('4', '2'), ('4', '3'), ('4', '4'), ('4', '5'), ('5', '1'), ('6', '2'), ('6', '4'), ('6', '1'),
('6', '3'), ('7', '4'), ('7', '5'), ('8', '1'), ('8', '3'), ('8', '4'), ('9', '2'), ('9', '4'),
('10', '1'), ('10', '3'), ('10', '5'), ('10', '6');


INSERT INTO PUBLIC.POSTS_DIRECTIONS (POST_ID, DIRECTION_ID) VALUES
('1', '1'), ('1', '3'), ('1', '4'), ('2', '2'), ('2', '5'), ('3', '2'), ('3', '1'), ('3', '5'),
('4', '1'), ('4', '2'), ('5', '2'), ('5', '3'), ('5', '4'), ('6', '1'), ('6', '2'), ('6', '3'),
('7', '1'), ('7', '3'), ('7', '4'), ('7', '5'), ('8', '1'), ('8', '2'), ('8', '3'), ('9', '3'),
('10', '1'), ('10', '3'), ('10', '4'), ('10', '5');