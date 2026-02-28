-- V3__create_function_reservation_of_goods.sql

CREATE OR REPLACE FUNCTION reservation_of_goods(
    reservation_names VARCHAR[],
    reservation_quantities INTEGER[]
)
    RETURNS TABLE
            (
                goods_id             BIGINT,
                goods_quantity       INTEGER,
                goods_name           VARCHAR,
                reservation_quantity INTEGER
            )
AS
$$
BEGIN
    RETURN QUERY
        WITH request AS (SELECT unnest(reservation_names)      AS reservation_name,
                                unnest(reservation_quantities) AS reservation_quantity),
             update AS (UPDATE goods g
                 SET quantity = g.quantity - r.reservation_quantity
                 FROM request r
                 WHERE r.reservation_name = g.name AND g.quantity - r.reservation_quantity >= 0
                 RETURNING g.id, g.name, g.quantity),
             response AS (SELECT u.id, u.quantity, r.reservation_name, r.reservation_quantity
                          FROM update u
                                   RIGHT JOIN request r ON r.reservation_name = u.name)
        SELECT *
        FROM response;
END;
$$ LANGUAGE plpgsql;