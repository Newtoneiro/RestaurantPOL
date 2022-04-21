CREATE OR REPLACE TRIGGER restaurant_slug
BEFORE INSERT ON restaurant_instance FOR EACH ROW
DECLARE
    v_same_slug_count NUMBER;
    v_counter NUMBER;
    v_temporary_slug VARCHAR2(100) := :new.slug;
BEGIN
    SELECT COUNT(restaurant_id)
    INTO v_same_slug_count
    FROM restaurant_instance
    WHERE slug LIKE CONCAT(:new.slug, '%');
    
    if v_same_slug_count > 0 then
        v_temporary_slug := CONCAT(CONCAT(v_temporary_slug, '-'), v_same_slug_count);
        select count(*)
        into v_counter
        from restaurant_instance
        where slug = v_temporary_slug;
        
        while v_counter > 0 
        loop
            v_same_slug_count := v_same_slug_count + 1;
            v_temporary_slug := CONCAT(CONCAT(v_temporary_slug, '-'), v_same_slug_count);
            select count(*)
            into v_counter
            from restaurant_instance
            where slug = v_temporary_slug;
        end loop;
    end if;
    :new.slug := v_temporary_slug;
END;
