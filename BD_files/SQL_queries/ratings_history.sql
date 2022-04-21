create sequence ratings_history_id_seq start with 1 increment by 1 nomaxvalue;

create table RATINGS_HISTORY (
    ratings_history_id INTEGER,
    rating_id varchar2(100) not null,
    restaurant_id varchar2(100) not null,
    user_id varchar2(100) not null,
    date_modified DATE not null,
    action varchar2(100) not null,
    CONSTRAINT ratings_history_primary_key PRIMARY KEY (ratings_history_id)
);

create or replace trigger ratings_history_set_key
before insert on ratings_history
for each row
begin
      SELECT ratings_history_id_seq.nextval
      INTO :new.ratings_history_id
      FROM dual;
end;
/

CREATE OR REPLACE PACKAGE ratings_history_functions AS
    PROCEDURE insert_data(rat_id VARCHAR2, res_id VARCHAR2, us_id VARCHAR2, action VARCHAR2);
end ratings_history_functions;
/

CREATE OR REPLACE PACKAGE BODY ratings_history_functions AS
    PROCEDURE insert_data(rat_id VARCHAR2, res_id VARCHAR2, us_id VARCHAR2, action VARCHAR2) IS
    BEGIN
        insert into ratings_history (rating_id, restaurant_id, user_id, date_modified, action) values (rat_id, res_id, us_id, SYSDATE, action);
    END;
END ratings_history_functions;
/

create or replace trigger ratings_modify_add
before insert on ratings
for each row
begin
    ratings_history_functions.insert_data(:new.rating_id, :new.restaurant_id, :new.user_id, 'added');
end;
/

create or replace trigger ratings_modify_delete
before delete on ratings
for each row
begin
    ratings_history_functions.insert_data(:old.rating_id, :old.restaurant_id, :old.user_id, 'deleted');
end;
/