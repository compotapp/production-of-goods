-- V6__create_function_update_stock_items.sql

CREATE OR REPLACE FUNCTION update_stock_items(
    product_numbers VARCHAR[],
    quantities INTEGER[]
)
    RETURNS TABLE
            (
                product_number VARCHAR
            )
AS
$$
BEGIN
    RETURN QUERY
        WITH request AS (SELECT unnest(product_numbers) AS product_number,
                                unnest(quantities)      AS quantity),
             update AS (UPDATE stock_items si
                 SET quantity = si.quantity + r.quantity, last_modified_date = now()
                 FROM request r
                 WHERE r.product_number = si.product_number AND si.quantity + r.quantity >= 0
                 RETURNING r.product_number)
        SELECT *
        FROM update;
END;
$$ LANGUAGE plpgsql;