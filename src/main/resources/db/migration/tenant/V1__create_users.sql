CREATE TYPE user_role AS ENUM(
    'SERVICE_PROVIDER',
    'BPO',
    'SALES_TEAM_MEMBER',
    'OPERATIONS_HEAD',
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

CREATE TABLE bpo_states(
    bpo_id      BIGINT,
    state_id    BIGINT,
    CONSTRAINT  fk_bpo_id FOREIGN KEY (bpo_id) REFERENCES employees(id),
    CONSTRAINT  fk_state_id FOREIGN KEY (state_id) REFERENCES public.states(id)
);
