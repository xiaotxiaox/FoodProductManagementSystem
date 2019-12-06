create table producing
(
    id serial
        constraint producing_pk
            primary key,
    state int default 0,
    round int,
    good int,
    good_count int,
    produce_date VARCHAR(48),
    handler int
);

