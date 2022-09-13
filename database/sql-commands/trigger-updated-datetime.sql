create function write_update_datetime()
    returns trigger
    language plpgsql
as
$$
begin
    NEW.updated_datetime := CURRENT_TIMESTAMP;
    return NEW;
END;
$$;

create TRIGGER write_update_datetime
    before UPDATE
    on students
    for each row
execute procedure write_update_datetime();