INSERT INTO public.tags (tag)
VALUES ('Профілактика');
INSERT INTO public.tags (tag)
VALUES ('Комплекс');
INSERT INTO public.tags (tag)
VALUES ('Ковід');
INSERT INTO public.tags (tag)
VALUES ('Допомога');
INSERT INTO public.tags (tag)
VALUES ('Міокард');
INSERT INTO public.tags (tag)
VALUES ('ЕКГ');

INSERT INTO public.directions (label, color, name)
VALUES ('Covid-19', '#ef5350', 'covid-19');
INSERT INTO public.directions (label, color, name)
VALUES ('Офтальмологія', '#98ef50', 'ophthalmology');
INSERT INTO public.directions (label, color, name)
VALUES ('Хірургія', '#7aebbf', 'surgery');
INSERT INTO public.directions (label, color, name)
VALUES ('Терапія', '#ffee58', 'therapy');
INSERT INTO public.directions (label, color, name)
VALUES ('Вірусологія', '#da80e8', 'virology');
INSERT INTO public.directions (label, color, name)
VALUES ('Кардіологія', '#00ffff', 'cardiology');
INSERT INTO public.directions (label, color, name)
VALUES ('Педіатрія', '#993333', 'pediatrics');

INSERT INTO public.post_types (name)
VALUES ('Стаття');
INSERT INTO public.post_types (name)
VALUES ('Відео');
INSERT INTO public.post_types (name)
VALUES ('Допис');
INSERT INTO public.post_types (name)
VALUES ('Переклад');

INSERT INTO public.regions (name)
VALUES ('Автономна Республіка Крим');
INSERT INTO public.regions (name)
VALUES ('Вінницька область');
INSERT INTO public.regions (name)
VALUES ('Волинська область');
INSERT INTO public.regions (name)
VALUES ('Дніпропетровська область');
INSERT INTO public.regions (name)
VALUES ('Донецька область');
INSERT INTO public.regions (name)
VALUES ('Житомирська область');
INSERT INTO public.regions (name)
VALUES ('Закарпатська область');
INSERT INTO public.regions (name)
VALUES ('Запорізька область');
INSERT INTO public.regions (name)
VALUES ('Івано-Франківська область');
INSERT INTO public.regions (name)
VALUES ('Київська область');
INSERT INTO public.regions (name)
VALUES ('Кіровоградська область');
INSERT INTO public.regions (name)
VALUES ('Луганська область');
INSERT INTO public.regions (name)
VALUES ('Львівська область');
INSERT INTO public.regions (name)
VALUES ('Миколаївська область');
INSERT INTO public.regions (name)
VALUES ('Одеська область');
INSERT INTO public.regions (name)
VALUES ('Полтавська область');
INSERT INTO public.regions (name)
VALUES ('Рівненська область');
INSERT INTO public.regions (name)
VALUES ('Сумська область');
INSERT INTO public.regions (name)
VALUES ('Тернопільська область');
INSERT INTO public.regions (name)
VALUES ('Харківська область');
INSERT INTO public.regions (name)
VALUES ('Херсонська область');
INSERT INTO public.regions (name)
VALUES ('Хмельницька область');
INSERT INTO public.regions (name)
VALUES ('Черкаська область');
INSERT INTO public.regions (name)
VALUES ('Чернівецька область');
INSERT INTO public.regions (name)
VALUES ('Чернігівська область');

INSERT INTO public.roles (role_name)
VALUES ('Administrator');
INSERT INTO public.roles (role_name)
VALUES ('Moderator');
INSERT INTO public.roles (role_name)
VALUES ('Doctor');

INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('TarzhemanSokolov585@mail.com', '$2y$10$CXUu2az9AJHT23fUcXkGd.x4m.ByDOCb6e0uU8teF/Dgq2lqeHfv2', 'ACTIVE',
        'Таржеман', 'Соколов', '+380631434712', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=70', true,
        3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('MiletiyChernyshov229@mail.com', '$2y$10$SvnpkOhhs4I6J7w3jLcvIe5mWakxJ3ADtquH5Tv0x70rmjPZifTSi', 'ACTIVE',
        'Милетий', 'Чернышов', '+380637931441', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=68', true,
        3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('NezdaUshakov899@mail.com', '$2y$10$MqU8B7EDd7aNo/CTmr0OKeIkRQHObRBEfxMbY2LfDbvZKmsliZ3ia', 'ACTIVE',
        'Незда', 'Ушаков', '+380637687420', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=67', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('KukuriChapko624@mail.com', '$2y$10$FHxAyHKwM2AqUF582/FFhuuf.OzU6ECIpwTpMxLCeEyz0rht.hjcG', 'ACTIVE',
        'Кукури', 'Чапко', '+380631793743', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=66', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('GigranAlekseev32@mail.com', '$2y$10$/m6y5AVzG9yxmthcQ7M1FOsrh8EqZmFvGZjoi7lDyy04eUqQ0gnqe', 'ACTIVE',
        'Гигран', 'Алексеев', '+380634724237', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=65', true,
        3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('PalanaLitvinova514@mail.com', '$2y$10$I2VwJytpzEZi2DPzLPJk.uhcd4WIi/Ek5dMkLjQcurEZWqI0MxTl6', 'ACTIVE',
        'Палана', 'Литвинова', '+380633484351', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=44', true,
        3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('regina@mail.com', '$2y$10$SxF6UMIS1cn7Wi76GwMBFedgGukB9pe3XeL8Qz9pRfUNw3BtD.CDG', 'ACTIVE', 'Регіна',
        'Регіненко', '+380956765669', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=27', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('taras@mail.com', '$2y$10$dCSLVzPoFC7fAfFdgCFayui2u3Tt9Z6IXEZZkCKzuXFvqyWB3cmUO', 'ACTIVE', 'Тарас',
        'Шевченко', '+380956098369', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=17', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('mykola@mail.com', '$2y$10$IaiLTkeqRVSuUtr4weOYmODGOlUp4aBdPQTjQun1klh9CMTsQ12Zu', 'ACTIVE', 'Микола',
        'Миколенко', '+380956412369', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=15', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('masha@mail.com', '$2y$10$igRjDpb3m9EuDvPiGr.RLuuLyjB0M9Snx3yeGW25zprYceSnBNfMq', 'ACTIVE', 'Марія',
        'Марієнко', '+380956456969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=16', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('danylo@mail.com', '$2y$10$dhLh3iKYde/riLznRRU1duits0zbiAFQ46l5AVRhDddJtUBHm3OHW', 'ACTIVE', 'Данило',
        'Даниленко', '+380961233969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=13', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('stepan@mail.com', '$2y$10$CQdJOp/Jw5LmRoUt0W7wSO6lE4N0hM3k0gr1mCI8w8ZOp8ERZcjzO', 'ACTIVE', 'Степан',
        'Степанов', '+380969123969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=12', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('maryna@mail.com', '$2y$10$9R5cJzKJEsP4sjMkH7yHLe6miXbIfY7Jh3xHf8eC4h2jLYH6fGof2', 'ACTIVE', 'Марина',
        'Вовк', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=5', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('oleh@mail.com', '$2y$10$VSWtNZs6WLP5FvAjK.B/JOSLkkcTet9EBHD.PCBbcE1NyyFiVHuA6', 'ACTIVE', 'Олег',
        'Петренко', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=11', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('kateryna@mail.com', '$2y$10$oblwylAYFcVWCXINBCNH1egDuBSy5YGdiyMvN0wM8trelRuy45BU2', 'ACTIVE', 'Катерина',
        'Кравченко', '+380989898989', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=9', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('dmytro@mail.com', '$2y$10$7BhpXBFMGobXWpNj31XyWeezGYlx.OxTKVOqy34Nl/qVd6.50j/LS', 'ACTIVE', 'Дмитро',
        'Степаненко', '+380444444444', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=8', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('olena@mail.com', '$2y$10$EGlzdsCMezKEBDuXsXsGV.REQRsfWPzFecRhnOZjbLl/HTQMbZx7G', 'ACTIVE', 'Олена',
        'Шевченко', '+380939393939', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=1', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('andrii@mail.com', '$2y$10$ZelYHG3iwVCJVvp0dimraerFaYdABop.SPBwUkW7RsiDMA9.h6XTa', 'ACTIVE', 'Андрій',
        'Петров', '+380505050505', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=7', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('ivan@mail.com', '$2y$10$ishgf6hBdlEQwE8Ld1ktkOOPsINMgE7CviFi1qxRaiOgvUdg3RCTy', 'ACTIVE', 'Іван', 'Іванов',
        '+380969696969', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=3', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('FekletinyaShashkova782@mail.com', '$2y$10$6PL9y4SFgNf014aGOAsM1u9CVA.9q08tPDDe/C8.E9WXl0B0oWk3G', 'ACTIVE',
        'Феклетинья', 'Шашкова', '+380639220084', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=48',
        true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('PamfiyTihomirov752@mail.com', '$2y$10$qoMjXIOaovHvpEHraY5pw.q.FwPaXm2PdpU83uM1GemK4dgPD6rSq', 'ACTIVE',
        'Памфий', 'Тихомиров', '+380635701294', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=59', true,
        3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('FadiStalin333@mail.com', '$2y$10$Qk.6cy.s7uTNKQGYFeF9/urECrBpZ4NtLEN9Hzjj0g7soVRzcGu1u', 'ACTIVE', 'Фади',
        'Сталин', '+380636962951', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=58', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('ShtukaNovitskaya261@mail.com', '$2y$10$59GRRruj9n/T4wqElz.IEOXrhwJDoPaz8FdJQJuk4KM.iisikn0lW', 'ACTIVE',
        'Щука', 'Новицкая', '+380632405186', '2021-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=26', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('platon@mail.com', '$2y$10$pZm26FgFAgGbMna/ut0vu.jtR0PZI9kRWCbF.6HshU8wQ32/rMpH2', 'ACTIVE', 'Платон',
        'Платоненко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=57', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('terentiy@mail.com', '$2y$10$NhzfYhZemyRhR61aPJmtnO1WFv06y2ENyEctQCePx.Ymn6wL6P2h.', 'ACTIVE', 'Терентій',
        'Терентієнко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=56', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('fedot@mail.com', '$2y$10$.oKUlohR31I8wni/Qi8nwu9cJti4P5ddg6oq6FIK4H7r/jmmK44sG', 'ACTIVE', 'Федот',
        'Федотенко', '+380956761119', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=55', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('admin@mail.com', '$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2', 'ACTIVE', 'Gregory',
        'House', '+380939393939', '2021-02-16 03:56:37.332925', null, true, 1);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('matviy@mail.com', '$2y$10$dGfbNZAFW5vAkr50kTJBEO3rUDWJuj8B4dfFkmOS/kBlnGqiKqLHS', 'ACTIVE', 'Матвій',
        'Матвійчук', '+380956098369', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=54', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('nikon@mail.com', '$2y$10$EdUEHquCl7OyAqTtfoT1suL1QYZ/68yH36TNWOFnFj/gU.9TjvZAS', 'ACTIVE', 'Нікон',
        'Ніконенко', '+380956412369', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=53', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('zhan@mail.com', '$2y$10$Dx6cNJ3AGW9xzin3xYLpKuQbDO85WM7zy8t.CIJ7JZJFUY3R.8UKe', 'ACTIVE', 'Жан', 'Жанко',
        '+380956456969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=51', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('nazar@mail.com', '$2y$10$4jMtf0eEjVaV0bJWLEb2OOpzEI8md27557Ei/z9eVqugk4Ueg3LLm', 'ACTIVE', 'Назар',
        'Назаренко', '+380961456969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=52', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('petro@mail.com', '$2y$10$MAU64Cv9xZ629f3oTiTJZeY5LbkV1KrVzQ9BeHE0wymMlSqHjUovS', 'ACTIVE', 'Петро',
        'Петренко', '+380961456969', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=14', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('tetyana@mail.com', '$2y$10$bwaU77GtjK3HfMZjti5Gx.Q.SkqerM0V2.XzxN6AlOLGmI90dsPTu', 'ACTIVE', 'Тетяна',
        'Коваленко', '+380444444444', '2021-02-16 03:56:37.332925', 'https://i.pravatar.cc/300?img=10', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('zahar@mail.com', '$2y$10$hVu0ysEa3BNNi4ZAzcvMxe2A0qb5szLLM2Md/XltMIg54jrVbJ1Ay', 'ACTIVE', 'Захар',
        'Захаренко', '+380961233969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=33', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('eva@mail.com', '$2y$10$9whP9V3MfAs/G9imeF9g4e6UMVotHZzp6cqo9bMleSNXNbwPESbm.', 'ACTIVE', 'Єва', 'Євенко',
        '+380969123969', '2021-02-16 03:56:37.394884', 'https://i.pravatar.cc/300?img=20', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('bohdan@mail.com', '$2y$10$DYodORfhp4RQl/5mW5XUfO4suMshfRW7611eKSnb/4AVdrKwUb6wS', 'ACTIVE', 'Богдан',
        'Хмельницький', '+380956761119', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=18', true, 3);
INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('lesia@mail.com', '$2y$10$TeNyf4iiE7xy0HFHmtRRLu2Q7BH4ceYjYudZpIUJ13qjhvapdjjHe', 'ACTIVE', 'Леся',
        'Українка', '+380956765669', '2021-02-16 03:56:37.368121', 'https://i.pravatar.cc/300?img=19', true, 3);

INSERT INTO public.charities (body, author_id, created_at, modified_at)
VALUES ('Lorem ipsum - 50шт. Ut enim - 10шт.', 2, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (body, author_id, created_at, modified_at)
VALUES ('Ut enim - 5шт. ipsum - 20шт.', 4, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (body, author_id, created_at, modified_at)
VALUES ('Lorem - 500шт. Ut enim - 100шт.', 6, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');
INSERT INTO public.charities (body, author_id, created_at, modified_at)
VALUES ('Ut enim - 10шт. Excepteur sint - 200шт.', 7, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925');

INSERT INTO public.cities (name, region_id)
VALUES ('Авдіївка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Алмазна', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Алупка', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Алушта', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Алчевськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Амвросіївка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Ананьїв', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Андрушівка', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Антрацит', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Апостолове', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Армянськ', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Арциз', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Балаклія', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Балта', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Бар', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Баранівка', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Барвінкове', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Батурин', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Бахмач', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Бахмут', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Бахчисарай', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Баштанка', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Белз', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Бердичів', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Бердянськ', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Берегове', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Бережани', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Березань', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Березівка', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Березне', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Берестечко', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Берислав', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Бершадь', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Бібрка', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Біла Церква', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Білгород-Дністровський', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Білицьке', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Білогірськ', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Білозерське', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Білопілля', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Біляївка', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Благовіщенське', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Бобринець', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Бобровиця', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Богодухів', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Богуслав', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Боково-Хрустальне', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Болград', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Болехів', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Борзна', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Борислав', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Бориспіль', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Борщів', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Боярка', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Бровари', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Броди', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Брянка', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Бунге', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Буринь', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Бурштин', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Буськ', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Буча', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Бучач', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Валки', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Вараш', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Василівка', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Васильків', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Ватутіне', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Вашківці', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Великі Мости', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Верхівцеве', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Верхньодніпровськ', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Вижниця', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Вилкове', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Винники', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Виноградів', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Вишгород', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Вишневе', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Вільногірськ', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Вільнянськ', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Вінниця', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Вовчанськ', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Вознесенівка', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Вознесенськ', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Волноваха', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Володимир-Волинський', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Волочиськ', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Ворожба', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Вуглегірськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Вугледар', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Гадяч', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Гайворон', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Гайсин', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Галич', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Генічеськ', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Герца', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Гірник', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Гірське', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Глиняни', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Глобине', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Глухів', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Гнівань', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Гола Пристань', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Голубівка', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Горішні Плавні', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Горлівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Городенка', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Городище', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Городня', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Городок', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Городок', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Горохів', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Гребінка', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Гуляйполе', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Дебальцеве', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Деражня', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Дергачі', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Джанкой', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Дніпро', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Дніпрорудне', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Добромиль', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Добропілля', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Довжанськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Докучаєвськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Долина', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Долинська', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Донецьк', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Дрогобич', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Дружба', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Дружківка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Дубляни', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Дубно', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Дубровиця', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Дунаївці', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Енергодар', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Євпаторія', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Єнакієве', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Жашків', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Жданівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Жидачів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Житомир', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Жмеринка', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Жовква', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Жовті Води', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Заводське', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Залізне', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Заліщики', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Запоріжжя', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Заставна', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Збараж', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Зборів', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Звенигородка', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Здолбунів', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Зеленодольськ', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Зимогір''я', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Зіньків', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Зміїв', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Знам''янка', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Золоте', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Золотоноша', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Золочів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Зоринськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Зугрес', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Івано-Франківськ', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Ізмаїл', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Ізюм', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Ізяслав', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Іллінці', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Іловайськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Інкерман', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Ірміно', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Ірпінь', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Іршава', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Ічня', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Кагарлик', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Кадіївка', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Калинівка', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Калуш', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Кальміуське', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Камінь-Каширський', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Кам''янець-Подільський', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Кам''янка', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Камянка-Бузька', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Кам''янка-Дніпровська', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Кам''янське', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Канів', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Карлівка', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Каховка', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Керч', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Київ', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Кипуче', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Ківерці', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Кілія', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Кіцмань', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Кобеляки', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Ковель', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Кодима', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Козятин', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Коломия', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Комарно', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Конотоп', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Копичинці', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Корець', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Коростень', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Коростишів', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Корсунь-Шевченківський', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Корюківка', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Косів', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Костопіль', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Костянтинівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Краматорськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Красилів', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Красногорівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Красноград', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Красноперекопськ (Яни Капу)', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Кременець', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Кременчук', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Кремінна', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Кривий Ріг', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Кролевець', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Кропивницький', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Куп''янськ', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Курахове', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Ладижин', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Ланівці', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Лебедин', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Лиман', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Липовець', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Лисичанськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Лозова', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Лохвиця', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Лубни', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Луганськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Лутугине', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Луцьк', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Львів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Любомль', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Люботин', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Макіївка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Мала Виска', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Малин', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Марганець', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Маріуполь', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Мар''їнка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Мелітополь', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Мена', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Мерефа', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Миколаїв', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Миколаїв', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Миколаївка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Миргород', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Мирноград', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Миронівка', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Міусинськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Могилів-Подільський', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Молодогвардійськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Молочанськ', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Монастириська', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Монастирище', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Моршин', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Моспине', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Мостиська', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Мукачево', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Надвірна', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Немирів', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Нетішин', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Ніжин', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Нікополь', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Нова Каховка', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Нова Одеса', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Новгород-Сіверський', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Новий Буг', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Новий Калинів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Новий Розділ', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Новоазовськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Нововолинськ', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Новоград-Волинський', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Новогродівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Новодністровськ', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Новодружеськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Новомиргород', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Новомосковськ', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Новоселиця', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Новоукраїнка', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Новояворівськ', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Носівка', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Обухів', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Овруч', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Одеса', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Олевськ', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Олександрівськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Олександрія', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Олешки', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Оріхів', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Остер', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Острог', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Охтирка', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Очаків', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Павлоград', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Первомайськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Первомайськ', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Первомайський', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Перевальськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Перемишляни', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Перечин', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Перещепине', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Переяслав', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Першотравенськ', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Петрово-Красносілля', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Пирятин', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Південне', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Підгайці', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Підгородне', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Погребище', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Подільськ', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Покров', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Покровськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Пологи', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Полонне', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Полтава', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Помічна', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Попасна', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Почаїв', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Привілля', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Прилуки', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Приморськ', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Прип''ять', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Пустомити', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Путивль', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('П''ятихатки', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Рава-Руська', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Радехів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Радивилів', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Радомишль', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Рахів', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Рені', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Решетилівка', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Ржищів', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Рівне', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Ровеньки', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Рогатин', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Родинське', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Рожище', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Роздільна', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Ромни', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Рубіжне', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Рудки', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Саки', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Самбір', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Сарни', 17);
INSERT INTO public.cities (name, region_id)
VALUES ('Свалява', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Сватове', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Світловодськ', 11);
INSERT INTO public.cities (name, region_id)
VALUES ('Світлодарськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Святогірськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Севастополь', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Селидове', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Семенівка', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Середина-Буда', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Сєвєродонецьк', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Синельникове', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Сіверськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Сімферополь', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Скадовськ', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Скалат', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Сквира', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Сколе', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Славута', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Славутич', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Слов''янськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Сміла', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Снігурівка', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Сніжне', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Сновськ', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Снятин', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Сокаль', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Сокиряни', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Соледар', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Сорокине', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Соснівка', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Старий Крим', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Старий Самбір', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Старобільськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Старокостянтинів', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Стебник', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Сторожинець', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Стрий', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Судак', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Судова Вишня', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Суми', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Суходільськ', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Таврійськ', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Тальне', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Тараща', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Татарбунари', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Теплодар', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Теребовля', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Тернівка', 4);
INSERT INTO public.cities (name, region_id)
VALUES ('Тернопіль', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Тетіїв', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Тисмениця', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Тлумач', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Токмак', 8);
INSERT INTO public.cities (name, region_id)
VALUES ('Торецьк', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Тростянець', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Трускавець', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Тульчин', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Турка', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Тячів', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Угнів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Ужгород', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Узин', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Українка', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Українськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Умань', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Устилуг', 3);
INSERT INTO public.cities (name, region_id)
VALUES ('Фастів', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Феодосія', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Харків', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Харцизьк', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Херсон', 21);
INSERT INTO public.cities (name, region_id)
VALUES ('Хирів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Хмельницький', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Хмільник', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Ходорів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Хорол', 16);
INSERT INTO public.cities (name, region_id)
VALUES ('Хоростків', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Хотин', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Хрестівка', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Христинівка', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Хрустальний', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Хуст', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Часів Яр', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Червоноград', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Черкаси', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Чернівці', 24);
INSERT INTO public.cities (name, region_id)
VALUES ('Чернігів', 25);
INSERT INTO public.cities (name, region_id)
VALUES ('Чигирин', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Чистякове', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Чоп', 7);
INSERT INTO public.cities (name, region_id)
VALUES ('Чорнобиль', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Чорноморськ', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Чортків', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Чугуїв', 20);
INSERT INTO public.cities (name, region_id)
VALUES ('Чуднів', 6);
INSERT INTO public.cities (name, region_id)
VALUES ('Шаргород', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Шахтарськ', 5);
INSERT INTO public.cities (name, region_id)
VALUES ('Шепетівка', 22);
INSERT INTO public.cities (name, region_id)
VALUES ('Шостка', 18);
INSERT INTO public.cities (name, region_id)
VALUES ('Шпола', 23);
INSERT INTO public.cities (name, region_id)
VALUES ('Шумськ', 19);
INSERT INTO public.cities (name, region_id)
VALUES ('Щастя', 12);
INSERT INTO public.cities (name, region_id)
VALUES ('Щолкіне', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Южне', 15);
INSERT INTO public.cities (name, region_id)
VALUES ('Южноукраїнськ', 14);
INSERT INTO public.cities (name, region_id)
VALUES ('Яворів', 13);
INSERT INTO public.cities (name, region_id)
VALUES ('Яготин', 10);
INSERT INTO public.cities (name, region_id)
VALUES ('Ялта', 1);
INSERT INTO public.cities (name, region_id)
VALUES ('Ямпіль', 2);
INSERT INTO public.cities (name, region_id)
VALUES ('Яремче', 9);
INSERT INTO public.cities (name, region_id)
VALUES ('Ясинувата', 5);

INSERT INTO public.institutions (name, address, city_id)
VALUES ('Адоніс', 'вул. Дніпровська набережна, 26К', 190);
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Новий зір', 'вул. Коперника, 12Д', 190);
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Інномед', 'ш. Хмельницьке, 96Г', 81);
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Медікум', 'пр-т. Д. Яворницького, 59', 119);
INSERT INTO public.institutions (name, address, city_id)
VALUES ('Medical Idea', 'вул. Короленка, 54', 55);

INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        12, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        13, 1, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        14, 5, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        15, 2, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        16, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        17, 1, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        18, 5, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        19, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        20, 3, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        21, 1, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        22, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        23, 2, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        24, 2, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        25, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        26, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        27, 1, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        32, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        33, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        1, 1, 1, 0, 5, 5);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        2, 1, 1, 0, 4, 4);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        3, 2, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        4, 1, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        28, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        29, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        7, 5, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        8, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        34, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        35, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        5, 3, 1, 0, 3, 3);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        6, 4, 1, 0, 4, 4);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        10, 5, 1, 0, 14, 14);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар-спеціаліст',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        11, 2, 1, 0, 2, 2);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        30, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Кандидат медичних наук',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        31, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        36, 5, 1, 0, 1, 1);
INSERT INTO public.doctors (qualification, bio, user_id, institution_id, promotion_scale, promotion_level,
                            published_posts, rating)
VALUES ('Лікар вищої категорії',
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

INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (2, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (5, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'MODERATION_SECOND_SIGN', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 4, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'ARCHIVED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (6, 2, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'MODERATION_FIRST_SIGN', true, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 3, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'ARCHIVED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 2, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 2, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'DRAFT', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 2, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.', 'DRAFT',
        false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 2, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.332925', '2021-02-16 03:56:37.332925', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (2, 1, 'Fermentum iaculis',
        'Aenean non lobortis arcu. Sed ac enim in metus tincidunt vestibulum ut feugiat purus. Fusce eu velit auctor, facilisis urna sit amet, venenatis magna. Mauris lacinia ultricies pharetra. Phasellus vulputate sodales nulla, quis maximus risus pulvinar viverra. Cras et erat id tellus congue rhoncus nec nec ipsum. Mauris ultrices, neque quis lobortis hendrerit, turpis ipsum porttitor tortor, eget dignissim ante sapien sed sapien. Maecenas quis purus rutrum, tempor neque ut, imperdiet enim. Aenean bibendum dui ac ligula ultricies, vitae pulvinar massa tincidunt.',
        'PUBLISHED', false, '2019-11-25 13:42:34.609474', '2019-11-26 13:42:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 1, 'Fermentum iaculis',
        'Duis lacus nisl, dictum at auctor et, dignissim ac diam. Morbi laoreet sit amet ante quis vehicula. Proin sodales sem ac lacus sodales, id pellentesque nibh tempus. Phasellus porttitor, leo ac pulvinar malesuada, est mauris dignissim nibh, ac interdum enim lorem quis justo. Nam porttitor vitae odio ut posuere. In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam ultricies lacus in dapibus egestas. Quisque at neque condimentum, auctor ante consectetur, ullamcorper ex. Cras dolor diam, imperdiet sit amet dui eu, ultricies dapibus velit. Duis odio dolor, sagittis a libero sit amet, tincidunt gravida quam. Nunc congue ultrices tortor vitae hendrerit. Proin aliquam posuere leo, nec feugiat diam. Nunc efficitur porttitor auctor. Morbi bibendum, risus in dapibus mattis, nunc massa malesuada massa, scelerisque pharetra purus lorem sit amet velit.',
        'PUBLISHED', false, '2019-10-25 12:42:34.609474', '2019-11-26 13:44:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (3, 1, 'Fermentum iaculis',
        'Vestibulum lacinia efficitur felis eget dapibus. Curabitur ut ante ante. Etiam tincidunt enim nunc, non tempus tortor mollis vitae. Sed bibendum risus quis faucibus pharetra. Praesent at lacus nibh. Donec mollis pharetra dolor quis commodo. Nunc eget dui luctus, volutpat velit vel, egestas enim. Morbi semper congue ante, ut tincidunt urna efficitur at. Nunc vulputate est sed mi hendrerit porttitor. Nulla ut nibh vitae augue malesuada commodo vitae vitae magna. Fusce sollicitudin faucibus nisl, ac sollicitudin orci tincidunt ut. Donec aliquet faucibus elit, quis consectetur tortor pulvinar et. Nunc facilisis ante ante, vitae viverra ligula semper non.',
        'PUBLISHED', true, '2019-11-20 14:42:34.609474', '2019-12-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 1, 'Fermentum iaculis',
        'Ut posuere accumsan diam, non vestibulum mauris aliquet non. Cras sed nisl id orci imperdiet pellentesque. Duis facilisis porttitor varius. Mauris quis leo facilisis, scelerisque massa non, ornare turpis. Donec ultricies eget massa vitae pellentesque. Nunc rhoncus faucibus hendrerit. Pellentesque et maximus leo, ac posuere nunc. Cras vitae tincidunt nibh. Ut non erat vel felis congue iaculis.',
        'PUBLISHED', true, '2020-01-20 14:42:34.609474', '2020-02-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (5, 1, 'Fermentum iaculis',
        'Suspendisse vel ex venenatis, euismod augue at, auctor velit. Sed fringilla, arcu in pulvinar condimentum, urna nulla iaculis felis, in ornare orci turpis non nibh. Pellentesque turpis ante, hendrerit sed tempor elementum, tristique quis justo. Ut non dignissim orci. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut vel augue nec magna cursus bibendum at eu eros. Phasellus dui ligula, accumsan sit amet tincidunt nec, ornare vel metus. Proin justo mauris, ullamcorper ut euismod malesuada, iaculis a lectus. Aliquam maximus laoreet justo vel viverra. Curabitur at lacus nisl. Nunc tincidunt faucibus dolor eu mollis. Mauris id sem id lorem luctus pretium nec sit amet turpis. Maecenas eu leo sit amet metus commodo aliquet sit amet vitae lectus. Cras finibus luctus urna a ullamcorper.',
        'PUBLISHED', true, '2020-02-20 14:42:34.609474', '2020-03-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Ninth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-14 11:59:53.000000', '2020-12-14 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (6, 1, 'Fermentum iaculis',
        'Quisque sollicitudin orci sollicitudin sapien finibus tempus. Nulla sapien urna, viverra quis enim posuere, consectetur laoreet elit. Nulla a pretium diam. Nulla facilisi. Sed ipsum ante, dictum non justo sit amet, commodo ultricies dolor. Etiam sed nunc vel justo tincidunt varius. Etiam in pretium lectus. Donec porttitor lobortis sem, eget consequat metus accumsan ac. Ut ex dolor, efficitur sit amet sodales in, vulputate quis tortor. Nunc sit amet nisl tortor. Sed finibus feugiat quam, eget tincidunt turpis vestibulum id. Mauris maximus ex leo, a egestas mauris sollicitudin in. Aenean ut arcu pellentesque eros efficitur posuere. Nam lobortis accumsan suscipit. Sed velit est, aliquam nec odio in, sodales facilisis ex.',
        'PUBLISHED', true, '2020-03-20 14:42:34.609474', '2020-04-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 1, 'Fermentum iaculis',
        'Cras at est ex. Aliquam erat volutpat. Donec ultricies dolor nec justo euismod pretium. Donec elementum vestibulum feugiat. Fusce ullamcorper sapien sit amet venenatis convallis. Sed sagittis interdum sapien ac interdum. Praesent porttitor condimentum lacus, ac aliquam dui pulvinar accumsan.',
        'PUBLISHED', true, '2020-04-20 14:42:34.609474', '2020-05-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (8, 1, 'Fermentum iaculis',
        'Praesent nec maximus sem. Ut imperdiet, libero sit amet porta vulputate, dolor erat pretium neque, in efficitur eros enim in enim. In non pretium urna. Suspendisse quis ante ut erat pulvinar accumsan. Integer molestie ex eget libero tincidunt, sit amet ullamcorper mauris fermentum. Donec porta posuere ornare. Etiam congue quis lacus id volutpat. Integer scelerisque a dolor non tristique. Sed nisl sapien, tincidunt sit amet posuere ut, bibendum at velit. Proin eget risus in risus accumsan accumsan pharetra rutrum leo. Vivamus porta lectus leo, vel tincidunt libero rhoncus vitae. Sed et dolor nec tellus ornare bibendum at vel mi. Donec pellentesque placerat fringilla.',
        'PUBLISHED', true, '2020-05-20 14:42:34.609474', '2020-06-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Fermentum iaculis',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Proin orci lectus, sodales sed varius non, fringilla non metus. Nam tincidunt libero eu tincidunt lacinia. Sed scelerisque metus at purus pellentesque, eu elementum mauris tempor. Mauris metus mauris, convallis in porttitor sit amet, fermentum vel ipsum. Sed hendrerit volutpat eros, quis commodo ligula aliquet vitae. Nullam gravida quam et ligula facilisis, a volutpat sapien viverra. Integer non porta enim, in blandit leo. Etiam tempor elit lacus, ut venenatis massa rhoncus at. Etiam eu massa rutrum, aliquet arcu at, lobortis ligula. In sit amet nisi nec ex congue lobortis vitae at risus. Praesent elementum accumsan magna, eget auctor ex scelerisque non. Fusce posuere, metus varius malesuada varius, sem velit varius mauris, eget scelerisque mi turpis sit amet dui. Cras pellentesque pulvinar tincidunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
        'PUBLISHED', true, '2020-07-20 14:42:34.609474', '2020-08-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'First therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-06 11:59:53.000000', '2020-12-06 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Second therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-07 11:59:53.000000', '2020-12-07 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Third therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-08 11:59:53.000000', '2020-12-08 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Fourth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-09 11:59:53.000000', '2020-12-09 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Fifth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-10 11:59:53.000000', '2020-12-10 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Sixth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-11 11:59:53.000000', '2020-12-11 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Seventh therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-12 11:59:53.000000', '2020-12-12 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Eighth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-13 11:59:53.000000', '2020-12-13 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Tenth therapy post',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'PUBLISHED', true, '2020-12-15 11:59:53.000000', '2020-12-15 11:59:53.000000', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Fermentum iaculis',
        'Duis finibus risus et hendrerit fringilla. Donec varius nisi massa, eu facilisis velit iaculis id. Sed porta lectus eget lectus auctor, at iaculis risus consequat. Morbi lacinia pretium est, pulvinar porttitor lorem facilisis ut. Etiam in nisi justo. Vivamus luctus efficitur mauris, ut placerat lorem imperdiet a. Phasellus condimentum ante quis leo tempor semper. Nam sit amet faucibus orci, et egestas tortor. Maecenas ornare a lorem nec mattis. Etiam ac dapibus quam. Proin consequat laoreet rutrum. Cras et congue ligula.',
        'PUBLISHED', true, '2020-06-20 14:42:34.609474', '2020-07-26 13:45:34.609474', null);
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (2, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (3, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (5, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (6, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (8, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (11, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (12, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (13, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (14, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (15, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (16, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (17, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (18, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (19, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (20, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (21, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (22, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (23, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (24, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (25, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (26, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (27, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (28, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (29, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (30, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (31, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (32, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (33, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (34, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (35, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (36, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (37, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (11, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (10, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (8, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (7, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (6, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (5, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (4, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (3, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (2, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (1, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (21, 1, 'Fermentum iaculis',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed. Et pharetra pharetra massa massa ultricies. Malesuada proin libero nunc consequat interdum varius sit. Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna. Dui id ornare arcu odio ut sem nulla pharetra.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Fermentum iaculis eu non diam phasellus vestibulum lorem sed.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (20, 1, 'Integer eget aliquet',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo. Porttitor eget dolor morbi non. Urna cursus eget nunc scelerisque viverra. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Fermentum et sollicitudin ac orci phasellus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Integer eget aliquet nibh praesent tristique magna sit amet purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (19, 1, 'Ultrices sagittis orci',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (18, 1, 'Massa eget egestas',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar neque laoreet suspendisse interdum consectetur. Vitae suscipit tellus mauris a diam. Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Massa eget egestas purus viverra accumsan. Porta non pulvinar');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (17, 1, 'Est ullamcorper eget',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam. Risus ultricies tristique nulla aliquet enim tortor at. Diam quis enim lobortis scelerisque fermentum. Tempor commodo ullamcorper a lacus vestibulum. Erat nam at lectus urna.',
        'PUBLISHED', true, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Mauris cursus mattis molestie a. Est ullamcorper eget nulla facilisi etiam dignissim diam');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (16, 1, 'Sit amet consectetur',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis. Integer eget aliquet nibh praesent tristique magna sit amet purus. Dolor purus non enim praesent elementum facilisis leo.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Varius vel pharetra vel turpis nunc eget. Nisl tincidunt eget nullam non nisi est sit amet facilisis.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (15, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (14, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (13, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (12, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Dolor sit amet consectetur adipiscing elit ut aliquam purus.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (15, 1, 'Proin nibh nisl condimentum',
        'Ullamcorper malesuada proin libero nunc consequat. Sit amet consectetur adipiscing elit pellentesque habitant morbi tristique. Leo in vitae turpis massa sed. Ultrices eros in cursus turpis.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ullamcorper malesuada proin libero nunc consequat.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (14, 1, 'Tempus quam pellentesque',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at. Arcu bibendum at varius vel pharetra vel turpis nunc eget. Tempus quam pellentesque nec nam aliquam sem. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Nullam vehicula ipsum a arcu cursus vitae congue mauris rhoncus.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Ultrices sagittis orci a scelerisque purus semper eget. Arcu non odio euismod lacinia at');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (13, 1, 'Tempus quam pellentesque', 'Proin nibh nisl condimentum id venenatis a condimentum vitae.',
        'PUBLISHED', false, '2021-02-16 03:56:37.700332', '2021-02-16 03:56:37.700332',
        'Proin nibh nisl condimentum id venenatis a condimentum vitae.');
INSERT INTO public.posts (author_id, type_id, title, content, status, important, created_at, modified_at,
                          preview)
VALUES (12, 1, 'Ultrices eros in cursus', 'Dolor sit amet consectetur adipiscing elit ut aliquam purus.',
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

INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'ivan@mail.com', '1', 1);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'andrii@mail.com', '2', 2);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'olena@mail.com', '3', 3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'dmytro@mail.com', '4', 4);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'kateryna@mail.com', '5', 5);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'oleh@mail.com', '6', 6);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'tetyana@mail.com', '7', 7);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'maryna@mail.com', '8', 8);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'admin@mail.com', '9', 9);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'stepan@mail.com', '10', 10);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'danylo@mail.com', '11', 11);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'petro@mail.com', '12', 12);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'masha@mail.com', '13', 13);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'mykola@mail.com', '14', 14);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'taras@mail.com', '15', 15);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'lesia@mail.com', '16', 16);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'bohdan@mail.com', '17', 17);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'eva@mail.com', '18', 18);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'zahar@mail.com', '19', 19);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'nazar@mail.com', '20', 20);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'zhan@mail.com', '21', 21);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'nikon@mail.com', '22', 22);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'matviy@mail.com', '23', 23);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'regina@mail.com', '24', 24);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'fedot@mail.com', '25', 25);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'terentiy@mail.com', '26', 26);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'platon@mail.com', '27', 27);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'ShtukaNovitskaya261@mail.com', '28', 28);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'FadiStalin333@mail.com', '29', 29);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'PamfiyTihomirov752@mail.com', '30', 30);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'FekletinyaShashkova782@mail.com', '31', 31);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'PalanaLitvinova514@mail.com', '32', 32);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'GigranAlekseev32@mail.com', '33', 33);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'KukuriChapko624@mail.com', '34', 34);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'NezdaUshakov899@mail.com', '35', 35);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'MiletiyChernyshov229@mail.com', '36', 36);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'TarzhemanSokolov585@mail.com', '37', 37);

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
