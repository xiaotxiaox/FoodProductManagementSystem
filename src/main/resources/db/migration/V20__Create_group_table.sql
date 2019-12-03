create table role
(
    id serial
        constraint role_pk
            primary key,
    name VARCHAR(48) not null,
    description VARCHAR(48)
);

