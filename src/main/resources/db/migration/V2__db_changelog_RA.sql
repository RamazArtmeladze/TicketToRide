  create table tickets (
        ticket_id SERIAL PRIMARY KEY,
        departure VARCHAR(255) NOT NULL,
        arrival VARCHAR(255) NOT NULL,
        segment_count int NOT NULL,
        price int NOT NULL,
        currency VARCHAR(255) NOT NULL,
        traveller_amount int NOT NULL,
        traveller VARCHAR(255) NOT NULL,
        user_id BIGINT NOT NULL
  );