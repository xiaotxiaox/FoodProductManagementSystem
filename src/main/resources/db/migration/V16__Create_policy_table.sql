create table POLICY
(
	id int auto_increment,
	type VARCHAR(48) not null,
	rate int,
	handler int,
	constraint POLICY_pk
		primary key (id)
);

