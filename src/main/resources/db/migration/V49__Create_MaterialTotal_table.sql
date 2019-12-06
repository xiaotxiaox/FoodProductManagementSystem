create table materialtotal
(
    id serial
        constraint materialtotal_pk
            primary key,
    name varchar(256),
    purchaser varchar(256),
    price int,
    unit varchar(256)
);

