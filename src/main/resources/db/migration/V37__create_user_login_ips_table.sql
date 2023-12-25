CREATE TABLE user_login_ips (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    ip_address VARCHAR NOT NULL,
    UNIQUE (user_id, ip_address)
);