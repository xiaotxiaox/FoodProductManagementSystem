alter table comment add comment_count int default 0;
alter table question alter column creator type bigint using creator::bigint;
