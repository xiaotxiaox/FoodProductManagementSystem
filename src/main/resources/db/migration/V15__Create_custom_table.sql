create table CUSTOM
(
	id serial,
	name VARCHAR(20) not null,
	telephone VARCHAR(32),
	type VARCHAR(16),
	count int,
	handler VARCHAR(32),
	constraint CUSTOM_pk
		primary key (id)
);

