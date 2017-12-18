-- Table: public."Professor"

-- DROP TABLE public."Professor";

CREATE TABLE public.professor
(
    profid integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    monthlysalary integer,
    CONSTRAINT "Professor_pkey" PRIMARY KEY (profid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.professor
    OWNER to postgres;




-- Table: public."Student"

-- DROP TABLE public."Student";

CREATE TABLE public.student
(
    studentid integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    yearlytuitionfee integer,
    guardian character varying(50) COLLATE pg_catalog."default"
    CONSTRAINT "Student_pkey" PRIMARY KEY (studentid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;




-- Table: public."Course"

-- DROP TABLE public."Course";

CREATE TABLE public.course
(
    courseid integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    profid integer,
    CONSTRAINT "Course_pkey" PRIMARY KEY (courseid),
    CONSTRAINT "Professor" FOREIGN KEY (profid)
        REFERENCES public.professor (profid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.course
    OWNER to postgres;



-- Table: public."CourseTaken"

-- DROP TABLE public."CourseTaken";

CREATE TABLE public.coursetaken
(
    studentid integer,
    courseid integer,
    CONSTRAINT course FOREIGN KEY (courseid)
        REFERENCES public.course (courseid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT student FOREIGN KEY (studentid)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.coursetaken
    OWNER to postgres;