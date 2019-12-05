create table department
(
    id serial
        constraint department_pk
            primary key,
    name VARCHAR(48) not null,
    factory int,
    leader VARCHAR(48),
    leader_number VARCHAR(11)
);

