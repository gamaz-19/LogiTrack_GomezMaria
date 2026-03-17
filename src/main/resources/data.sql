use logitrack_gomezmaria;

-- roles
insert into rol (nombre) values ('admin');
insert into rol (nombre) values ('empleado');


-- personas
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('juan','perez','1001','3001111111','juan@mail.com','calle10');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('ana','lopez','1002','3001111112','ana@mail.com','calle11');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('carlos','gomez','1003','3001111113','carlos@mail.com','calle12');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('maria','torres','1004','3001111114','maria@mail.com','calle13');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('luis','rojas','1005','3001111115','luis@mail.com','calle14');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('sofia','martinez','1006','3001111116','sofia@mail.com','calle15');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('diego','castro','1007','3001111117','diego@mail.com','calle16');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('laura','ramirez','1008','3001111118','laura@mail.com','calle17');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('pedro','morales','1009','3001111119','pedro@mail.com','calle18');
insert into persona (nombre,apellido,documento,telefono,email,direccion) values ('julia','diaz','1010','3001111120','julia@mail.com','calle19');


-- usuarios
insert into usuario (nombreusuario,persona_id,rol_id) values ('juanuser',1,1);
insert into usuario (nombreusuario,persona_id,rol_id) values ('anauser',2,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('carlosuser',3,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('mariauser',4,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('luisuser',5,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('sofiauser',6,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('diegouser',7,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('laurauser',8,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('pedrouser',9,2);
insert into usuario (nombreusuario,persona_id,rol_id) values ('juliauser',10,2);

-- categorias
insert into categoria (nombre) values ('electronica');
insert into categoria (nombre) values ('ropa');
insert into categoria (nombre) values ('hogar');
insert into categoria (nombre) values ('tecnologia');
insert into categoria (nombre) values ('deportes');
insert into categoria (nombre) values ('alimentos');
insert into categoria (nombre) values ('herramientas');
insert into categoria (nombre) values ('papeleria');
insert into categoria (nombre) values ('juguetes');
insert into categoria (nombre) values ('muebles');


-- productos
insert into producto (nombre,categoria_id,precio) values ('laptop',1,3500);
insert into producto (nombre,categoria_id,precio) values ('camiseta',2,50);
insert into producto (nombre,categoria_id,precio) values ('silla',3,120);
insert into producto (nombre,categoria_id,precio) values ('mouse',4,40);
insert into producto (nombre,categoria_id,precio) values ('balon',5,80);
insert into producto (nombre,categoria_id,precio) values ('arroz',6,10);
insert into producto (nombre,categoria_id,precio) values ('martillo',7,35);
insert into producto (nombre,categoria_id,precio) values ('cuaderno',8,8);
insert into producto (nombre,categoria_id,precio) values ('muneco',9,25);
insert into producto (nombre,categoria_id,precio) values ('mesa',10,200);


-- bodegas
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_central','zona1',1000,1);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_norte','zona2',800,2);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_sur','zona3',700,3);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_oriente','zona4',600,4);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_occidente','zona5',900,5);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_a','zona6',500,6);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_b','zona7',400,7);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_c','zona8',300,8);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_d','zona9',200,9);
insert into bodega (nombre,ubicacion,capacidad,encargado_id) values ('bodega_e','zona10',100,10);


-- inventario
insert into inventario (bodega_id,producto_id,stock) values (1,1,50);
insert into inventario (bodega_id,producto_id,stock) values (2,2,100);
insert into inventario (bodega_id,producto_id,stock) values (3,3,70);
insert into inventario (bodega_id,producto_id,stock) values (4,4,60);
insert into inventario (bodega_id,producto_id,stock) values (5,5,90);
insert into inventario (bodega_id,producto_id,stock) values (6,6,120);
insert into inventario (bodega_id,producto_id,stock) values (7,7,40);
insert into inventario (bodega_id,producto_id,stock) values (8,8,200);
insert into inventario (bodega_id,producto_id,stock) values (9,9,80);
insert into inventario (bodega_id,producto_id,stock) values (10,10,30);


-- movimientos
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('entrada',1,null,1);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('salida',2,2,null);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('transferencia',3,1,2);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('entrada',4,null,3);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('salida',5,4,null);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('transferencia',6,5,6);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('entrada',7,null,7);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('salida',8,8,null);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('transferencia',9,9,10);
insert into movimiento (tipo,usuario_id,bodegaorigen_id,bodegadestino_id) values ('entrada',10,null,5);


-- detalle movimiento
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (1,1,10);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (2,2,5);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (3,3,8);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (4,4,12);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (5,5,7);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (6,6,20);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (7,7,4);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (8,8,15);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (9,9,6);
insert into detalle_movimiento (movimiento_id,producto_id,cantidad) values (10,10,3);


-- auditoria
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (1,'insert','persona',1);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (2,'insert','usuario',2);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (3,'update','producto',3);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (4,'delete','categoria',4);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (5,'insert','inventario',5);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (6,'update','bodega',6);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (7,'insert','movimiento',7);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (8,'update','detalle_movimiento',8);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (9,'delete','producto',9);
insert into auditoria (usuario_id,operacion,entidad,entidad_id) values (10,'insert','categoria',10);