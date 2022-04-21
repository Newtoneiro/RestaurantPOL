CREATE TABLE reviews(
    review_id VARCHAR2(100),
    username VARCHAR2(100) NOT NULL,
    rating FLOAT NOT NULL,
    descript VARCHAR2(20) NOT NULL,
    CONSTRAINT reviews_primary_key PRIMARY KEY(review_id)
);

alter table reviews add constraint check_rating check (rating in (1, 2, 3, 4, 5));
