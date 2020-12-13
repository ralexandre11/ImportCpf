-- SEQUENCE: public.person_seq

-- DROP SEQUENCE public.person_seq;

CREATE SEQUENCE public.person_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.person_seq
    OWNER TO postgres;
    
-- Table: public.person

-- DROP TABLE public.person;

CREATE TABLE public.person
(
    id_person bigint NOT NULL DEFAULT nextval('person_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Person_pkey" PRIMARY KEY (id_person)
)

TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;
    