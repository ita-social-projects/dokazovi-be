INSERT INTO public.tags (tag_id, tag)
VALUES (1, 'Профілактика');
INSERT INTO public.tags (tag_id, tag)
VALUES (2, 'Комплекс');
INSERT INTO public.tags (tag_id, tag)
VALUES (3, 'Ковід');
INSERT INTO public.tags (tag_id, tag)
VALUES (4, 'Допомога');
INSERT INTO public.tags (tag_id, tag)
VALUES (5, 'Міокард');
INSERT INTO public.tags (tag_id, tag)
VALUES (6, 'ЕКГ');

INSERT INTO public.directions (direction_id, label, color, name)
VALUES (1, 'Covid-19', '#ef5350', 'covid-19');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (2, 'Офтальмологія', '#98ef50', 'ophthalmology');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (3, 'Хірургія', '#7aebbf', 'surgery');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (4, 'Терапія', '#ffee58', 'therapy');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (5, 'Вірусологія', '#da80e8', 'virology');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (6, 'Кардіологія', '#00ffff', 'cardiology');
INSERT INTO public.directions (direction_id, label, color, name)
VALUES (7, 'Педіатрія', '#993333', 'pediatrics');

INSERT INTO public.post_types (type_id, name)
VALUES (1, 'Стаття');
INSERT INTO public.post_types (type_id, name)
VALUES (2, 'Відео');
INSERT INTO public.post_types (type_id, name)
VALUES (3, 'Допис');
INSERT INTO public.post_types (type_id, name)
VALUES (4, 'Переклад');

INSERT INTO public.regions (region_id, name)
VALUES (1, 'Автономна Республіка Крим');
INSERT INTO public.regions (region_id, name)
VALUES (2, 'Вінницька область');
INSERT INTO public.regions (region_id, name)
VALUES (3, 'Волинська область');
INSERT INTO public.regions (region_id, name)
VALUES (4, 'Дніпропетровська область');
INSERT INTO public.regions (region_id, name)
VALUES (5, 'Донецька область');
INSERT INTO public.regions (region_id, name)
VALUES (6, 'Житомирська область');
INSERT INTO public.regions (region_id, name)
VALUES (7, 'Закарпатська область');
INSERT INTO public.regions (region_id, name)
VALUES (8, 'Запорізька область');
INSERT INTO public.regions (region_id, name)
VALUES (9, 'Івано-Франківська область');
INSERT INTO public.regions (region_id, name)
VALUES (10, 'Київська область');
INSERT INTO public.regions (region_id, name)
VALUES (11, 'Кіровоградська область');
INSERT INTO public.regions (region_id, name)
VALUES (12, 'Луганська область');
INSERT INTO public.regions (region_id, name)
VALUES (13, 'Львівська область');
INSERT INTO public.regions (region_id, name)
VALUES (14, 'Миколаївська область');
INSERT INTO public.regions (region_id, name)
VALUES (15, 'Одеська область');
INSERT INTO public.regions (region_id, name)
VALUES (16, 'Полтавська область');
INSERT INTO public.regions (region_id, name)
VALUES (17, 'Рівненська область');
INSERT INTO public.regions (region_id, name)
VALUES (18, 'Сумська область');
INSERT INTO public.regions (region_id, name)
VALUES (19, 'Тернопільська область');
INSERT INTO public.regions (region_id, name)
VALUES (20, 'Харківська область');
INSERT INTO public.regions (region_id, name)
VALUES (21, 'Херсонська область');
INSERT INTO public.regions (region_id, name)
VALUES (22, 'Хмельницька область');
INSERT INTO public.regions (region_id, name)
VALUES (23, 'Черкаська область');
INSERT INTO public.regions (region_id, name)
VALUES (24, 'Чернівецька область');
INSERT INTO public.regions (region_id, name)
VALUES (25, 'Чернігівська область');

INSERT INTO public.roles (role_id, role_name)
VALUES (1, 'Administrator');
INSERT INTO public.roles (role_id, role_name)
VALUES (2, 'Moderator');
INSERT INTO public.roles (role_id, role_name)
VALUES (3, 'Doctor');

INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (37, 'TarzhemanSokolov585@mail.com', '$2y$10$CXUu2az9AJHT23fUcXkGd.x4m.ByDOCb6e0uU8teF/Dgq2lqeHfv2', 'ACTIVE',
        'Таржеман', 'Соколов', '+380631434712', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=70', true,
        3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (36, 'MiletiyChernyshov229@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Милетий', 'Чернышов', '+380637931441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=68', true,
        3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (35, 'NezdaUshakov899@mail.com', '$2y$10$MqU8B7EDd7aNo/CTmr0OKeIkRQHObRBEfxMbY2LfDbvZKmsliZ3ia', 'ACTIVE',
        'Незда', 'Ушаков', '+380637687420', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=67', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (34, 'KukuriChapko624@mail.com', '$2y$10$FHxAyHKwM2AqUF582/FFhuuf.OzU6ECIpwTpMxLCeEyz0rht.hjcG', 'ACTIVE',
        'Кукури', 'Чапко', '+380631793743', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=66', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (33, 'GigranAlekseev32@mail.com', '$2y$10$/m6y5AVzG9yxmthcQ7M1FOsrh8EqZmFvGZjoi7lDyy04eUqQ0gnqe', 'ACTIVE',
        'Гигран', 'Алексеев', '+380634724237', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=65', true,
        3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (32, 'PalanaLitvinova514@mail.com', '$2y$10$I2VwJytpzEZi2DPzLPJk.uhcd4WIi/Ek5dMkLjQcurEZWqI0MxTl6', 'ACTIVE',
        'Палана', 'Литвинова', '+380633484351', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=44', true,
        3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (24, 'regina@mail.com', '$2y$10$SxF6UMIS1cn7Wi76GwMBFedgGukB9pe3XeL8Qz9pRfUNw3BtD.CDG', 'ACTIVE', 'Регіна',
        'Регіненко', '+380956765669', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=27', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (15, 'taras@mail.com', '$2y$10$dCSLVzPoFC7fAfFdgCFayui2u3Tt9Z6IXEZZkCKzuXFvqyWB3cmUO', 'ACTIVE', 'Тарас',
        'Шевченко', '+380956098369', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=17', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (14, 'mykola@mail.com', '$2y$10$IaiLTkeqRVSuUtr4weOYmODGOlUp4aBdPQTjQun1klh9CMTsQ12Zu', 'ACTIVE', 'Микола',
        'Миколенко', '+380956412369', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=15', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (13, 'masha@mail.com', '$2y$10$igRjDpb3m9EuDvPiGr.RLuuLyjB0M9Snx3yeGW25zprYceSnBNfMq', 'ACTIVE', 'Марія',
        'Марієнко', '+380956456969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=16', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (11, 'danylo@mail.com', '$2y$10$dhLh3iKYde/riLznRRU1duits0zbiAFQ46l5AVRhDddJtUBHm3OHW', 'ACTIVE', 'Данило',
        'Даниленко', '+380961233969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=13', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (10, 'stepan@mail.com', '$2y$10$CQdJOp/Jw5LmRoUt0W7wSO6lE4N0hM3k0gr1mCI8w8ZOp8ERZcjzO', 'ACTIVE', 'Степан',
        'Степанов', '+380969123969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=12', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (8, 'maryna@mail.com', '$2y$10$9R5cJzKJEsP4sjMkH7yHLe6miXbIfY7Jh3xHf8eC4h2jLYH6fGof2', 'ACTIVE', 'Марина',
        'Вовк', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=5', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (6, 'oleh@mail.com', '$2y$10$VSWtNZs6WLP5FvAjK.B/JOSLkkcTet9EBHD.PCBbcE1NyyFiVHuA6', 'ACTIVE', 'Олег',
        'Петренко', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=11', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (5, 'kateryna@mail.com', '$2y$10$oblwylAYFcVWCXINBCNH1egDuBSy5YGdiyMvN0wM8trelRuy45BU2', 'ACTIVE', 'Катерина',
        'Кравченко', '+380989898989', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=9', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (4, 'dmytro@mail.com', '$2y$10$7BhpXBFMGobXWpNj31XyWeezGYlx.OxTKVOqy34Nl/qVd6.50j/LS', 'ACTIVE', 'Дмитро',
        'Степаненко', '+380444444444', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=8', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (3, 'olena@mail.com', '$2y$10$EGlzdsCMezKEBDuXsXsGV.REQRsfWPzFecRhnOZjbLl/HTQMbZx7G', 'ACTIVE', 'Олена',
        'Шевченко', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=1', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (2, 'andrii@mail.com', '$2y$10$ZelYHG3iwVCJVvp0dimraerFaYdABop.SPBwUkW7RsiDMA9.h6XTa', 'ACTIVE', 'Андрій',
        'Петров', '+380505050505', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=7', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (1, 'ivan@mail.com', '$2y$10$ishgf6hBdlEQwE8Ld1ktkOOPsINMgE7CviFi1qxRaiOgvUdg3RCTy', 'ACTIVE', 'Іван', 'Іванов',
        '+380969696969', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=3', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (31, 'FekletinyaShashkova782@mail.com', '$2y$10$6PL9y4SFgNf014aGOAsM1u9CVA.9q08tPDDe/C8.E9WXl0B0oWk3G', 'ACTIVE',
        'Феклетинья', 'Шашкова', '+380639220084', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=48',
        true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (30, 'PamfiyTihomirov752@mail.com', '$2y$10$qoMjXIOaovHvpEHraY5pw.q.FwPaXm2PdpU83uM1GemK4dgPD6rSq', 'ACTIVE',
        'Памфий', 'Тихомиров', '+380635701294', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=59', true,
        3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (29, 'FadiStalin333@mail.com', '$2y$10$Qk.6cy.s7uTNKQGYFeF9/urECrBpZ4NtLEN9Hzjj0g7soVRzcGu1u', 'ACTIVE', 'Фади',
        'Сталин', '+380636962951', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=58', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (28, 'ShtukaNovitskaya261@mail.com', '$2y$10$59GRRruj9n/T4wqElz.IEOXrhwJDoPaz8FdJQJuk4KM.iisikn0lW', 'ACTIVE',
        'Щука', 'Новицкая', '+380632405186', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=26', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (27, 'platon@mail.com', '$2y$10$pZm26FgFAgGbMna/ut0vu.jtR0PZI9kRWCbF.6HshU8wQ32/rMpH2', 'ACTIVE', 'Платон',
        'Платоненко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=57', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (26, 'terentiy@mail.com', '$2y$10$NhzfYhZemyRhR61aPJmtnO1WFv06y2ENyEctQCePx.Ymn6wL6P2h.', 'ACTIVE', 'Терентій',
        'Терентієнко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=56', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (25, 'fedot@mail.com', '$2y$10$.oKUlohR31I8wni/Qi8nwu9cJti4P5ddg6oq6FIK4H7r/jmmK44sG', 'ACTIVE', 'Федот',
        'Федотенко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=55', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (9, 'admin@mail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE', 'Gregory',
        'House', '+380939393939', '2021-02-16 03:56:37.332925', null, true, 1);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (23, 'matviy@mail.com', '$2y$10$dGfbNZAFW5vAkr50kTJBEO3rUDWJuj8B4dfFkmOS/kBlnGqiKqLHS', 'ACTIVE', 'Матвій',
        'Матвійчук', '+380956098369', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=54', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (22, 'nikon@mail.com', '$2y$10$EdUEHquCl7OyAqTtfoT1suL1QYZ/68yH36TNWOFnFj/gU.9TjvZAS', 'ACTIVE', 'Нікон',
        'Ніконенко', '+380956412369', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=53', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (21, 'zhan@mail.com', '$2y$10$Dx6cNJ3AGW9xzin3xYLpKuQbDO85WM7zy8t.CIJ7JZJFUY3R.8UKe', 'ACTIVE', 'Жан', 'Жанко',
        '+380956456969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=51', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (20, 'nazar@mail.com', '$2y$10$4jMtf0eEjVaV0bJWLEb2OOpzEI8md27557Ei/z9eVqugk4Ueg3LLm', 'ACTIVE', 'Назар',
        'Назаренко', '+380961456969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=52', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (12, 'petro@mail.com', '$2y$10$MAU64Cv9xZ629f3oTiTJZeY5LbkV1KrVzQ9BeHE0wymMlSqHjUovS', 'ACTIVE', 'Петро',
        'Петренко', '+380961456969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=14', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (7, 'tetyana@mail.com', '$2y$10$bwaU77GtjK3HfMZjti5Gx.Q.SkqerM0V2.XzxN6AlOLGmI90dsPTu', 'ACTIVE', 'Тетяна',
        'Коваленко', '+380444444444', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=10', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (19, 'zahar@mail.com', '$2y$10$hVu0ysEa3BNNi4ZAzcvMxe2A0qb5szLLM2Md/XltMIg54jrVbJ1Ay', 'ACTIVE', 'Захар',
        'Захаренко', '+380961233969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=33', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (18, 'eva@mail.com', '$2y$10$9whP9V3MfAs/G9imeF9g4e6UMVotHZzp6cqo9bMleSNXNbwPESbm.', 'ACTIVE', 'Єва', 'Євенко',
        '+380969123969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=20', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (17, 'bohdan@mail.com', '$2y$10$DYodORfhp4RQl/5mW5XUfO4suMshfRW7611eKSnb/4AVdrKwUb6wS', 'ACTIVE', 'Богдан',
        'Хмельницький', '+380956761119', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=18', true, 3);
INSERT INTO public.users (user_id, email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES (16, 'lesia@mail.com', '$2y$10$TeNyf4iiE7xy0HFHmtRRLu2Q7BH4ceYjYudZpIUJ13qjhvapdjjHe', 'ACTIVE', 'Леся',
        'Українка', '+380956765669', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=19', true, 3);

INSERT INTO public.charities (charity_id, body, author_id, created_at, modified_at)
VALUES (1, 'Lorem ipsum - 50шт. Ut enim - 10шт.', 2, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (charity_id, body, author_id, created_at, modified_at)
VALUES (2, 'Ut enim - 5шт. ipsum - 20шт.', 4, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (charity_id, body, author_id, created_at, modified_at)
VALUES (3, 'Lorem - 500шт. Ut enim - 100шт.', 6, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (charity_id, body, author_id, created_at, modified_at)
VALUES (4, 'Ut enim - 10шт. Excepteur sint - 200шт.', 7, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');

INSERT INTO public.cities (city_id, name, region_id)
VALUES (1, 'Авдіївка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (2, 'Алмазна', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (3, 'Алупка', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (4, 'Алушта', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (5, 'Алчевськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (6, 'Амвросіївка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (7, 'Ананьїв', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (8, 'Андрушівка', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (9, 'Антрацит', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (10, 'Апостолове', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (11, 'Армянськ', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (12, 'Арциз', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (13, 'Балаклія', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (14, 'Балта', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (15, 'Бар', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (16, 'Баранівка', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (17, 'Барвінкове', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (18, 'Батурин', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (19, 'Бахмач', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (20, 'Бахмут', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (21, 'Бахчисарай', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (22, 'Баштанка', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (23, 'Белз', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (24, 'Бердичів', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (25, 'Бердянськ', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (26, 'Берегове', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (27, 'Бережани', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (28, 'Березань', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (29, 'Березівка', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (30, 'Березне', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (31, 'Берестечко', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (32, 'Берислав', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (33, 'Бершадь', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (34, 'Бібрка', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (35, 'Біла Церква', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (36, 'Білгород-Дністровський', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (37, 'Білицьке', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (38, 'Білогірськ', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (39, 'Білозерське', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (40, 'Білопілля', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (41, 'Біляївка', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (42, 'Благовіщенське', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (43, 'Бобринець', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (44, 'Бобровиця', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (45, 'Богодухів', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (46, 'Богуслав', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (47, 'Боково-Хрустальне', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (48, 'Болград', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (49, 'Болехів', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (50, 'Борзна', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (51, 'Борислав', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (52, 'Бориспіль', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (53, 'Борщів', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (54, 'Боярка', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (55, 'Бровари', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (56, 'Броди', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (57, 'Брянка', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (58, 'Бунге', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (59, 'Буринь', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (60, 'Бурштин', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (61, 'Буськ', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (62, 'Буча', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (63, 'Бучач', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (64, 'Валки', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (65, 'Вараш', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (66, 'Василівка', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (67, 'Васильків', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (68, 'Ватутіне', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (69, 'Вашківці', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (70, 'Великі Мости', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (71, 'Верхівцеве', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (72, 'Верхньодніпровськ', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (73, 'Вижниця', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (74, 'Вилкове', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (75, 'Винники', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (76, 'Виноградів', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (77, 'Вишгород', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (78, 'Вишневе', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (79, 'Вільногірськ', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (80, 'Вільнянськ', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (81, 'Вінниця', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (82, 'Вовчанськ', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (83, 'Вознесенівка', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (84, 'Вознесенськ', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (85, 'Волноваха', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (86, 'Володимир-Волинський', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (87, 'Волочиськ', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (88, 'Ворожба', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (89, 'Вуглегірськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (90, 'Вугледар', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (91, 'Гадяч', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (92, 'Гайворон', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (93, 'Гайсин', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (94, 'Галич', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (95, 'Генічеськ', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (96, 'Герца', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (97, 'Гірник', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (98, 'Гірське', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (99, 'Глиняни', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (100, 'Глобине', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (101, 'Глухів', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (102, 'Гнівань', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (103, 'Гола Пристань', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (104, 'Голубівка', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (105, 'Горішні Плавні', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (106, 'Горлівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (107, 'Городенка', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (108, 'Городище', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (109, 'Городня', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (110, 'Городок', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (111, 'Городок', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (112, 'Горохів', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (113, 'Гребінка', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (114, 'Гуляйполе', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (115, 'Дебальцеве', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (116, 'Деражня', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (117, 'Дергачі', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (118, 'Джанкой', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (119, 'Дніпро', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (120, 'Дніпрорудне', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (121, 'Добромиль', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (122, 'Добропілля', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (123, 'Довжанськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (124, 'Докучаєвськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (125, 'Долина', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (126, 'Долинська', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (127, 'Донецьк', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (128, 'Дрогобич', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (129, 'Дружба', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (130, 'Дружківка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (131, 'Дубляни', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (132, 'Дубно', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (133, 'Дубровиця', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (134, 'Дунаївці', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (135, 'Енергодар', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (136, 'Євпаторія', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (137, 'Єнакієве', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (138, 'Жашків', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (139, 'Жданівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (140, 'Жидачів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (141, 'Житомир', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (142, 'Жмеринка', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (143, 'Жовква', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (144, 'Жовті Води', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (145, 'Заводське', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (146, 'Залізне', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (147, 'Заліщики', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (148, 'Запоріжжя', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (149, 'Заставна', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (150, 'Збараж', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (151, 'Зборів', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (152, 'Звенигородка', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (153, 'Здолбунів', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (154, 'Зеленодольськ', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (155, 'Зимогір''я', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (156, 'Зіньків', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (157, 'Зміїв', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (158, 'Знам''янка', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (159, 'Золоте', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (160, 'Золотоноша', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (161, 'Золочів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (162, 'Зоринськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (163, 'Зугрес', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (164, 'Івано-Франківськ', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (165, 'Ізмаїл', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (166, 'Ізюм', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (167, 'Ізяслав', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (168, 'Іллінці', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (169, 'Іловайськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (170, 'Інкерман', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (171, 'Ірміно', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (172, 'Ірпінь', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (173, 'Іршава', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (174, 'Ічня', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (175, 'Кагарлик', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (176, 'Кадіївка', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (177, 'Калинівка', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (178, 'Калуш', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (179, 'Кальміуське', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (180, 'Камінь-Каширський', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (181, 'Кам''янець-Подільський', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (182, 'Кам''янка', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (183, 'Камянка-Бузька', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (184, 'Кам''янка-Дніпровська', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (185, 'Кам''янське', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (186, 'Канів', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (187, 'Карлівка', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (188, 'Каховка', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (189, 'Керч', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (190, 'Київ', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (191, 'Кипуче', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (192, 'Ківерці', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (193, 'Кілія', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (194, 'Кіцмань', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (195, 'Кобеляки', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (196, 'Ковель', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (197, 'Кодима', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (198, 'Козятин', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (199, 'Коломия', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (200, 'Комарно', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (201, 'Конотоп', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (202, 'Копичинці', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (203, 'Корець', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (204, 'Коростень', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (205, 'Коростишів', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (206, 'Корсунь-Шевченківський', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (207, 'Корюківка', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (208, 'Косів', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (209, 'Костопіль', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (210, 'Костянтинівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (211, 'Краматорськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (212, 'Красилів', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (213, 'Красногорівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (214, 'Красноград', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (215, 'Красноперекопськ (Яни Капу)', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (216, 'Кременець', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (217, 'Кременчук', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (218, 'Кремінна', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (219, 'Кривий Ріг', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (220, 'Кролевець', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (221, 'Кропивницький', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (222, 'Куп''янськ', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (223, 'Курахове', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (224, 'Ладижин', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (225, 'Ланівці', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (226, 'Лебедин', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (227, 'Лиман', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (228, 'Липовець', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (229, 'Лисичанськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (230, 'Лозова', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (231, 'Лохвиця', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (232, 'Лубни', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (233, 'Луганськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (234, 'Лутугине', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (235, 'Луцьк', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (236, 'Львів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (237, 'Любомль', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (238, 'Люботин', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (239, 'Макіївка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (240, 'Мала Виска', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (241, 'Малин', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (242, 'Марганець', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (243, 'Маріуполь', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (244, 'Мар''їнка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (245, 'Мелітополь', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (246, 'Мена', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (247, 'Мерефа', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (248, 'Миколаїв', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (249, 'Миколаїв', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (250, 'Миколаївка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (251, 'Миргород', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (252, 'Мирноград', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (253, 'Миронівка', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (254, 'Міусинськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (255, 'Могилів-Подільський', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (256, 'Молодогвардійськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (257, 'Молочанськ', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (258, 'Монастириська', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (259, 'Монастирище', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (260, 'Моршин', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (261, 'Моспине', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (262, 'Мостиська', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (263, 'Мукачево', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (264, 'Надвірна', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (265, 'Немирів', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (266, 'Нетішин', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (267, 'Ніжин', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (268, 'Нікополь', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (269, 'Нова Каховка', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (270, 'Нова Одеса', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (271, 'Новгород-Сіверський', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (272, 'Новий Буг', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (273, 'Новий Калинів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (274, 'Новий Розділ', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (275, 'Новоазовськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (276, 'Нововолинськ', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (277, 'Новоград-Волинський', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (278, 'Новогродівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (279, 'Новодністровськ', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (280, 'Новодружеськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (281, 'Новомиргород', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (282, 'Новомосковськ', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (283, 'Новоселиця', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (284, 'Новоукраїнка', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (285, 'Новояворівськ', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (286, 'Носівка', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (287, 'Обухів', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (288, 'Овруч', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (289, 'Одеса', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (290, 'Олевськ', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (291, 'Олександрівськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (292, 'Олександрія', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (293, 'Олешки', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (294, 'Оріхів', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (295, 'Остер', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (296, 'Острог', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (297, 'Охтирка', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (298, 'Очаків', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (299, 'Павлоград', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (300, 'Первомайськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (301, 'Первомайськ', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (302, 'Первомайський', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (303, 'Перевальськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (304, 'Перемишляни', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (305, 'Перечин', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (306, 'Перещепине', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (307, 'Переяслав', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (308, 'Першотравенськ', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (309, 'Петрово-Красносілля', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (310, 'Пирятин', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (311, 'Південне', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (312, 'Підгайці', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (313, 'Підгородне', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (314, 'Погребище', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (315, 'Подільськ', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (316, 'Покров', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (317, 'Покровськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (318, 'Пологи', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (319, 'Полонне', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (320, 'Полтава', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (321, 'Помічна', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (322, 'Попасна', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (323, 'Почаїв', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (324, 'Привілля', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (325, 'Прилуки', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (326, 'Приморськ', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (327, 'Прип''ять', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (328, 'Пустомити', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (329, 'Путивль', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (330, 'П''ятихатки', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (331, 'Рава-Руська', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (332, 'Радехів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (333, 'Радивилів', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (334, 'Радомишль', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (335, 'Рахів', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (336, 'Рені', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (337, 'Решетилівка', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (338, 'Ржищів', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (339, 'Рівне', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (340, 'Ровеньки', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (341, 'Рогатин', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (342, 'Родинське', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (343, 'Рожище', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (344, 'Роздільна', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (345, 'Ромни', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (346, 'Рубіжне', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (347, 'Рудки', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (348, 'Саки', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (349, 'Самбір', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (350, 'Сарни', 17);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (351, 'Свалява', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (352, 'Сватове', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (353, 'Світловодськ', 11);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (354, 'Світлодарськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (355, 'Святогірськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (356, 'Севастополь', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (357, 'Селидове', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (358, 'Семенівка', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (359, 'Середина-Буда', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (360, 'Сєвєродонецьк', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (361, 'Синельникове', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (362, 'Сіверськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (363, 'Сімферополь', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (364, 'Скадовськ', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (365, 'Скалат', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (366, 'Сквира', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (367, 'Сколе', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (368, 'Славута', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (369, 'Славутич', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (370, 'Слов''янськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (371, 'Сміла', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (372, 'Снігурівка', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (373, 'Сніжне', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (374, 'Сновськ', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (375, 'Снятин', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (376, 'Сокаль', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (377, 'Сокиряни', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (378, 'Соледар', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (379, 'Сорокине', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (380, 'Соснівка', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (381, 'Старий Крим', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (382, 'Старий Самбір', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (383, 'Старобільськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (384, 'Старокостянтинів', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (385, 'Стебник', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (386, 'Сторожинець', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (387, 'Стрий', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (388, 'Судак', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (389, 'Судова Вишня', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (390, 'Суми', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (391, 'Суходільськ', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (392, 'Таврійськ', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (393, 'Тальне', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (394, 'Тараща', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (395, 'Татарбунари', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (396, 'Теплодар', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (397, 'Теребовля', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (398, 'Тернівка', 4);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (399, 'Тернопіль', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (400, 'Тетіїв', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (401, 'Тисмениця', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (402, 'Тлумач', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (403, 'Токмак', 8);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (404, 'Торецьк', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (405, 'Тростянець', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (406, 'Трускавець', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (407, 'Тульчин', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (408, 'Турка', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (409, 'Тячів', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (410, 'Угнів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (411, 'Ужгород', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (412, 'Узин', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (413, 'Українка', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (414, 'Українськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (415, 'Умань', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (416, 'Устилуг', 3);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (417, 'Фастів', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (418, 'Феодосія', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (419, 'Харків', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (420, 'Харцизьк', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (421, 'Херсон', 21);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (422, 'Хирів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (423, 'Хмельницький', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (424, 'Хмільник', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (425, 'Ходорів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (426, 'Хорол', 16);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (427, 'Хоростків', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (428, 'Хотин', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (429, 'Хрестівка', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (430, 'Христинівка', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (431, 'Хрустальний', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (432, 'Хуст', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (433, 'Часів Яр', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (434, 'Червоноград', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (435, 'Черкаси', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (436, 'Чернівці', 24);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (437, 'Чернігів', 25);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (438, 'Чигирин', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (439, 'Чистякове', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (440, 'Чоп', 7);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (441, 'Чорнобиль', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (442, 'Чорноморськ', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (443, 'Чортків', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (444, 'Чугуїв', 20);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (445, 'Чуднів', 6);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (446, 'Шаргород', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (447, 'Шахтарськ', 5);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (448, 'Шепетівка', 22);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (449, 'Шостка', 18);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (450, 'Шпола', 23);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (451, 'Шумськ', 19);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (452, 'Щастя', 12);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (453, 'Щолкіне', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (454, 'Южне', 15);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (455, 'Южноукраїнськ', 14);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (456, 'Яворів', 13);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (457, 'Яготин', 10);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (458, 'Ялта', 1);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (459, 'Ямпіль', 2);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (460, 'Яремче', 9);
INSERT INTO public.cities (city_id, name, region_id)
VALUES (461, 'Ясинувата', 5);

INSERT INTO public.institutions (institution_id, name, address, city_id)
VALUES (1, 'Адоніс', 'вул. Дніпровська набережна, 26К', 190);
INSERT INTO public.institutions (institution_id, name, address, city_id)
VALUES (2, 'Новий зір', 'вул. Коперника, 12Д', 190);
INSERT INTO public.institutions (institution_id, name, address, city_id)
VALUES (3, 'Інномед', 'ш. Хмельницьке, 96Г', 81);
INSERT INTO public.institutions (institution_id, name, address, city_id)
VALUES (4, 'Медікум', 'пр-т. Д. Яворницького, 59', 119);
INSERT INTO public.institutions (institution_id, name, address, city_id)
VALUES (5, 'Medical Idea', 'вул. Короленка, 54', 55);

INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (11, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        12, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (12, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        13, 1, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (13, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        14, 5, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (14, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        15, 2, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (15, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        16, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (16, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        17, 1, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (17, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        18, 5, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (18, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        19, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (19, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        20, 3, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (20, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        21, 1, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (21, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        22, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (22, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        23, 2, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (23, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        24, 2, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (24, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        25, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (25, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        26, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (26, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        27, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (31, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        32, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (32, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        33, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (1, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        1, 1, 1, 0, 5, 5);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (2, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        2, 1, 1, 0, 4, 4);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (3, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        3, 2, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (4, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        4, 1, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (27, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        28, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (28, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        29, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (7, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        7, 5, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (8, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        8, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (33, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        34, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (34, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        35, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (5, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        5, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (6, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        6, 4, 1, 0, 4, 4);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (9, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        10, 5, 1, 0, 14, 14);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (10, 'Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        11, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (29, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        30, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (30, 'Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        31, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (35, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        36, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (doctor_id, qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES (36, 'Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        37, 5, 1, 0, 1, 1);

INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (1, 1);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (1, 2);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (1, 5);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (2, 2);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (2, 5);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (3, 1);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (3, 2);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (4, 1);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (4, 5);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (5, 3);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (6, 4);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (7, 1);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (7, 5);
INSERT INTO public.doctors_institutions (doctor_id, institution_id)
VALUES (8, 3);

INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (1, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (1, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (1, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (2, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (2, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (2, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (3, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (3, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (3, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (4, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (4, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (4, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (5, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (5, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (5, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (6, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (6, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (6, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (7, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (7, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (7, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (8, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (8, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (8, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (9, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (10, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (11, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (12, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (13, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (14, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (15, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (16, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (17, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (18, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (19, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (20, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (21, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (22, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (23, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (24, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (25, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (26, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (27, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (28, 1);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (29, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (30, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (31, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (32, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (33, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (34, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (35, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (36, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (9, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (10, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (11, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (12, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (13, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (14, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (15, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (16, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (17, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (18, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (19, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (20, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (21, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (22, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (23, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (24, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (25, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (26, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (27, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (28, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (29, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (30, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (31, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (32, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (33, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (34, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (35, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (36, 2);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (9, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (10, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (11, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (12, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (13, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (14, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (15, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (16, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (17, 3);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (18, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (19, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (20, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (21, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (22, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (23, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (24, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (25, 4);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (26, 7);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (27, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (28, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (29, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (30, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (31, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (32, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (33, 6);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (34, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (35, 5);
INSERT INTO public.doctors_directions (doctor_id, direction_id)
VALUES (36, 5);

INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 2, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (2, 5, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'MODERATION_SECOND_SIGN', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (3, 4, 4, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'ARCHIVED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 6, 2, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (5, 1, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'MODERATION_FIRST_SIGN', true, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (6, 4, 3, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'ARCHIVED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 1, 2, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (8, 7, 2, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'DRAFT', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (9, 7, 2, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.', 'DRAFT',
        false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 2, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (11, 2, 1, 'Fermentum iaculis',
        'Aenean non lobortis arcu. Sed ac enim in metus tincidunt vestibulum ut feugiat purus. Fusce eu velit auctor, facilisis urna sit amet, venenatis magna. Mauris lacinia ultricies pharetra. Phasellus vulputate sodales nulla, quis maximus risus pulvinar viverra. Cras et erat id tellus congue rhoncus nec nec ipsum. Mauris ultrices, neque quis lobortis hendrerit, turpis ipsum porttitor tortor, eget dignissim ante sapien sed sapien. Maecenas quis purus rutrum, tempor neque ut, imperdiet enim. Aenean bibendum dui ac ligula ultricies, vitae pulvinar massa tincidunt.',
        'PUBLISHED', false, '2019-11-25 13:42:34.609474', '2019-11-26 13:42:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (12, 1, 1, 'Fermentum iaculis',
        'Duis lacus nisl, dictum at auctor et, dignissim ac diam. Morbi laoreet sit amet ante quis vehicula. Proin sodales sem ac lacus sodales, id pellentesque nibh tempus. Phasellus porttitor, leo ac pulvinar malesuada, est mauris dignissim nibh, ac interdum enim lorem quis justo. Nam porttitor vitae odio ut posuere. In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam ultricies lacus in dapibus egestas. Quisque at neque condimentum, auctor ante consectetur, ullamcorper ex. Cras dolor diam, imperdiet sit amet dui eu, ultricies dapibus velit. Duis odio dolor, sagittis a libero sit amet, tincidunt gravida quam. Nunc congue ultrices tortor vitae hendrerit. Proin aliquam posuere leo, nec feugiat diam. Nunc efficitur porttitor auctor. Morbi bibendum, risus in dapibus mattis, nunc massa malesuada massa, scelerisque pharetra purus lorem sit amet velit.',
        'PUBLISHED', false, '2019-10-25 12:42:34.609474', '2019-11-26 13:44:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (13, 3, 1, 'Fermentum iaculis',
        'Vestibulum lacinia efficitur felis eget dapibus. Curabitur ut ante ante. Etiam tincidunt enim nunc, non tempus tortor mollis vitae. Sed bibendum risus quis faucibus pharetra. Praesent at lacus nibh. Donec mollis pharetra dolor quis commodo. Nunc eget dui luctus, volutpat velit vel, egestas enim. Morbi semper congue ante, ut tincidunt urna efficitur at. Nunc vulputate est sed mi hendrerit porttitor. Nulla ut nibh vitae augue malesuada commodo vitae vitae magna. Fusce sollicitudin faucibus nisl, ac sollicitudin orci tincidunt ut. Donec aliquet faucibus elit, quis consectetur tortor pulvinar et. Nunc facilisis ante ante, vitae viverra ligula semper non.',
        'PUBLISHED', true, '2019-11-20 14:42:34.609474', '2019-12-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (14, 4, 1, 'Fermentum iaculis',
        'Ut posuere accumsan diam, non vestibulum mauris aliquet non. Cras sed nisl id orci imperdiet pellentesque. Duis facilisis porttitor varius. Mauris quis leo facilisis, scelerisque massa non, ornare turpis. Donec ultricies eget massa vitae pellentesque. Nunc rhoncus faucibus hendrerit. Pellentesque et maximus leo, ac posuere nunc. Cras vitae tincidunt nibh. Ut non erat vel felis congue iaculis.',
        'PUBLISHED', true, '2020-01-20 14:42:34.609474', '2020-02-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (15, 5, 1, 'Fermentum iaculis',
        'Suspendisse vel ex venenatis, euismod augue at, auctor velit. Sed fringilla, arcu in pulvinar condimentum, urna nulla iaculis felis, in ornare orci turpis non nibh. Pellentesque turpis ante, hendrerit sed tempor elementum, tristique quis justo. Ut non dignissim orci. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut vel augue nec magna cursus bibendum at eu eros. Phasellus dui ligula, accumsan sit amet tincidunt nec, ornare vel metus. Proin justo mauris, ullamcorper ut euismod malesuada, iaculis a lectus. Aliquam maximus laoreet justo vel viverra. Curabitur at lacus nisl. Nunc tincidunt faucibus dolor eu mollis. Mauris id sem id lorem luctus pretium nec sit amet turpis. Maecenas eu leo sit amet metus commodo aliquet sit amet vitae lectus. Cras finibus luctus urna a ullamcorper.',
        'PUBLISHED', true, '2020-02-20 14:42:34.609474', '2020-03-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (29, 10, 1, 'Ninth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-14 11:59:53.000000', '2020-12-14 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (16, 6, 1, 'Fermentum iaculis',
        'Quisque sollicitudin orci sollicitudin sapien finibus tempus. Nulla sapien urna, viverra quis enim posuere, consectetur laoreet elit. Nulla a pretium diam. Nulla facilisi. Sed ipsum ante, dictum non justo sit amet, commodo ultricies dolor. Etiam sed nunc vel justo tincidunt varius. Etiam in pretium lectus. Donec porttitor lobortis sem, eget consequat metus accumsan ac. Ut ex dolor, efficitur sit amet sodales in, vulputate quis tortor. Nunc sit amet nisl tortor. Sed finibus feugiat quam, eget tincidunt turpis vestibulum id. Mauris maximus ex leo, a egestas mauris sollicitudin in. Aenean ut arcu pellentesque eros efficitur posuere. Nam lobortis accumsan suscipit. Sed velit est, aliquam nec odio in, sodales facilisis ex.',
        'PUBLISHED', true, '2020-03-20 14:42:34.609474', '2020-04-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (17, 7, 1, 'Fermentum iaculis',
        'Cras at est ex. Aliquam erat volutpat. Donec ultricies dolor nec justo euismod pretium. Donec elementum vestibulum feugiat. Fusce ullamcorper sapien sit amet venenatis convallis. Sed sagittis interdum sapien ac interdum. Praesent porttitor condimentum lacus, ac aliquam dui pulvinar accumsan.',
        'PUBLISHED', true, '2020-04-20 14:42:34.609474', '2020-05-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (18, 8, 1, 'Fermentum iaculis',
        'Praesent nec maximus sem. Ut imperdiet, libero sit amet porta vulputate, dolor erat pretium neque, in efficitur eros enim in enim. In non pretium urna. Suspendisse quis ante ut erat pulvinar accumsan. Integer molestie ex eget libero tincidunt, sit amet ullamcorper mauris fermentum. Donec porta posuere ornare. Etiam congue quis lacus id volutpat. Integer scelerisque a dolor non tristique. Sed nisl sapien, tincidunt sit amet posuere ut, bibendum at velit. Proin eget risus in risus accumsan accumsan pharetra rutrum leo. Vivamus porta lectus leo, vel tincidunt libero rhoncus vitae. Sed et dolor nec tellus ornare bibendum at vel mi. Donec pellentesque placerat fringilla.',
        'PUBLISHED', true, '2020-05-20 14:42:34.609474', '2020-06-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (20, 10, 1, 'Fermentum iaculis',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Proin orci lectus, sodales sed varius non, fringilla non metus. Nam tincidunt libero eu tincidunt lacinia. Sed scelerisque metus at purus pellentesque, eu elementum mauris tempor. Mauris metus mauris, convallis in porttitor sit amet, fermentum vel ipsum. Sed hendrerit volutpat eros, quis commodo ligula aliquet vitae. Nullam gravida quam et ligula facilisis, a volutpat sapien viverra. Integer non porta enim, in blandit leo. Etiam tempor elit lacus, ut venenatis massa rhoncus at. Etiam eu massa rutrum, aliquet arcu at, lobortis ligula. In sit amet nisi nec ex congue lobortis vitae at risus. Praesent elementum accumsan magna, eget auctor ex scelerisque non. Fusce posuere, metus varius malesuada varius, sem velit varius mauris, eget scelerisque mi turpis sit amet dui. Cras pellentesque pulvinar tincidunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
        'PUBLISHED', true, '2020-07-20 14:42:34.609474', '2020-08-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (21, 10, 1, 'First therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-06 11:59:53.000000', '2020-12-06 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (22, 10, 1, 'Second therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-07 11:59:53.000000', '2020-12-07 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (23, 10, 1, 'Third therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-08 11:59:53.000000', '2020-12-08 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (24, 10, 1, 'Fourth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-09 11:59:53.000000', '2020-12-09 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (25, 10, 1, 'Fifth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-10 11:59:53.000000', '2020-12-10 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (26, 10, 1, 'Sixth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-11 11:59:53.000000', '2020-12-11 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (27, 10, 1, 'Seventh therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-12 11:59:53.000000', '2020-12-12 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (28, 10, 1, 'Eighth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-13 11:59:53.000000', '2020-12-13 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (30, 10, 1, 'Tenth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-15 11:59:53.000000', '2020-12-15 11:59:53.000000', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (19, 10, 1, 'Fermentum iaculis',
        'Duis finibus risus et hendrerit fringilla. Donec varius nisi massa, eu facilisis velit iaculis id. Sed porta lectus eget lectus auctor, at iaculis risus consequat. Morbi lacinia pretium est, pulvinar porttitor lorem facilisis ut. Etiam in nisi justo. Vivamus luctus efficitur mauris, ut placerat lorem imperdiet a. Phasellus condimentum ante quis leo tempor semper. Nam sit amet faucibus orci, et egestas tortor. Maecenas ornare a lorem nec mattis. Etiam ac dapibus quam. Proin consequat laoreet rutrum. Cras et congue ligula.',
        'PUBLISHED', true, '2020-06-20 14:42:34.609474', '2020-07-26 13:45:34.609474', null);
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (31, 1, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (32, 2, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (33, 3, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (34, 4, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (35, 5, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (36, 6, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (37, 7, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (38, 8, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (39, 10, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (40, 11, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (41, 12, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (42, 13, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (43, 14, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (44, 15, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (45, 16, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (46, 17, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (47, 18, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (48, 19, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (49, 20, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (50, 21, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (51, 22, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (52, 23, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (53, 24, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (54, 25, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (55, 26, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (56, 27, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (57, 28, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (58, 29, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (59, 30, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (60, 31, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (61, 32, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (62, 33, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (63, 34, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (64, 35, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (65, 36, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (66, 37, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (67, 11, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (68, 10, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (69, 8, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (70, 7, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (71, 6, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (72, 5, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (73, 4, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (74, 3, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (75, 2, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (76, 1, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (77, 21, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (78, 20, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (79, 19, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (80, 18, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (81, 17, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (82, 16, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (83, 15, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (84, 14, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (85, 13, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (86, 12, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (87, 15, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (88, 14, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (89, 13, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (post_id, author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (90, 12, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');

INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (1, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (1, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (1, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (2, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (2, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (2, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (3, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (3, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (3, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (4, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (4, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (4, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (5, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (5, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (5, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (6, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (6, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (6, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (7, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (7, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (7, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (8, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (8, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (8, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (9, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (10, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (11, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (12, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (13, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (14, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (15, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (16, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (17, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (18, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (19, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (20, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (21, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (22, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (23, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (24, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (25, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (26, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (27, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (28, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (29, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (9, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (10, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (11, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (12, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (13, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (14, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (15, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (16, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (17, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (18, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (19, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (20, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (21, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (22, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (23, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (24, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (25, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (26, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (27, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (28, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (29, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (9, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (10, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (11, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (12, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (13, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (14, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (15, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (16, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (17, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (18, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (19, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (20, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (21, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (22, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (23, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (24, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (25, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (26, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (27, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (28, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (29, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (30, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (30, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (30, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (31, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (31, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (31, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (32, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (32, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (32, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (33, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (33, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (33, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (34, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (34, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (34, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (35, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (35, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (35, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (36, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (36, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (36, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (37, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (37, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (37, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (38, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (38, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (38, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (39, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (39, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (39, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (40, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (40, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (40, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (41, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (41, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (41, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (42, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (42, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (42, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (43, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (43, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (43, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (44, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (44, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (44, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (45, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (45, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (45, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (46, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (46, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (46, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (47, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (47, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (47, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (48, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (48, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (48, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (49, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (49, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (49, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (50, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (50, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (50, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (51, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (51, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (51, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (52, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (52, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (52, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (53, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (53, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (53, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (54, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (54, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (54, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (55, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (55, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (55, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (56, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (56, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (56, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (57, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (57, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (57, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (58, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (58, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (58, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (59, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (59, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (59, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (60, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (60, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (60, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (61, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (61, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (61, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (62, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (62, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (62, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (63, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (63, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (63, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (64, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (64, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (64, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (65, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (65, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (65, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (66, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (66, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (66, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (67, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (67, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (67, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (68, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (68, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (68, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (69, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (69, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (69, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (70, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (70, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (70, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (71, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (71, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (71, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (72, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (72, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (72, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (73, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (73, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (73, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (74, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (74, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (74, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (75, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (75, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (75, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (76, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (76, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (76, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (77, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (77, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (77, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (78, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (78, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (78, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (79, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (79, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (79, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (80, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (80, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (80, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (81, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (81, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (81, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (82, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (82, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (82, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (83, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (83, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (83, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (84, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (84, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (84, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (85, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (85, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (85, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (86, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (86, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (86, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (87, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (87, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (87, 7);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (88, 1);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (88, 2);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (88, 3);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (89, 4);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (89, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (89, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (90, 5);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (90, 6);
INSERT INTO public.posts_directions (post_id, direction_id)
VALUES (90, 7);

INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (1, 'PROFILE_IMAGE', 'https://i.imgur.com/I80W1Q0.png', 1);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (2, 'PROFILE_IMAGE', 'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png', 2);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (3, 'PROFILE_IMAGE', 'https://html5css.ru/howto/img_avatar2.png', 3);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (4, 'PROFILE_IMAGE',
        'https://www.publicdomainpictures.net/pictures/270000/velka/avatar-people-person-business-u.jpg', 4);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (5, 'PROFILE_IMAGE',
        'https://cnet2.cbsistatic.com/img/liJ9UZA87zs1viJiuEfVnL7YYfw=/940x0/2020/05/18/5bac8cc1-4bd5-4496-a8c3-66a6cd12d0cb/fb-avatar-2.jpg',
        5);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (6, 'PROFILE_IMAGE', 'https://www.striketwosummit.com/wp-content/uploads/2019/04/avatar-1606916_960_720.png', 6);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (7, 'PROFILE_IMAGE', 'https://www.pinclipart.com/picdir/middle/155-1559325_female-avatar-clipart.png', 7);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (8, 'PROFILE_IMAGE',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR3F3e5C2_4KVWpSSvmBDVb8iPGyRnDB5DVPA&usqp=CAU', 8);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (9, 'PROFILE_IMAGE', 'https://pickaface.net/gallery/avatar/20130319_083314_1174_admin.png', 9);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (10, 'POST_IMAGE',
        'https://www.afd.fr/sites/afd/files/styles/visuel_principal/public/2019-10-09-27-46/flickr-marco-verch.jpg?itok=XH4x7-Y4',
        4);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (11, 'POST_IMAGE', 'https://www.everest.ua/wp-content/uploads/2020/04/6_result-4.jpg', 1);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (12, 'POST_IMAGE',
        'https://epsilon.aeon.co/images/afef287f-dd6f-4a6a-b8a6-4f0a09330657/idea_sized-kendal-l4ikccachoc-unsplash.jpg',
        1);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (13, 'POST_IMAGE',
        'https://blogs.biomedcentral.com/on-medicine/wp-content/uploads/sites/6/2018/06/2017-08-30-07-09-32-1000x667-620x342.jpg',
        4);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (14, 'POST_VIDEO', 'https://www.youtube.com/watch?v=YdzH6iIY1PQ&ab_channel=%D0%A2%D0%A1%D0%9D', 7);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (15, 'POST_VIDEO',
        'https://www.youtube.com/watch?v=vEabuIRTJ2M&ab_channel=%D0%9C%D0%B0%D0%BC%D0%B5%D0%B4%D1%8B%D1%87-%D0%96%D0%B8%D0%B7%D0%BD%D1%8C%D1%85%D0%B8%D1%80%D1%83%D1%80%D0%B3%D0%B0',
        1);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (16, 'POST_VIDEO',
        'https://www.youtube.com/watch?v=a24CJXQJdyU&ab_channel=%D0%92%D1%96%D0%BA%D0%BD%D0%B0-%D0%BD%D0%BE%D0%B2%D0%B8%D0%BD%D0%B8',
        1);
INSERT INTO public.sources (source_id, type, value, user_id)
VALUES (17, 'POST_VIDEO',
        'https://www.youtube.com/watch?v=03yciZyoVRI&ab_channel=%D0%9B%D1%96%D0%BA%D0%B0%D1%80%D0%AE%D1%80%D1%96%D0%B9%D0%A1%D0%BE%D0%BB%D0%BE%D0%B4%D0%BE%D0%B2',
        7);

INSERT INTO public.posts_sources (post_id, source_id)
VALUES (5, 11);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (5, 12);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (6, 13);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (6, 10);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (7, 15);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (8, 14);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (9, 17);
INSERT INTO public.posts_sources (post_id, source_id)
VALUES (10, 16);

INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (1, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (1, 2);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (1, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (1, 6);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (2, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (2, 2);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (3, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (3, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (4, 2);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (4, 3);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (4, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (4, 5);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (5, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (6, 2);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (6, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (6, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (6, 3);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (7, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (7, 5);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (8, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (8, 3);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (8, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (9, 2);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (9, 4);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (10, 1);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (10, 3);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (10, 5);
INSERT INTO public.posts_tags (post_id, tag_id)
VALUES (10, 6);

INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (1, 'LOCAL', 'ivan@mail.com', '1', 1);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (2, 'LOCAL', 'andrii@mail.com', '2', 2);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (3, 'LOCAL', 'olena@mail.com', '3', 3);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (4, 'LOCAL', 'dmytro@mail.com', '4', 4);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (5, 'LOCAL', 'kateryna@mail.com', '5', 5);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (6, 'LOCAL', 'oleh@mail.com', '6', 6);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (7, 'LOCAL', 'tetyana@mail.com', '7', 7);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (8, 'LOCAL', 'maryna@mail.com', '8', 8);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (9, 'LOCAL', 'admin@mail.com', '9', 9);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (10, 'LOCAL', 'stepan@mail.com', '10', 10);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (11, 'LOCAL', 'danylo@mail.com', '11', 11);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (12, 'LOCAL', 'petro@mail.com', '12', 12);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (13, 'LOCAL', 'masha@mail.com', '13', 13);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (14, 'LOCAL', 'mykola@mail.com', '14', 14);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (15, 'LOCAL', 'taras@mail.com', '15', 15);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (16, 'LOCAL', 'lesia@mail.com', '16', 16);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (17, 'LOCAL', 'bohdan@mail.com', '17', 17);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (18, 'LOCAL', 'eva@mail.com', '18', 18);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (19, 'LOCAL', 'zahar@mail.com', '19', 19);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (20, 'LOCAL', 'nazar@mail.com', '20', 20);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (21, 'LOCAL', 'zhan@mail.com', '21', 21);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (22, 'LOCAL', 'nikon@mail.com', '22', 22);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (23, 'LOCAL', 'matviy@mail.com', '23', 23);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (24, 'LOCAL', 'regina@mail.com', '24', 24);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (25, 'LOCAL', 'fedot@mail.com', '25', 25);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (26, 'LOCAL', 'terentiy@mail.com', '26', 26);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (27, 'LOCAL', 'platon@mail.com', '27', 27);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (28, 'LOCAL', 'ShtukaNovitskaya261@mail.com', '28', 28);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (29, 'LOCAL', 'FadiStalin333@mail.com', '29', 29);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (30, 'LOCAL', 'PamfiyTihomirov752@mail.com', '30', 30);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (31, 'LOCAL', 'FekletinyaShashkova782@mail.com', '31', 31);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (32, 'LOCAL', 'PalanaLitvinova514@mail.com', '32', 32);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (33, 'LOCAL', 'GigranAlekseev32@mail.com', '33', 33);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (34, 'LOCAL', 'KukuriChapko624@mail.com', '34', 34);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (35, 'LOCAL', 'NezdaUshakov899@mail.com', '35', 35);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (36, 'LOCAL', 'MiletiyChernyshov229@mail.com', '36', 36);
INSERT INTO public.providers (provider_id, provider_name, email, user_id_by_provider, user_id)
VALUES (37, 'LOCAL', 'TarzhemanSokolov585@mail.com', '37', 37);

INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (1, 'SAVE_TAG');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (2, 'SAVE_TAG');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'SAVE_OWN_PUBLICATION');
INSERT INTO public.role_permission (role_id, permissions)
VALUES (3, 'SAVE_TAG');