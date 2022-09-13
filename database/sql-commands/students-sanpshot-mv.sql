CREATE MATERIALIZED VIEW student_snapshot AS
select s.name, s.surname, sb.subject_name, r.mark
from students s
         JOIN exam_results r on s.id = r.student_id
         join subjects sb on sb.id = r.subject_id;

select * from student_snapshot;