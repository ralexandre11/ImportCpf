DROP TABLE main.person;

CREATE TABLE main.person
(
    id_person bigint NOT NULL DEFAULT nextval('person_seq'::regclass) PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(15) COLLATE pg_catalog."default" NOT NULL
);
