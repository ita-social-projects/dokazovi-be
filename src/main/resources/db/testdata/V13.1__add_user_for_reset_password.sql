INSERT INTO public.users (email, password, status, first_name, last_name, phone, created_at, avatar, enabled,
                          role_id)
VALUES ('igor.zaharko@gmail.com', '$2y$10$CXUu2az9AJHT23fUcXkGd.x4m.ByDOCb6e0uU8teF/Dgq2lqeHfv2', 'ACTIVE',
        'Test', 'Testoff', '+380631434156', '2019-02-16 03:56:37.444571', 'https://i.pravatar.cc/300?img=70', true,
        3);
INSERT INTO public.providers (provider_name, email, user_id_by_provider, user_id)
VALUES ('LOCAL', 'igor.zaharko@gmail.com', '38', 38);
