create table good_material
(
    id serial
        constraint good_material_pk
            primary key,
    good int,
    material int,
    count int,
    note VARCHAR(128)
);

