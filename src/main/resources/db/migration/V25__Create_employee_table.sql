create table employee
(
    id serial
        constraint employee_pk
            primary key,
    TimeIn varchar(256),
    gender int,
    phone varchar(16),
    pay int,
    department int,
    person bigint
);

