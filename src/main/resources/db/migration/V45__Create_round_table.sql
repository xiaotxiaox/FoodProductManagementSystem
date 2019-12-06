create table round
(
    id serial
        constraint round_pk
            primary key,
    name VARCHAR(48),
    goods int,
    count int,
    production_plan int
);

