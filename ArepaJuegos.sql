DROP database ArepaJuegos;
CREATE DATABASE ArepaJuegos character set utf8mb4;

USE ArepaJuegos;

CREATE TABLE CONSOLA(
ID_CONSOLA INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE_CONSOLA VARCHAR(70) NOT NULL,
VISIBLE BOOL DEFAULT TRUE
);

CREATE TABLE TIPO_DOC(
ID_TIPO_DOC INT auto_increment PRIMARY KEY,
COD_TIPO_DOC VARCHAR(3) NOT NULL,
VISIBLE BOOL DEFAULT TRUE
);

CREATE TABLE PRODUCTO(
ID_PRODUCTO INT PRIMARY KEY AUTO_INCREMENT,
NOMBRE_PRO VARCHAR(50) NOT NULL,
PRECIO_VENTA DECIMAL(4,2) NOT NULL,
DESCRIPCION VARCHAR(500),
STOCK INT NOT NULL NOT NULL,
ID_CONSOLA INT,
VISIBLE BOOL DEFAULT TRUE,
foreign key(ID_CONSOLA) REFERENCES CONSOLA(ID_CONSOLA)
);




CREATE TABLE CLIENTE(
ID_CLIENTE INT PRIMARY KEY auto_increment,
NOMBRE_O_RZON_SOCIAL VARCHAR(100) NOT NULL,
TIPO_DOC INT NOT NULL,
DOCUMENTO VARCHAR(11) NOT NULL,
TELEFONO VARCHAR(9),
EMAIL VARCHAR(40),
DIRECCION VARCHAR(100),
VISIBLE BOOL default TRUE,
foreign key(TIPO_DOC) REFERENCES TIPO_DOC(ID_TIPO_DOC)
);

SELECT* FROM CLIENTE;

CREATE TABLE COMPRA(
ID_COMPRA INT PRIMARY KEY AUTO_INCREMENT,
ID_CLIENTE INT,
ID_PRODUCTO INT,
CANTIDAD INT,
FECHA_COMPRA DATE,
FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE),
foreign key(ID_PRODUCTO) references producto(ID_PRODUCTO),
VISIBLE BOOL DEFAULT TRUE
);

--
INSERT INTO CONSOLA (NOMBRE_CONSOLA) VALUES
('PlayStation 5'),
('Xbox Series X'),
('Nintendo Switch'),
('PlayStation 4'),
('Xbox One'),
('Nintendo 3DS'),
('PC'),
('PlayStation 3'),
('Xbox 360'),
('Nintendo Wii');

INSERT INTO TIPO_DOC (COD_TIPO_DOC) VALUES
('DNI'),
('CEX'),
('RUC');

INSERT INTO CLIENTE (NOMBRE_O_RZON_SOCIAL, TIPO_DOC, DOCUMENTO, TELEFONO, EMAIL, DIRECCION) VALUES
('Juan Pérez', 1, '12345678', '999888777', 'juan@example.com', 'Calle 123'),
('María Gómez', 2, 'A1234567', '987654321', 'maria@example.com', 'Avenida 456'),
('Pedro Rodríguez', 1, '87654321', '555444333', 'pedro@example.com', 'Plaza 789'),
('Laura López', 3, '12345678901', '111222333', 'laura@example.com', 'Carrera 321'),
('Carlos Martínez', 1, '54321678', '999333666', 'carlos@example.com', 'Callejón 567'),
('Ana Sánchez', 1, '98765432', '777888999', 'ana@example.com', 'Avenida Principal 890'),
('Luis Torres', 2, 'B1234567', '444555666', 'luis@example.com', 'Boulevard 123'),
('Sofía Romero', 1, '43218765', '222333444', 'sofia@example.com', 'Plaza Central 456'),
('Mario Herrera', 1, '98765432', '888999000', 'mario@example.com', 'Calle 789'),
('Carmen Guzmán', 3, '98765432109', '666777888', 'carmen@example.com', 'Avenida 012'),
('Eduardo Silva', 1, '987654321', '555666777', 'eduardo@example.com', 'Calle Principal 123'),
('Alejandra Gutiérrez', 2, 'C1234567', '999000111', 'alejandra@example.com', 'Avenida Secundaria 456'),
('Roberto Morales', 1, '87654321', '222333444', 'roberto@example.com', 'Plaza Central 789'),
('Carolina Ramírez', 3, '12345678901', '777888999', 'carolina@example.com', 'Carrera Principal 012'),
('Fernando González', 1, '54321678', '111222333', 'fernando@example.com', 'Callejón Secundario 345'),
('Gabriela Vargas', 1, '98765432', '888999000', 'gabriela@example.com', 'Avenida Principal 678'),
('Diego Castro', 2, 'D1234567', '444555666', 'diego@example.com', 'Boulevard Principal 901'),
('Patricia Herrera', 1, '43218765', '222333444', 'patricia@example.com', 'Plaza Secundaria 234'),
('Andrés Molina', 1, '98765432', '777888999', 'andres@example.com', 'Calle Principal 567'),
('Vanessa Ríos', 3, '98765432109', '666777888', 'vanessa@example.com', 'Avenida Secundaria 890'),
('Javier Arévalo', 1, '7654321098', '555444333', 'javier@example.com', 'Carrera Principal 123'),
('Mariana Cortés', 2, 'E1234567', '999888777', 'mariana@example.com', 'Avenida Secundaria 456'),
('Rodrigo Varela', 1, '98765432', '222333444', 'rodrigo@example.com', 'Plaza Central 789'),
('Lucía Soto', 3, '10987654321', '777888999', 'lucia@example.com', 'Calle Principal 012'),
('Gustavo Castro', 1, '54321678', '888999000', 'gustavo@example.com', 'Avenida Principal 345'),
('Valentina Rivas', 1, '98765432', '222333444', 'valentina@example.com', 'Boulevard Principal 678'),
('Héctor Méndez', 2, 'F1234567', '444555666', 'hector@example.com', 'Plaza Central 901'),
('Ana Belén Mendoza', 1, '43218765', '777888999', 'ana@example.com', 'Carrera Principal 234'),
('Erick Montes', 1, '98765432', '666777888', 'erick@example.com', 'Calle Principal 567'),
('Juliana Navarro', 3, '98765432109', '111222333', 'juliana@example.com', 'Avenida Secundaria 890');


INSERT INTO PRODUCTO (NOMBRE_PRO, PRECIO_VENTA, DESCRIPCION, STOCK, ID_CONSOLA) VALUES
('The Last of Us Part II', 39.99, 'Tras una pandemia que ha devastado la civilización, Ellie y Joel luchan por sobrevivir en un mundo despiadado en este emocionante videojuego de acción y supervivencia.', 10, 1),
('FIFA 21', 49.99, 'Disfruta del emocionante deporte del fútbol con esta nueva entrega de la popular saga de videojuegos FIFA.', 30, 1),
('God of War', 39.99, 'Embárcate en una aventura llena de acción y mitología en la piel del poderoso guerrero espartano Kratos en este videojuego de acción y aventuras.', 15, 1),
('Super Mario Party', 49.99, 'Disfruta de divertidos minijuegos y competencias con tus personajes favoritos de Mario en este juego de fiesta para Nintendo Switch.', 20, 3),
('The Legend of Zelda: Breath of the Wild', 59.99, 'Explora un vasto mundo abierto lleno de aventuras y misterios en esta aclamada entrega de la saga The Legend of Zelda para Nintendo Switch.', 10, 3),
('Animal Crossing: New Horizons', 49.99, 'Crea tu propia isla paradisíaca y vive una vida relajada en este encantador juego de simulación social para Nintendo Switch.', 25, 3),
('Halo Infinite', 59.99, 'Únete al Jefe Maestro en su última batalla contra las fuerzas alienígenas en este esperado título exclusivo de Xbox Series X/S.', 10, 2),
('Forza Horizon 4', 49.99, 'Disfruta de carreras emocionantes en un mundo abierto británico en este aclamado juego de conducción para Xbox Series X/S.', 15, 2),
('Gears 5', 39.99, 'Lucha contra los enemigos alienígenas y descubre los secretos de Sera en este intenso videojuego de disparos en tercera persona para Xbox Series X/S.', 20, 2),
('PES 2022', 49.99, 'Experimenta la emoción del fútbol con este simulador de fútbol realista que ofrece una jugabilidad fluida y gráficos impresionantes.', 10, 4),
('Monster Hunter Rise', 59.99, 'Embárcate en emocionantes cacerías de monstruos en un mundo lleno de vida en este exitoso juego de acción y aventuras para Nintendo Switch.', 15, 4),
('Ratchet & Clank: Rift Apart', 59.99, 'Únete a Ratchet y Clank en una aventura interdimensional llena de acción y humor en este nuevo título exclusivo de PlayStation 5.', 5, 5),
('Spider-Man: Miles Morales', 49.99, 'Únete a Miles Morales y lucha contra el crimen en las calles de Nueva York en este emocionante juego de acción y aventuras para PlayStation 5.', 20, 5),
('Assassin''s Creed Valhalla', 59.99, 'Viaja al siglo IX y vive la epopeya vikinga en este videojuego de acción y mundo abierto ambientado en la era de los vikingos.', 10, 6),
('Far Cry 6', 59.99, 'Adéntrate en la isla de Yara y lucha contra un régimen opresivo en este videojuego de disparos y mundo abierto lleno de acción y adrenalina.', 15, 6),
('The Sims 4', 39.99, 'Crea y controla la vida de tus Sims en este popular juego de simulación de vida, donde podrás construir casas, tener carreras profesionales y más.', 30, 7),
('Madden NFL 22', 49.99, 'Vive la emoción del fútbol americano con este simulador deportivo que ofrece realismo y autenticidad en cada jugada.', 20, 7),
('Resident Evil Village', 59.99, 'Explora un pueblo aterrador y lucha por tu vida en este videojuego de horror y supervivencia que es la continuación de la saga Resident Evil.', 10, 8),
('Call of Duty: Black Ops Cold War', 59.99, 'Sumérgete en la guerra fría y participa en emocionantes batallas en este juego de disparos en primera persona de la popular saga Call of Duty.', 15, 8),
('Final Fantasy VII Remake', 59.99, 'Revive la historia clásica de Final Fantasy VII con impresionantes gráficos y un sistema de combate renovado en este aclamado videojuego de rol.', 25, 9),
('Cyberpunk 2077', 49.99, 'Adéntrate en Night City, una metrópolis futurista llena de peligro y tecnología en este aclamado videojuego de rol y acción.', 10, 9),
('Just Dance 2022', 39.99, 'Ponte en movimiento y disfruta de las mejores canciones con este divertido juego de baile que te hará mover el cuerpo y divertirte en cualquier fiesta.', 20, 10),
('FIFA 22', 59.99, 'Experimenta la emoción del fútbol con esta nueva entrega de la popular saga de videojuegos FIFA, que presenta mejoras en la jugabilidad y los gráficos.', 15, 10);


INSERT INTO COMPRA (ID_CLIENTE, ID_PRODUCTO, CANTIDAD, FECHA_COMPRA)
VALUES
  -- Cliente 1
  (1, 1, 2, '2023-01-10'),
  (1, 3, 1, '2023-02-05'),
  (1, 7, 3, '2023-03-20'),
  -- Cliente 2
  (2, 2, 1, '2023-01-15'),
  (2, 4, 1, '2023-02-15'),
  (2, 6, 2, '2023-03-10'),
  -- Cliente 3
  (3, 1, 1, '2023-01-20'),
  (3, 5, 1, '2023-03-15'),
  (3, 9, 2, '2023-05-05'),
  -- Cliente 4
  (4, 3, 2, '2023-01-25'),
  (4, 4, 2, '2023-04-01'),
  (4, 10, 1, '2023-05-15'),
  -- Cliente 5
  (5, 2, 1, '2023-02-01'),
  (5, 6, 1, '2023-05-20'),
  (5, 8, 3, '2023-07-01'),
  -- Cliente 6
  (6, 1, 3, '2023-02-05'),
  (6, 5, 2, '2023-06-10'),
  (6, 10, 1, '2023-08-15'),
  -- Cliente 7
  (7, 3, 1, '2023-02-10'),
  (7, 7, 1, '2023-07-20'),
  (7, 9, 2, '2023-09-01'),
  -- Cliente 8
  (8, 2, 2, '2023-02-15'),
  (8, 4, 1, '2023-04-25'),
  (8, 8, 1, '2023-09-15'),
  -- Cliente 9
  (9, 1, 1, '2023-02-20'),
  (9, 3, 1, '2023-07-05'),
  (9, 5, 2, '2023-09-25'),
  -- Cliente 10
  (10, 4, 2, '2023-03-01'),
  (10, 6, 1, '2023-06-15'),
  (10, 7, 1, '2023-10-05'),
  -- Cliente 11 a 30: Se asigna una compra de cada producto
  (11, 1, 1, '2023-03-05'),
  (12, 2, 1, '2023-03-10'),
  (13, 3, 1, '2023-03-15'),
  (14, 4, 1, '2023-03-20'),
  (15, 5, 1, '2023-03-25'),
  (16, 6, 1, '2023-03-30'),
  (17, 7, 1, '2023-04-05'),
  (18, 8, 1, '2023-04-10'),
  (19, 9, 1, '2023-04-15'),
  (20, 10, 1, '2023-04-20'),
  (21, 1, 1, '2023-04-25'),
  (22, 2, 1, '2023-05-01'),
  (23, 3, 1, '2023-05-05'),
  (24, 4, 1, '2023-05-10'),
  (25, 5, 1, '2023-05-15'),
  (26, 6, 1, '2023-05-20'),
  (27, 7, 1, '2023-05-25'),
  (28, 8, 1, '2023-05-30'),
  (29, 9, 1, '2023-06-05'),
  (30, 10, 1, '2023-06-10');


-- SELECT * FROM COMPRA;
DELIMITER // 
CREATE PROCEDURE listarProductosDisponibles()
BEGIN
	SELECT * FROM PRODUCTO
    where VISIBLE AND STOCK>1;
END
//

DELIMITER //
CREATE PROCEDURE productoConsola(in idconsola int)
BEGIN
	SELECT *
    FROM PRODUCTO P
    JOIN CONSOLA C ON C.ID_CONSOLA = P.ID_CONSOLA
    WHERE P.ID_CONSOLA=idconsola AND P.VISIBLE AND STOCK>1;
END
//


DELIMITER //
CREATE PROCEDURE listarConsolas(i int)
BEGIN
	SELECT c.ID_CONSOLA, c.NOMBRE_CONSOLA, COUNT(p.ID_PRODUCTO) AS CANTIDAD_PRODUCTOS
	FROM CONSOLA c
	LEFT JOIN PRODUCTO p ON c.ID_CONSOLA = p.ID_CONSOLA
    where c.visible = i
	GROUP BY c.ID_CONSOLA, c.NOMBRE_CONSOLA;
END
//

DELIMITER // 
CREATE PROCEDURE efectuarCompra(idCliente int, idProducto int, cantidad int, fecha date)
BEGIN
	INSERT INTO COMPRA VALUES(NULL, idCliente, idProducto, cantidad, fecha, 1);
    UPDATE PRODUCTO SET STOCK=STOCK-cantidad WHERE ID_PRODUCTO=idProducto;
END
//

DELIMITER // 
CREATE PROCEDURE listarCompras(i int)
BEGIN 
	SELECT C.ID_COMPRA AS ID,
    T.NOMBRE_O_RZON_SOCIAL AS CLIENTE, 
    P.NOMBRE_PRO AS PRODUCTO,
    C.FECHA_COMPRA, 
    C.CANTIDAD, C.CANTIDAD*P.PRECIO_VENTA AS PAGOTOTAL 
    FROM COMPRA C
    JOIN CLIENTE T ON T.ID_CLIENTE = C.ID_CLIENTE
    JOIN PRODUCTO P ON C.ID_PRODUCTO = P.ID_PRODUCTO
    where C.visible=i;
END
//

DELIMITER // 
CREATE PROCEDURE infoCompra(idcompra int)
BEGIN 
	SELECT C.ID_COMPRA AS ID,
    T.NOMBRE_O_RZON_SOCIAL AS CLIENTE, 
    P.NOMBRE_PRO AS PRODUCTO,
    C.FECHA_COMPRA, 
    C.CANTIDAD, C.CANTIDAD*P.PRECIO_VENTA AS PAGOTOTAL 
    FROM COMPRA C
    JOIN CLIENTE T ON T.ID_CLIENTE = C.ID_CLIENTE
    JOIN PRODUCTO P ON C.ID_PRODUCTO = P.ID_PRODUCTO
    where C.ID_COMPRA=idcompra;
END
//

call infoCompra(4);
DELIMITER // 
CREATE PROCEDURE listarComprasCliente(idcliente int)
BEGIN 
	SELECT C.ID_COMPRA AS ID,
    T.NOMBRE_O_RZON_SOCIAL AS CLIENTE, 
    P.NOMBRE_PRO AS PRODUCTO,
    C.FECHA_COMPRA, 
    C.CANTIDAD, C.CANTIDAD*P.PRECIO_VENTA AS PAGOTOTAL 
    FROM COMPRA C
    JOIN CLIENTE T ON T.ID_CLIENTE = C.ID_CLIENTE
    JOIN PRODUCTO P ON C.ID_PRODUCTO = P.ID_PRODUCTO
    WHERE C.ID_CLIENTE = idcliente;
END
//



DELIMITER // 
CREATE PROCEDURE topClientes()
BEGIN 
SELECT c.ID_CLIENTE, c.NOMBRE_O_RZON_SOCIAL, T.COD_TIPO_DOC, c.DOCUMENTO, C.TELEFONO, C.EMAIL, C.DIRECCION, COUNT(*) AS TOTAL_COMPRAS
FROM CLIENTE c
JOIN COMPRA co ON c.ID_CLIENTE = co.ID_CLIENTE
JOIN TIPO_DOC T ON T.ID_TIPO_DOC = C.TIPO_DOC
WHERE C.VISIBLE
GROUP BY c.ID_CLIENTE, c.NOMBRE_O_RZON_SOCIAL
ORDER BY TOTAL_COMPRAS DESC
LIMIT 10;
END
//

DELIMITER //
CREATE PROCEDURE buscarCliente(texto varchar(30))
BEGIN
SELECT *
FROM CLIENTE C
JOIN TIPO_DOC T ON T.ID_TIPO_DOC = C.TIPO_DOC
WHERE NOMBRE_O_RZON_SOCIAL LIKE CONCAT('%', texto, '%') and C.VISIBLE;
END
//

DELIMITER //
CREATE PROCEDURE topProductos()
BEGIN
    SELECT p.*, c.NOMBRE_CONSOLA, COUNT(*) AS TOTAL_VENTAS
    FROM PRODUCTO p
    JOIN COMPRA co ON p.ID_PRODUCTO = co.ID_PRODUCTO
    JOIN CONSOLA c ON p.ID_CONSOLA = c.ID_CONSOLA
    WHERE P.VISIBLE
    GROUP BY p.ID_PRODUCTO, c.NOMBRE_CONSOLA
    ORDER BY TOTAL_VENTAS DESC
    LIMIT 10;
END;
//

DELIMITER //
CREATE PROCEDURE buscarProductos(IN cadena VARCHAR(100))
BEGIN
    SELECT p.*, c.NOMBRE_CONSOLA
    FROM PRODUCTO p
    JOIN CONSOLA c ON p.ID_CONSOLA = c.ID_CONSOLA
    WHERE p.NOMBRE_PRO LIKE CONCAT('%', cadena, '%');
END;
//

DELIMITER //
CREATE PROCEDURE topConsolas()
BEGIN
    SELECT c.*, COUNT(*) AS TOTAL_VENTAS
    FROM CONSOLA c
    JOIN PRODUCTO p ON c.ID_CONSOLA = p.ID_CONSOLA
    JOIN COMPRA co ON p.ID_PRODUCTO = co.ID_PRODUCTO
    WHERE C.VISIBLE
    GROUP BY c.ID_CONSOLA, c.NOMBRE_CONSOLA
    ORDER BY TOTAL_VENTAS DESC
    LIMIT 5;
END;
//

DELIMITER //
CREATE PROCEDURE topCompras()
BEGIN
    SELECT c.ID_COMPRA as ID, cl.NOMBRE_O_RZON_SOCIAL AS CLIENTE, p.NOMBRE_PRO AS PRODUCTO, c.CANTIDAD, c.FECHA_COMPRA,
    C.CANTIDAD*P.PRECIO_VENTA AS PAGOTOTAL
    FROM COMPRA c
    JOIN CLIENTE cl ON c.ID_CLIENTE = cl.ID_CLIENTE
    JOIN PRODUCTO p ON c.ID_PRODUCTO = p.ID_PRODUCTO
    WHERE c.VISIBLE AND cl.VISIBLE AND p.VISIBLE
    ORDER BY c.FECHA_COMPRA DESC
    LIMIT 5;
END;
//

DELIMITER //
CREATE PROCEDURE buscarCompras (
    IN fechaInicio DATE,
    IN fechaFin DATE
)
BEGIN
SELECT C.ID_COMPRA AS ID,
    T.NOMBRE_O_RZON_SOCIAL AS CLIENTE, 
    P.NOMBRE_PRO AS PRODUCTO,
    C.FECHA_COMPRA, 
    C.CANTIDAD, C.CANTIDAD*P.PRECIO_VENTA AS PAGOTOTAL 
    FROM COMPRA C
    JOIN CLIENTE T ON T.ID_CLIENTE = C.ID_CLIENTE
    JOIN PRODUCTO P ON C.ID_PRODUCTO = P.ID_PRODUCTO
	WHERE FECHA_COMPRA BETWEEN fechaInicio AND fechaFin;
END //

UPDATE COMPRA SET VISIBLE = NOT VISIBLE WHERE ID_COMPRA = 1;



