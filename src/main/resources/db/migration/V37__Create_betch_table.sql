create table batch
(
    id serial
        constraint batch_pk
            primary key,
    goods_name varchar(256),
    goods_num int,
    time_finish varchar(256),
    time_protect varchar(256),
    person int
);

