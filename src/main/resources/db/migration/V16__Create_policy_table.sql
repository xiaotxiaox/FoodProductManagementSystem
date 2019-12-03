create table POLICY
(
	id serial,
	type VARCHAR(48) not null,
	rate int,
	handler int,
	constraint POLICY_pk
		primary key (id)
);

