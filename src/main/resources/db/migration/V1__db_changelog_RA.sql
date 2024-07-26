
create table users (
      user_id SERIAL PRIMARY KEY,
      email VARCHAR(255) NOT NULL,
      name VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL
);

create table destinations (
      id SERIAL PRIMARY KEY,
      departure VARCHAR(255) NOT NULL,
      arrival VARCHAR(255) NOT NULL,
      segment_count int NOT NULL
);
