create table outbound
(
    id serial
        constraint outbound_pk
            primary key,
    materialname varchar(256),
    num int,
    timeout varchar(256)
);

