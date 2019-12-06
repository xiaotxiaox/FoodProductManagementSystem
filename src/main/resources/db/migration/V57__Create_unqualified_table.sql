create table unqualified
(
    id serial
        constraint unqualified_pk
            primary key,
    producing int,
    way VARCHAR(128),
    handler int,
    is_handle int,
    note VARCHAR(128)
);

