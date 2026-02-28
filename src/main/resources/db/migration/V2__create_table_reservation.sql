-- V2__create_table_reservation.sql

CREATE TABLE IF NOT EXISTS reservation (
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    reservation_number varchar(255)          NOT NULL,
    reservation_owner  varchar(255)          NOT NULL,
    quantity           INT                   NOT NULL,
    status             varchar(255)          NOT NULL,
    goods_id           BIGINT                NOT NULL,
    created_date       timestamp             NOT NULL,
    last_modified_date timestamp             NOT NULL,

    CONSTRAINT chk_quantity CHECK (quantity > 0),
    FOREIGN KEY (goods_id) REFERENCES goods(id)
)