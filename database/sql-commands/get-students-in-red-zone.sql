create function get_students_at_red_zone()
    returns table
            (
                id              int,
                name            varchar,
                surname         varchar,
                number_of_marks bigint
            )
    language plpgsql
AS
$$
BEGIN
    return query
        select s.id, s.name, s.surname, count(r.mark)
        from students s
                 join exam_results r on s.id = r.student_id
        where r.mark <= 3
        group by s.id, s.name, s.surname
        having count(s.id) > 2;
end;
$$;

select get_students_at_red_zone();