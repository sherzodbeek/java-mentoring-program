create function validate_name() returns trigger
    language plpgsql
as
$$
DECLARE
    ch CHARACTER VARYING;
begin
    FOREACH ch IN ARRAY regexp_split_to_array(NEW.name, '')
        LOOP
            if ch = '@' or ch = '#' or ch = '$' then
                raise exception 'Name must not be contains with @, # or $';
            end if;
        end loop;
    return NEW;
END;
$$;

create TRIGGER validate_name
    before UPDATE or INSERT
    on students
    for each row
execute procedure validate_name();