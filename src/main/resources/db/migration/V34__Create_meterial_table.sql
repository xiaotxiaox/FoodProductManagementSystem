create table material
(
    name varchar(256),
    id serial
        constraint material_pk
            primary key,
    num int,
    price int,
    time_apply varchar(256),
    time_handle varchar(256),
    state int,
    person int
);

