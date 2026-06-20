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

CREATE TYPE reliance.query_status AS ENUM(
    'PENDING',
    'RESOLVED',
    'ESCALATED_M1',
    'ESCALATED_M2'
);

CREATE TABLE reliance.customer_queries(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    query VARCHAR(255) NOT NULL,
    customer_id BIGINT NOT NULL,
    statue query_status,
    query_date TIMESTAMPTZ DEFAULT NOW(),
    resolved_date TIMESTAMPTZ,
    first_escalated_on TIMESTAMPTZ,
    second_escalated_on TIMESTAMPTZ,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.customers(id)
);

CREATE TABLE reliance.meter_service_providers(
    meter_type public.meter_type PRIMARY KEY,
    rate_per_unit DECIMAL(2, 2) NOT NULL
);