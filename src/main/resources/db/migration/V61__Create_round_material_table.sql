create table round_material
(
    id serial
        constraint round_material_pk
            primary key,
    round int,
    material int,
    count int,
    note VARCHAR(128)
);

