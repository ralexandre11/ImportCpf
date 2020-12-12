DROP TABLE main.cpf IF EXISTS;

CREATE TABLE main.cpf
(
    id bigint NOT NULL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(15) COLLATE pg_catalog."default" NOT NULL
);

