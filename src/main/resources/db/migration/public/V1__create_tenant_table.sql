CREATE TABLE public.service_providers (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE users(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name        VARCHAR(255) NOT NULL ,
    email       VARCHAR(255) NOT NULL UNIQUE ,
    username    VARCHAR(255) NOT NULL ,
    password    VARCHAR(255) NOT NULL ,
    role        user_role NOT NULL
);