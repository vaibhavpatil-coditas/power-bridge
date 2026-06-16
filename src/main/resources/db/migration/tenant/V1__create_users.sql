CREATE TYPE user_role AS ENUM(
    'BPO',
    'SALES_TEAM',
    'OPERATIONAL_HEAD',
    'MANAGER_1',
    'MANAGER_2'
);

CREATE TABLE employees(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name        VARCHAR(255) NOT NULL ,
    email       VARCHAR(255) NOT NULL UNIQUE ,
    password    VARCHAR(255) NOT NULL ,
    role        user_role NOT NULL,
    created_at	TIMESTAMPTZ NOT NULL DEFAULT NOW()
);