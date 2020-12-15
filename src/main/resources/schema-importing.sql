DROP TABLE public.person IF EXISTS;

CREATE TABLE public.person
(
    id_person bigint NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Person_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;