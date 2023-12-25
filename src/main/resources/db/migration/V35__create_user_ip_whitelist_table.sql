CREATE TABLE user_ip_whitelist (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    whitelist_ip VARCHAR NOT NULL,
    UNIQUE (user_id, whitelist_ip)
);