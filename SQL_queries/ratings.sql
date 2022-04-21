CREATE TABLE ratings
(
    rating_id	    VARCHAR2(100) CONSTRAINT ratings_primary_key PRIMARY KEY,
	restaurant_id	VARCHAR2(100) NOT NULL,
    descript        VARCHAR2(200),
    user_id         VARCHAR2(100) NOT NULL,
	rating	        NUMBER(1, 1) CHECK (rating IN (0.5, 1.0, 1.5, 2.0, 2.5, 
                                                  3.0, 3.5, 4.0, 4.5, 5.0))
);

ALTER TABLE ratings
ADD CONSTRAINT RATIGNS_RESTAURANT_INSTANCE_FOREIGN_KEY
FOREIGN KEY (restaurant_id)
REFERENCES restaurant_instance (restaurant_id) on delete cascade;

ALTER TABLE ratings
ADD CONSTRAINT RATINGS_USERS_FOREIGN_KEY
FOREIGN KEY (user_id)
REFERENCES users (user_id) on delete cascade;

