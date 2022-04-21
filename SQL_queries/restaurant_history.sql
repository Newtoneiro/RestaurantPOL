create sequence restaurant_history_id_seq start with 1 increment by 1 nomaxvalue;

create table RESTAURANT_HISTORY (
    restaurant_history_id INTEGER,
    restaurant_id varchar2(100) not null,
    date_modified DATE not null,
    action varchar2(100) not null,
    CONSTRAINT restaurant_history_primary_key PRIMARY KEY (restaurant_history_id)
);

create or replace trigger restaurant_history_set_key
before insert on restaurant_history
for each row
begin
      SELECT restaurant_history_id_seq.nextval
      INTO :new.restaurant_history_id
      FROM dual;
end;
/

CREATE OR REPLACE PACKAGE restaurant_history_functions AS
    PROCEDURE insert_data(res_id VARCHAR2, action VARCHAR2);
end restaurant_history_functions;
/

CREATE OR REPLACE PACKAGE BODY restaurant_history_functions AS
    PROCEDURE insert_data(res_id VARCHAR2, action VARCHAR2) IS
    BEGIN
        insert into restaurant_history (restaurant_id, date_modified, action) values (res_id, SYSDATE, action);
    END;
END restaurant_history_functions;
/

create or replace trigger restaurant_instance_modify_add
before insert on restaurant_instance
for each row
begin
    restaurant_history_functions.insert_data(:new.restaurant_id, 'added');
end;
/

create or replace trigger restaurant_instance_modify_delete
before delete on restaurant_instance
for each row
begin
    restaurant_history_functions.insert_data(:old.restaurant_id, 'deleted');
end;
/