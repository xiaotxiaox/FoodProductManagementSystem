create table permissions
(
    id serial
        constraint permissions_pk
            primary key,
    name VARCHAR(48),
    description VARCHAR(48)
);

