create table inventory
(
    id serial
        constraint inventory_pk
            primary key,
    name varchar(256),
    num int,
    timeprotect varchar(256)
);

