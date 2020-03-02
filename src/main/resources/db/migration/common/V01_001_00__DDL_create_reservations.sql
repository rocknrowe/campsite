CREATE TABLE IF NOT EXISTS reservation (

    id SERIAL PRIMARY KEY NOT NULL,
    user_name varchar(50) NOT NULL,
    email varchar(30)  NOT NULL,
    start_date date  NOT NULL,
    number_of_days int  NOT NULL,
    creation_time timestamp NOT NULL DEFAULT NOW(),
    update_time timestamp
);