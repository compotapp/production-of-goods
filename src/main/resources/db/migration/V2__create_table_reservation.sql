-- V2__create_table_reservation.sql

CREATE TABLE IF NOT EXISTS reservation (
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    number             VARCHAR(255)          NOT NULL,
    owner              VARCHAR(255)          NOT NULL,
    quantity           INT                   NOT NULL,
    status             VARCHAR(255)          NOT NULL,
    item_id            BIGINT                NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL,
    CONSTRAINT chk_quantity CHECK (quantity > 0),
    FOREIGN KEY (item_id) REFERENCES item(id)
)