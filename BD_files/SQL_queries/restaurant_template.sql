CREATE TABLE restaurant_template(
    restaurant_template_id   VARCHAR2(100),
    restaurant_name          VARCHAR2(100) UNIQUE NOT NULL,
    restaurant_description   VARCHAR2(100) NOT NULL,
    img_url                  VARCHAR2(200) NOT NULL,
    cuisine_id               VARCHAR2(100) NOT NULL,
    CONSTRAINT restaurant_template_primary_key PRIMARY KEY (restaurant_template_primary_key)
);

alter table restaurant_template add constraint RESTAURANT_TEMPLATE_CUISINES_FOREIGN_KEY FOREIGN KEY (cuisine_id) references cuisines(cuisine_id);
