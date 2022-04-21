create or replace procedure get_restaurants_below_standard
AS
    v_restaurant_id VARCHAR2(100);
    v_rating FLOAT;
    v_avg_rating FLOAT;
    CURSOR cr IS
        select ri.restaurant_id
        from restaurant_instance ri join restaurant_avg_rating rar on (ri.restaurant_id = rar.restaurant_id)
        where rar.rating != 0 and rar.rating < (select avg(rating) from restaurant_avg_rating where rating != 0);   
BEGIN
    select avg(rating) into v_avg_rating from restaurant_avg_rating where rating != 0;
    dbms_output.put_line('AVG RATING: ' || v_avg_rating);
    open cr;
    loop
        exit when cr%NOTFOUND;
        fetch cr into v_restaurant_id;
        for res in (select * from restaurant_instance where restaurant_id = v_restaurant_id)
            loop
                select rar.rating
                into v_rating
                from restaurant_avg_rating rar
                where restaurant_id = res.restaurant_id;
                dbms_output.put_line('Name: ' || res.slug || ' ID: ' || res.restaurant_id || ' Rating: ' || v_rating);
            end loop;
        end loop;
EXCEPTION 
    WHEN NO_DATA_FOUND then
        dbms_output.put_line('Insufficient Data');
END;
/

create or replace procedure get_best_restaurants
as
    v_best_rating FLOAT;
    v_cur_rating FLOAT;
BEGIN
    select max(rating)
    into v_best_rating
    from restaurant_avg_rating;
    dbms_output.put_line('Best Restaurants:');
    for res in (select * from restaurant_instance)
        loop
            select rating
            into v_cur_rating
            from restaurant_avg_rating
            where restaurant_id = res.restaurant_id;
            
            if v_cur_rating = v_best_rating then
                dbms_output.put_line('Name: ' || res.slug || ' ID: ' || res.restaurant_id || ' Rating: ' || v_cur_rating);
            end if;
        end loop;
EXCEPTION 
    WHEN NO_DATA_FOUND then
        dbms_output.put_line('Insufficient Data');
END;
/
exec get_best_restaurants;


