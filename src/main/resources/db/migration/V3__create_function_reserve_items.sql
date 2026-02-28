-- V3__create_function_reserve_items.sql

CREATE OR REPLACE FUNCTION reserve_items(
    item_names VARCHAR[],
    reservation_quantities INTEGER[]
)
    RETURNS TABLE
            (
                item_id              BIGINT,
                item_quantity        INTEGER,
                item_name            VARCHAR,
                reservation_quantity INTEGER
            )
AS
$$
BEGIN
    RETURN QUERY
        WITH request AS (SELECT unnest(item_names)             AS item_name,
                                unnest(reservation_quantities) AS reservation_quantity),
             update AS (UPDATE item i
                 SET quantity = i.quantity - r.reservation_quantity
                 FROM request r
                 WHERE r.item_name = i.name AND i.quantity - r.reservation_quantity >= 0
                 RETURNING i.id, i.name, i.quantity),
             response AS (SELECT u.id, u.quantity, r.item_name, r.reservation_quantity
                          FROM update u
                                   RIGHT JOIN request r ON r.item_name = u.name)
        SELECT *
        FROM response;
END;
$$ LANGUAGE plpgsql;