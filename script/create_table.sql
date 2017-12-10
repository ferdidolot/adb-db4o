-- Table: public."Professor"

-- DROP TABLE public."Professor";

CREATE TABLE public."Professor"
(
    profid integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "Professor_pkey" PRIMARY KEY (profid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Professor"
    OWNER to postgres;




-- Table: public."Student"

-- DROP TABLE public."Student";

CREATE TABLE public."Student"
(
    id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "Student_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Student"
    OWNER to postgres;




-- Table: public."Course"

-- DROP TABLE public."Course";

CREATE TABLE public."Course"
(
    courseid integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    profid integer,
    CONSTRAINT "Course_pkey" PRIMARY KEY (courseid),
    CONSTRAINT "Professor" FOREIGN KEY (profid)
        REFERENCES public."Professor" (profid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Course"
    OWNER to postgres;



-- Table: public."CourseTaken"

-- DROP TABLE public."CourseTaken";

CREATE TABLE public."CourseTaken"
(
    studentid integer,
    courseid integer,
    CONSTRAINT course FOREIGN KEY (courseid)
        REFERENCES public."Course" (courseid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT student FOREIGN KEY (studentid)
        REFERENCES public."Student" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."CourseTaken"
    OWNER to postgres;