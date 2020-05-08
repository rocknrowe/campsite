CREATE TABLE IF NOT EXISTS island (

    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(50) NOT NULL,
    creation_time timestamp NOT NULL DEFAULT NOW(),
    update_time timestamp
);