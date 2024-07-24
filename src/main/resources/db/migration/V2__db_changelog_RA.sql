CREATE TABLE tickets (
    ticket_id SERIAL PRIMARY KEY,
    from_city VARCHAR(255) NOT NULL,
    to_city VARCHAR(255) NOT NULL,
    segment_count INT NOT NULL,
    price INT NOT NULL,
    user_id BIGINT NOT NULL
);