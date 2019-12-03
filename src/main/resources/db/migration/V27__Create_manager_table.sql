create table manager
(
    gender int,
    department int,
    TimeIn varchar(256),
    pay int,
    phone varchar(16),
    person bigint,
    id bigserial
        constraint manager_pk
            primary key,
    name varchar(128)
);

