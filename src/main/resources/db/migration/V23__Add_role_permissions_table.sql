create table role_permissions
(
    id serial
        constraint role_permissions_pk
            primary key,
    role int not null,
    permissions int not null
);

