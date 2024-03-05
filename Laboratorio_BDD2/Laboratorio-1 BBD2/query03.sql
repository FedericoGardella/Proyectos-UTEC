SELECT entradas, ventas, (entradas + ventas) AS total
FROM (
    SELECT
        -- Recaudación en entradas
        (SELECT SUM(monto) FROM visitantes v
         JOIN precios p ON p.id_precio = v.id_precio
         WHERE pago = TRUE AND v.fecha > '2020-06-02' AND v.fecha < '2022-03-14') AS entradas,
        -- Recaudación en ventas
        (SELECT SUM(monto) FROM mov_art_sti m 
         JOIN visitantes v ON v.id_visit = m.id_visit
         JOIN precios p ON p.id_precio = m.id_precio
         WHERE monto > 0 AND v.fecha > '2020-06-02' AND v.fecha < '2022-03-14') AS ventas
) AS subconsulta;