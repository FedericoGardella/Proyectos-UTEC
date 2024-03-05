-- (a) Insert 
insert into emailf
values('RomanHerreros@gmail.com','Juan Angel','Herreros','Romaguera','7854'),
	   ('FauMendoza@outlook.com','Faustino','Heredia','Mendoza','778'),
	   ('MariaYe@hotmail.com','Maria Elisa','Ye','Republica Argentina','12'),
	   ('Falcon560@gmail.com','Sara','Falcon','España','446'),
	   ('Bilal@gmail.com','Bilal','Laguna','Egido','468');
	   
insert into emaile VALUES
('ZonaGadget@gmail.com','Zona Gadget','Rincon','911','26288327'),
('TechMarket@outlook.com','Tech Market','Pocitos','468','24831122'),
('PlanetaDigital@gmail.com','Planeta Digital','Constitucion','74','43345976'),
('GeniusStore@hotmail.com','Genius Store','Gaboto esq La Paz','48','45224999'),
('RobotShop@gmail.com','Robot Shop','Uruguay','789','42668350');

insert into usuarios values
('Gabriel','Gabriel','Acosta','10/03/1998','Hombre','Gaby456'),
('Gerd15','Gerardo','Macedo','15-09-1982','Hombre','Macedo1234'),
('Blanco','Rodrigo','Blanco','1991/10/22','Hombre','Rodrigo1479'),
('Paola_pomelo','Paola','Pomelo','1974-01-16','Mujer','Pomelo16174'),
('ElizaLopez','Elisa','Lopez','13/07/1956','Mujer','ElizaLopez1956');

insert into precios(montodia,fecha, mantenimiento) VALUES
(300,'1/9/2021', false),
(250,'1/7/2021', false),
(260,'1/8/2022', false),
(100,'1/6/2022', true),
(350,'1/9/2022', false);

insert into modelo values
('ChromeBook 4','Samsung'),
('Surface 15','Microsoft'),
('Blade 15','Razer'),
('Aspire 5','Acer'),
('Latitude 7490','Dell'),
('Aspire 4','Acer'),
('Optiplex 990','Dell'),
('Optiplex 3070','Dell'),
('Surface 16','Microsoft'),
('Basic 2','MAGIC');

insert into empresas values
('215724868513','ZonaGadget@gmail.com'),
('233404641272','TechMarket@outlook.com'),
('589959653001','PlanetaDigital@gmail.com'),
('636101381620','GeniusStore@hotmail.com'),
('434829363878','RobotShop@gmail.com');

insert into emp_contactos VALUES
('215724868513','Homero Sinson'),
('233404641272','Bob Burger'),
('589959653001','Rick Sanchez'),
('636101381620','Bender Barreda'),
('434829363878','Bojack Horseman');

insert into funcionarios values
	   ('45466322','RomanHerreros@gmail.com'),
	   ('39350717','FauMendoza@outlook.com'),
	   ('55403213','MariaYe@hotmail.com'),
	   ('32043796','Falcon560@gmail.com'),
	   ('25884560','Bilal@gmail.com');

insert into equipos VALUES
('9190529297','ChromeBook 4','SAMSUNG XE350XBA-K01US Chromebook 4 + Chrome OS 15.6 "Full HD Intel Celeron Processor N4000 4GB RAM 32Gb Emmc Gigabit Wi-Fi, Silver 3.8','215724868513'),
('3446946171','Surface 15','Pantalla: 15" MULTITÁCTIL, 2496 x 1664 píxeles. Procesador: AMD RYZEN 5 3580U, QUAD CORE 2,1 GHz - 3,7 GHz. Gráficos Integrados: RADEON VEGA 9. Sistema Operativo: WINDOWS 10 HOME. Memoria RAM: 8 GB DDR4. Disco Sólido: 128 GB. Cámara Web.','589959653001'),
('4256592764','Blade 15','Procesador: Intel Core i7-10875H 10ma Generación 2.3 GHz - 5.1 GHz (Max Turbo). Memoria RAM: 32GB DDR4 2933 MHz. Almacenamiento: 1TB PCIe NVMe M.2. Tarjeta Gráfica: NVIDIA GeForce RTX 3080 8GB GDDR6. Sistema Operativo: Windows 10','636101381620'),
('1028839006','Aspire 5','Procesador Intel Core i3 - 10ª GENERACIÓN (10110U). Memoria RAM 12GB. Disco Sólido 120GB. Pantalla 15,6" IPS c/teclado numérico. Resolución: 1920x1080. Tarjeta de video UHD Graphics. WebCam HD','434829363878'),
('2163749613','Latitude 7490','Procesador Intel Core i7-8650U (Quad Core, caché de 8M, 1.9 GHz, 15 W, vPro). 16 GB, 2 x 8 GB, 2400 MHz DDR4 Memory M.2 512 GB SATA Clase 20 Unidad de estado sólido. 14.1 pulgadas FHD (1920 x 1080)','233404641272'),
('5898366367','Aspire 4','Pantalla: 15" MULTITÁCTIL, 2496 x 1664 píxeles. Procesador: AMD RYZEN 5 3580U, QUAD CORE 2,1 GHz - 3,7 GHz. Gráficos Integrados: RADEON VEGA 9. Sistema Operativo: WINDOWS 10 HOME. Memoria RAM: 8 GB DDR4. Disco Sólido: 128 GB. Cámara Web.','215724868513'),
('9311816730','Optiplex 990','Procesador Intel Core i3 - 10ª GENERACIÓN (10110U). Memoria RAM 12GB. Disco Sólido 120GB. Pantalla 15,6" IPS c/teclado numérico. Resolución: 1920x1080. Tarjeta de video UHD Graphics. WebCam HD','215724868513'),
('2069803579','Optiplex 3070','Procesador Intel Core i7-8650U (Quad Core, caché de 8M, 1.9 GHz, 15 W, vPro). 16 GB, 2 x 8 GB, 2400 MHz DDR4 Memory M.2 512 GB SATA Clase 20 Unidad de estado sólido. 14.1 pulgadas FHD (1920 x 1080)','589959653001'),
('5472185357','Surface 15','Procesador: Intel Core i7-10875H 10ma Generación 2.3 GHz - 5.1 GHz (Max Turbo). Memoria RAM: 32GB DDR4 2933 MHz. Almacenamiento: 1TB PCIe NVMe M.2. Tarjeta Gráfica: NVIDIA GeForce RTX 3080 8GB GDDR6. Sistema Operativo: Windows 10','589959653001'),
('1481819567','Basic 2','Procesador Intel Core i3 - 10ª GENERACIÓN (10110U). Memoria RAM 12GB. Disco Sólido 120GB. Pantalla 15,6" IPS c/teclado numérico. Resolución: 1920x1080. Tarjeta de video UHD Graphics. WebCam HD','589959653001');

insert into deposito VALUES
('A','1'),
('A','2'),
('A','3'),
('A','4'),
('A','5'),
('A','6'),
('A','7'),
('A','8'),
('A','9'),
('A','10'),
('B','1'),
('B','2'),
('B','3'),
('B','4'),
('B','5');

insert into kits(rut) VALUES
('589959653001'),
('215724868513'),
('636101381620'),
('434829363878'),
('233404641272');

insert into emailef values
('Gabrielnicolas10@hotmail.com','Gabriel'),
('Gerard_mace@gmail.com','Gerd15'),
('CuartetazoRodrigo@gmail.com','Blanco'),
('Paola_1974@hotmail.com','Paola_pomelo'),
('ElisaLopez56@hotmail.com','ElizaLopez');

insert into empl_firma(email) values
('Gabrielnicolas10@hotmail.com'),
('Gerard_mace@gmail.com'),
('CuartetazoRodrigo@gmail.com'),
('Paola_1974@hotmail.com'),
('ElisaLopez56@hotmail.com');

insert into r_almacenae values
('2163749613','A','2','3/09/2022','8:30'),
('1028839006','A','1','13/09/2022','8:30'),
('3446946171','A','3','5/09/2022','18:30'),
('9190529297','A','5','10/09/2022','12:00'),
('4256592764','A','4','20/09/2022','12:30'),
('5898366367','A','7','2/09/2022','10:00'),
('2069803579','A','6','1/09/2022','9:00'),
('9311816730','A','10','2/09/2022','13:00'),
('1481819567','A','8','3/09/2022','10:00'),
('5472185357','A','9','3/09/2022','9:00');

insert into r_almacenak values
('1','B','1','4/09/2022','8:30'),
('5','B','2','14/09/2022','12:30'),
('4','B','3','6/09/2022','12:00'),
('3','B','4','11/09/2022','18:30'),
('2','B','5','21/09/2022','18:30');

insert into orden_envio(nroremito,empreparto,fecha,ci,nroserie,idkit,idempleado,fechasolicitud) VALUES
('1','Leo Repartos S.A','5/10/2022','45466322','9190529297','2','1','2/10/2022'),
('2','Asspera S.A','7/10/2022','39350717','3446946171','1','1','6/10/2022'),
('3','Cinamer Repartos','12/10/2022','55403213','2163749613','5','2','11/10/2022'),
('4','Leo Repartos S.A','5/10/2022','32043796','4256592764','3','2','4/10/2022'),
('5','odneuqol Repartos','1/10/2022','25884560','1028839006','4','4','29/09/2022');

insert into r_devuelven VALUES
('45466322','9190529297','3/11/2022','12:00','1'),
('39350717','3446946171','7/11/2022','12:00','0'),
('55403213','2163749613','1/11/2022','13:00','0'),
('32043796','4256592764','6/11/2022','8:00','0'),
('25884560','1028839006','4/11/2022','9:00','1');

insert into r_costoe VALUES
('5','9190529297'),
('5','3446946171'),
('5','4256592764'),
('5','1028839006'),
('5','2163749613'),
('5','5898366367'),
('5','9311816730'),
('5','2069803579'),
('5','5472185357'),
('5','1481819567');



insert into r_costok VALUES
('5','1'),
('5','2'),
('5','3'),
('5','4'),
('5','5');

insert into r_administrae(nroserie,idempleado,tipo) values
('2163749613','1','Almacena'),
('1028839006','1','Almacena'),
('3446946171','2','Almacena'),
('9190529297','1','Almacena'),
('4256592764','3','Almacena'),
('5898366367','4','Almacena'),
('2069803579','5','Almacena'),
('9311816730','1','Almacena'),
('1481819567','1','Almacena'),
('5472185357','1','Almacena'),
('9190529297','2','Recibe'),
('3446946171','1','Recibe'),
('2163749613','4','Recibe'),
('4256592764','5','Recibe'),
('1028839006','4','Recibe');

insert into r_administrak(idkit,idempleado,tipo) values
('1','1','Almacena'),
('2','1','Almacena'),
('3','2','Almacena'),
('4','1','Almacena'),
('5','3','Almacena');

-- Consultas
-- (b)
select distinct r_almacenae.letra, r_almacenae.numero, (modelo.marca, equipos.modelo) as "equipo/kit", equipos.rut,emaile.razonsocial from r_almacenae
join equipos on equipos.nroserie = r_almacenae.nroserie
join modelo ON modelo.modelo = equipos.modelo
join empresas on empresas.rut = equipos.rut
join emaile ON emaile.email = empresas.email
union all
select distinct r_almacenak.letra, r_almacenak.numero,(kits.id,kits.id) as "equipo/kit", kits.rut,emaile.razonsocial from r_almacenak
join kits on kits.id = r_almacenak.id
join empresas on empresas.rut = kits.rut
 join emaile on emaile.email = empresas.email
order by razonsocial asc;

-- (c)

select emaile.razonsocial, count(*) from equipos
natural join empresas
natural join emaile
join r_almacenae on equipos.nroserie = r_almacenae.nroserie
where r_almacenae.fecha > '2022-09-01' and r_almacenae.fecha < '2022-09-10'
group by emaile.razonsocial
order by count(*) desc
limit 3;

-- (d)

select 
sum ((orden_envio.fecha - r_almacenae.fecha) * precios.montodia ) as monto_equipo, 
sum ((orden_envio.fecha - r_almacenak.fecha) * precios.montodia ) as montokit, 
case
when r_devuelven.mantenimiento = true then (select montodia from precios where mantenimiento = true) 
else 0 end as costo_mantenimiento, 
sum ((orden_envio.fecha - r_almacenae.fecha) * precios.montodia ) + sum ((orden_envio.fecha - r_almacenak.fecha) * precios.montodia ) +
(case
when r_devuelven.mantenimiento = true then (select montodia from precios
where mantenimiento = true) 
else 0 end )as total from equipos
join r_almacenae on r_almacenae.nroserie = equipos.nroserie
join orden_envio on orden_envio.nroserie = equipos.nroserie
join r_costoe on r_costoe.nroserie = equipos.nroserie
join precios on precios.id = r_costoe.id
join empresas on empresas.rut = equipos.rut
join kits on kits.rut = empresas.rut
join r_costok on r_costok.idkit = kits.id
join r_almacenak on r_almacenak.id = kits.id
join r_devuelven on r_devuelven.nroserie = equipos.nroserie
where empresas.rut = '215724868513' and orden_envio.fecha > '2022-09-01' and orden_envio.fecha < '2022-10-10'
group by r_devuelven.mantenimiento;

-- (e)

select emaile.razonsocial,emaile.telefono from equipos
join r_devuelven on equipos.nroserie = r_devuelven.nroserie
join empresas on empresas.rut = equipos.rut
join emaile on emaile.email = empresas.email
where r_devuelven.mantenimiento=false;

-- (f)

select 
orden_envio.nroremito,
orden_envio.empreparto,
(emailf.nombre , emailf.apellido) as "Nombre Completo del destinatario",
(emailf.calle,emailf.numero) as "Direccion del destinatario",
orden_envio.fecha,
orden_envio.nroserie,
empl_firma.id as "Id del empleado",
(usuarios.nombre, usuarios.apellido) as "Nombre Completo del empleado"
from orden_envio
natural join funcionarios
natural join emailf
join empl_firma on empl_firma.id = orden_envio.idempleado
join emailef on emailef.email = empl_firma.email
join usuarios on usuarios.usuario = emailef.usuario;