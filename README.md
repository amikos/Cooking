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

-- Table: user_roles

-- DROP TABLE user_roles;

CREATE TABLE user_roles
(
  user_role_id integer NOT NULL,
  username character varying(45) NOT NULL,
  role character varying(45) NOT NULL,
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_role_id),
  CONSTRAINT fk_username FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_roles
  OWNER TO postgres;

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username character varying(45) NOT NULL,
  password character varying(45) NOT NULL,
  enabled integer NOT NULL DEFAULT 1,
  CONSTRAINT users_pkey PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

