create table factory
(
    id serial
        constraint factory_pk
            primary key,
    name VARCHAR(48) not null,
    address VARCHAR(48),
    leader VARCHAR(24),
    leader_phone VARCHAR(11)
);

