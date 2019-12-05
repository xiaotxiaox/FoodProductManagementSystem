create table team
(
    id serial
        constraint team_pk
            primary key,
    department int,
    factory int,
    name VARCHAR(24),
    leader VARCHAR(24),
    leader_phone VARCHAR(11)
);

