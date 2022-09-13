CREATE INDEX idx_student_name_btree
    ON students USING btree(name);

CREATE INDEX idx_student_name_hash
    ON students USING hash(name);
-- Before adding data - 68 msec.
-- After adding data - 239 msec.

CREATE INDEX idx_student_name_gin
    ON students USING gin(name gin_trgm_ops);

CREATE INDEX idx_student_name_gist
    ON students USING gist(name gist_trgm_ops);

----------------------------------

CREATE INDEX idx_student_surname
    ON students USING gin(surname gin_trgm_ops);
-- Before adding data - 33 msec.
-- After adding data -  268 msec.

CREATE INDEX idx_student_surname_btree
    ON students USING btree(surname);

CREATE INDEX idx_student_surname_hash
    ON students USING hash(surname);

CREATE INDEX idx_student_surname_gist
    ON students USING gist(surname gist_trgm_ops);

------------------------

CREATE INDEX idx_phone_number_btree
    ON students USING btree(phone_number);

CREATE INDEX idx_phone_number_hash
    ON students USING hash(phone_number);

CREATE INDEX idx_phone_number_gin
    ON students USING gin(phone_number gin_trgm_ops);

CREATE INDEX idx_phone_number_gist
    ON students USING gist(phone_number gist_trgm_ops);


---------------------------

CREATE INDEX idx_student_id_btree
    ON students USING btree(id);
-- Before adding data - 37 msec.
-- After adding data - 180 msec.

CREATE INDEX idx_subject_id_btree
    ON subjects USING btree(id);

CREATE INDEX idx_exam_results_btree
    ON exam_results USING btree(student_id, subject_id);

SELECT
    pg_size_pretty (pg_indexes_size('students'));
-- check index of tables - 36 MB