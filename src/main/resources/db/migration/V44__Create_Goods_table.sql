create table goods
(
    id serial
        constraint goods_pk
            primary key,
    name varchar(256),
    price int
);

