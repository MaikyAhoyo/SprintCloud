INSERT INTO ventas (total, cliente_id, create_at, port) VALUES (2600.00, 1, Now(), 8083);
INSERT INTO ventas (total, cliente_id, create_at, port) VALUES (500.00, 2, Now(), 8083);
INSERT INTO ventas_items (producto_id, cantidad, precio_unitario, subtotal, venta_id) VALUES (1, 2, 800.00, 1600.00, 1);
INSERT INTO ventas_items (producto_id, cantidad, precio_unitario, subtotal, venta_id) VALUES (3, 1, 1000.00, 1000.00, 1);
INSERT INTO ventas_items (producto_id, cantidad, precio_unitario, subtotal, venta_id) VALUES (6, 5, 100.00, 500.00, 2);