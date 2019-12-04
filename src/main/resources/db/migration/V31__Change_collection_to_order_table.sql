alter table order_collection
    add total_cost decimal;

alter table order_collection
    add discount_cost decimal;

alter table order_collection
    add paid_money decimal;

alter table order_collection
    add will_date VARCHAR(48);

alter table order_collection
    add final_date VARCHAR(48);

alter table order_collection
    add state int;

