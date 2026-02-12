CREATE TABLE x6_user.x6user
(
    id serial NOT NULL,
    name varchar NOT NULL,
    email varchar NOT NULL,
    birthday date NOT NULL,
    info varchar,
    PRIMARY KEY (id)
);
