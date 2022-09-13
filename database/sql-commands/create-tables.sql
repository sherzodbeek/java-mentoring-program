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

CREATE TABLE student_address
(
    student_id int PRIMARY KEY,
    address    text NOT NULL
);

CREATE TABLE student_address_audit
(
    student_id  int  NOT NULL,
    old_address text NOT NULL,
    new_address text NOT NULL
);