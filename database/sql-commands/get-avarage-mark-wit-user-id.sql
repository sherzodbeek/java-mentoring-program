create function get_avarage_mark(user_id int)
    returns decimal
    language plpgsql
AS
$$
DECLARE
    avarage_mark decimal;
BEGIN
    select avg(r.mark)
    into avarage_mark
    from students s
             join exam_results r on s.id = r.student_id
             join subjects sb on sb.id = r.subject_id
    where s.id = user_id;
    return avarage_mark;
end;
$$;

select get_avarage_mark(4);