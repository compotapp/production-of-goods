-- V1__create_table_goods.sql

CREATE TABLE IF NOT EXISTS goods
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    name               VARCHAR(255)          NOT NULL UNIQUE,
    quantity           INT                   NOT NULL,
    category           VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL,

    CONSTRAINT chk_quantity CHECK (quantity >= 0)
);