create table "Order"
(
    id bigserial
        constraint Order_pk
            primary key,
    custom int not null,
    count int default 0,
    goods int,
    order_date VARCHAR(48),
    pick_date VARCHAR(48),
    handler bigint
);

