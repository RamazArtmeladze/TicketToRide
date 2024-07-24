
create table users (
      user_id SERIAL PRIMARY KEY,
      email VARCHAR(255) NOT NULL,
      name VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL
);

create table segments (
      id SERIAL PRIMARY KEY,
      from_City VARCHAR(255) NOT NULL,
      to_City VARCHAR(255) NOT NULL,
      segment_Count int NOT NULL
);
