  create table tickets (
        ticket_id SERIAL PRIMARY KEY,
        from_city VARCHAR(255) NOT NULL,
        to_city VARCHAR(255) NOT NULL,
        segments int NOT NULL,
        price int NOT NULL,
        currency VARCHAR(255) NOT NULL,
        traveller_amount int NOT NULL,
        traveller VARCHAR(255) NOT NULL,
        user_id BIGINT NOT NULL
  );