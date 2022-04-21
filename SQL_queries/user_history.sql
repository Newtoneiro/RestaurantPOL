create sequence user_history_id_seq start with 1 increment by 1 nomaxvalue;

create table USER_HISTORY (
    user_history_id INTEGER,
    user_id varchar2(100) not null,
    username varchar2(100) not null,
    admin CHAR(1 BYTE) not null,
    date_modified DATE not null,
    action varchar2(100) not null,
    CONSTRAINT user_history_primary_key PRIMARY KEY (user_history_id)
);

create or replace trigger user_history_set_key
before insert on user_history
for each row
begin
      SELECT user_history_id_seq.nextval
      INTO :new.user_history_id
      FROM dual;
end;
/

CREATE OR REPLACE PACKAGE user_history_functions AS
    PROCEDURE insert_data(u_id VARCHAR2, uname VARCHAR2, isAdmin CHAR, action VARCHAR2);
end user_history_functions;
/

CREATE OR REPLACE PACKAGE BODY user_history_functions AS
    PROCEDURE insert_data(u_id VARCHAR2, uname VARCHAR2, isAdmin CHAR, action VARCHAR2) IS
    BEGIN
        insert into user_history (user_id, username, admin, date_modified, action) values (u_id, uname, isAdmin, SYSDATE, action);
    END;
END user_history_functions;
/

create or replace trigger user_modify_add
before insert on users
for each row
begin
    user_history_functions.insert_data(:new.user_id, :new.username, :new.admin, 'added');
end;
/

create or replace trigger user_modify_delete
before delete on users
for each row
begin
    user_history_functions.insert_data(:old.user_id, :old.username, :old.admin, 'deleted');
end;
/