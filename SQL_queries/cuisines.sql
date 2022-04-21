CREATE TABLE cuisines
(
    cuisine_id     varchar2(100) NOT NULL,
    name            varchar2(100) UNIQUE NOT NULL,
    CONSTRAINT cuisines_primary_key PRIMARY KEY (cuisine_id)
);