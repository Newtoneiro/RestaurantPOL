CREATE TABLE addresses
(
    address_id  varchar2(100) NOT NULL,
    street      varchar2(150) NOT NULL,
    post_code   varchar2(150) NOT NULL,
    city        varchar2(150) NOT NULL,
    country     varchar2(150) NOT NULL,
    latitude    float,
    longitude   float,
    CONSTRAINT addresses_primary_key PRIMARY KEY (addresses_primary_key)
);

alter table addresses add constraint check_lat CHECK (latitude between -90 and 90);
alter table addresses add constraint check_long CHECK (longitude between -180 and 180);

create index addresses_address_id_idx on addresses(address_id);