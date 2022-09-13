CREATE OR REPLACE FUNCTION not_update_student_address()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS
$$
BEGIN
    IF NEW.student_id <> OLD.student_id OR NEW.address <> OLD.address THEN
        INSERT INTO student_address_audit(student_id, old_address, new_address)
        VALUES (OLD.student_id, OLD.address, NEW.address);
        RAISE INFO 'You can not update student_address table';
    END IF;
    RETURN NULL;
END;
$$;

create TRIGGER not_update_student_address
    before UPDATE
    on student_address
    for each row
execute procedure not_update_student_address();