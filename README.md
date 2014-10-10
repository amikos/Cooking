Cooking
=======

Cooking project
-- Table: user_reciepts

-- DROP TABLE user_reciepts;

CREATE TABLE user_reciepts
(
  reciept_id integer NOT NULL DEFAULT nextval('user_reciept_seq'::regclass),
  username character varying(45) NOT NULL,
  reciept_name text,
  reciept_description text,
  CONSTRAINT user_reciepts_pkey PRIMARY KEY (reciept_id),
  CONSTRAINT fk_username FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_reciepts OWNER TO postgres;

-- Sequence: user_reciept_seq

-- DROP SEQUENCE user_reciept_seq;

CREATE SEQUENCE user_reciept_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE user_reciept_seq OWNER TO postgres;
