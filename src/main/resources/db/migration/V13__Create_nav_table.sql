create table nav
(
    id serial primary key not null,
    title varchar(100),
    url varchar(256),
    priority int default 0,
    gmt_create bigint,
    gmt_modified bigint,
    status int
);