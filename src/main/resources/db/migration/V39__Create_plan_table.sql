create table ProductionPlan
(
    id serial
        constraint ProductionPlan_pk
            primary key,
    timecreate varchar(256),
    neednum int,
    timelastest varchar(256),
    state int
);

