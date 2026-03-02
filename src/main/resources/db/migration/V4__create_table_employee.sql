-- V4__create_table_employee.sql

CREATE TABLE IF NOT EXISTS employee
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    number             VARCHAR(255)          NOT NULL UNIQUE,
    exp                INT                   NOT NULL,
    title              VARCHAR(255)          NOT NULL,
    status             VARCHAR(255)          NOT NULL,
    version            INT                   NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL
);