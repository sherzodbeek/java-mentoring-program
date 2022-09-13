CREATE TABLE students
(
    id               int PRIMARY KEY,
    name             VARCHAR(50) NOT NULL,
    surname          VARCHAR(50) NOT NULL,
    phone_number     VARCHAR(25),
    primary_skill    TEXT,
    created_datetime TIMESTAMP   NOT NULL,
    updated_datetime TIMESTAMP
);

CREATE TABLE subjects
(
    id           int PRIMARY KEY,
    subject_name VARCHAR(50) NOT NULL,
    tutor        VARCHAR(50) NOT NULL
);

CREATE TABLE exam_results
(
    student_id int           NOT NULL,
    subject_id int           NOT NULL,
    mark       numeric(2, 1) NOT NULL,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id)
        REFERENCES students (id),
    FOREIGN KEY (subject_id)
        REFERENCES subjects (id)
);

CREATE INDEX idx_student_id
    ON students USING btree(id);
-- Before adding data - 37 msec.
-- After adding data - 180 msec.

CREATE INDEX idx_student_name
    ON students USING hash(name);
-- Before adding data - 68 msec.
-- After adding data - 239 msec.

CREATE INDEX idx_student_surname
ON students USING gin(surname gin_trgm_ops);
-- Before adding data - 33 msec.
-- After adding data -  268 msec.

SELECT
    pg_size_pretty (pg_indexes_size('students'));
-- check index of tables - 36 MB