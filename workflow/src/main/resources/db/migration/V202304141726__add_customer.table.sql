CREATE TYPE gender AS ENUM('M', 'F', 'U');

CREATE TABLE IF NOT EXISTS customers (
    id uuid not null default gen_random_uuid(),
    name varchar(100) not null,
    gender gender default 'U',
    card_id char(18) not null ,
    phone bigint,
    create_dt timestamp,
    create_user varchar(50),
    update_dt timestamp,
    update_user varchar(50),
    deleted_at TIMESTAMP,
    PRIMARY KEY (id)
);

COMMENT ON TABLE customers IS 'customers';