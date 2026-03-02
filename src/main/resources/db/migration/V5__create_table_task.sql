-- V5__create_table_task.sql

CREATE TABLE IF NOT EXISTS task
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    item_name          VARCHAR(255)          NOT NULL UNIQUE,
    quantity           INT                   NOT NULL,
    is_active          BOOLEAN               NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL
);