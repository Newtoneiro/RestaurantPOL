CREATE TABLE users
(
    user_id     varchar2(100) NOT NULL,
    username    varchar2(100) NOT NULL,
    password    varchar2(100) NOT NULL,
    first_name  varchar2(100) NOT NULL,
    last_name   varchar2(100) NOT NULL,
    email       varchar2(100) NOT NULL,
    admin       char(1) DEFAULT 'N' NOT NULL,
    favourite_restaurant varchar2(100),
    CONSTRAINT users_primary_key PRIMARY KEY (user_id),
    CONSTRAINT users_username_unique UNIQUE (username),
    CONSTRAINT users_email_unique UNIQUE (email),
    CONSTRAINT check_admin CHECK (admin IN ('N', 'Y'))
);

alter table users add constraint users_favourite_restaurant_foreign_key FOREIGN KEY(favourite_restaurant) references restaurant_instance(restaurant_id) on delete cascade;