create function get_avarage_mark_by_subject_name(subject_name varchar)
    returns decimal
    language plpgsql
AS
$$
DECLARE
    avarage_mark decimal;
BEGIN
    select avg(r.mark)
    into avarage_mark
    from subjects s
             join exam_results r on s.id = r.subject_id
    where s.subject_name = $1;
    return avarage_mark;
end;
$$;

select get_avarage_mark_by_subject_name('Reading');