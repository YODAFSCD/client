CREATE VIEW detail_view AS

SELECT d.*,p.description product
FROM detail d JOIN product p ON d.product_id = p.id;

































