alter table order_collection
    add back_date VARCHAR(48);

alter table order_collection
    add back_agree_date VARCHAR(48);

alter table order_collection
    add back_money_symbol int default 0;

alter table order_collection
    add back_money DECIMAL;

