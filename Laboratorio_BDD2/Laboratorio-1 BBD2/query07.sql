
SELECT articulo, (costo_total / valor_promedio_existencias) AS indice_rotacion_stock FROM 
 
	(SELECT m.nombre AS articulo, r.costo * v.cantidad AS costo_total, AVG(m.stock) AS valor_promedio_existencias
	FROM mov_art_sti m
	JOIN reposiciones r ON r.id_movas = m.id_movas
	JOIN (
    	SELECT nombre, COUNT(*) AS cantidad
    	FROM mov_art_sti mo
		JOIN visitantes v ON v.id_visit = mo.id_visit
		WHERE v.fecha >= '2020-01-01' AND v.fecha <= '2023-12-31' --El indice se suele calcular en el peridodo de un año
    	GROUP BY nombre												--pero mejor ilustración será en 3 en este ejemplo
	) AS v ON m.nombre = v.nombre	
	WHERE r.fecha >= '2020-01-01' AND r.fecha <= '2023-12-31'
	GROUP BY m.nombre, r.costo * v.cantidad
	)  AS subconsulta
