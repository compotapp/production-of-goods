-- V1__create_table_goods.sql

CREATE TABLE IF NOT EXISTS goods
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    name               varchar(255)          NOT NULL UNIQUE,
    quantity           INT                   NOT NULL,
    category           varchar(255)          NOT NULL,
    created_date       timestamp             NOT NULL,
    last_modified_date timestamp             NOT NULL,

    CONSTRAINT chk_quantity CHECK (quantity >= 0)
);