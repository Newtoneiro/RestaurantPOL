CREATE TABLE restaurant_instance(
    restaurant_id   VARCHAR2(100),
    address_id      VARCHAR2(100) NOT NULL,
    slug            VARCHAR2(100) UNIQUE NOT NULL,
    restaurant_template_id VARCHAR2(100) NOT NULL,
    CONSTRAINT restaurant_instance_primary_key PRIMARY KEY (restaurant_id)
);

alter table restaurant_instance add constraint RESTAURANT_INSTANCE_ADDRESSES_FOREIGN_KEY FOREIGN KEY (address_id) references addresses(address_id) on delete cascade;
alter table restaurant_instance add constraint RESTAURANT_INSTANCE_RESTAURANT_TEMPLATE_FOREIGN_KEY FOREIGN KEY (restaurant_template_id) references restaurant_template(restaurant_template_id) on delete cascade;

create or replace function get_average_rating(res_id VARCHAR2)
RETURN FLOAT
AS
    v_avg_rating FLOAT;
BEGIN
    select avg(r.rating)
    into v_avg_rating
    from ratings r
    where restaurant_id = res_id;
    return NVL(round(v_avg_rating, 2), 0);
END;
/

create or replace view restaurant_avg_rating as select get_average_rating(re.restaurant_id) as rating, re.restaurant_id as restaurant_id
                                                from restaurant_instance re;
                                        select * from restaurant_avg_rating;