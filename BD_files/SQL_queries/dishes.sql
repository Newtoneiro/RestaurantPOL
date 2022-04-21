CREATE TABLE dishes
(
    dish_id         varchar2(100) NOT NULL,
    name            varchar2(100) NOT NULL,
    price           float NOT NULL,
    description     varchar2(200) NOT NULL,
    restaurant_id   varchar2(100) NOT NULL,
    img_url         varchar2(200) NOT NULL,
    CONSTRAINT dishes_primary_key PRIMARY KEY (dish_id)
);

alter table dishes add constraint DISHES_RESTAURANT_INSTANCE_FOREIGN_KEY FOREIGN KEY (restaurant_id) references restaurant_instance (restaurant_id) on delete cascade;
alter table dishes add constraint check_price CHECK (price >= 0);
