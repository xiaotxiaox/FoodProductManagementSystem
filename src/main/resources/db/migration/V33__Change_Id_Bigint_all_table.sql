alter table user_user alter column id type int using id::int;

alter table question alter column id type int using id::int;

alter table question alter column creator type int using creator::int;

alter table policy alter column handler type int using handler::int;

alter table order_collection alter column id type int using id::int;

alter table order_collection alter column handler type int using handler::int;

alter table notification alter column id type int using id::int;

alter table notification alter column notifier type int using notifier::int;

alter table notification alter column receiver type int using receiver::int;

alter table notification alter column outerid type int using outerid::int;

alter table manager alter column person type int using person::int;

alter table manager alter column id type int using id::int;

alter table employee alter column person type int using person::int;

alter table custom alter column handler type int using handler::int;

alter table comment alter column id type int using id::int;

alter table comment alter column parent_id type int using parent_id::int;

alter table comment alter column commentator type int using commentator::int;

alter table comment alter column like_count type int using like_count::int;
alter table order_collection alter column state set default 1;







