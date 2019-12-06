alter table outbound drop column materialname;

alter table outbound
    add materialid int;

alter table material
    add materialid int;
