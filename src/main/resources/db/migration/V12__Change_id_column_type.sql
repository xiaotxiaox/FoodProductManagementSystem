alter table comment alter column id type BIGINT using id::BIGINT;
alter table comment alter column commentator type bigint using commentator::bigint;

