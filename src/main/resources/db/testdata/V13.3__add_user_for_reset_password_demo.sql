INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('dokazovo.team.ss@gmail.com', '$2y$10$CXUu2az9AJHT23fUcXkGd.x4m.ByDOCb6e0uU8teF/Dgq2lqeHfv2', 'ACTIVE',
        'Dokazovi', 'Project', '+380639438156', '2020-05-18 03:56:37.444571', 'https://i.pravatar.cc/300?img=7', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'dokazovo.team.ss@gmail.com', '58', 58);