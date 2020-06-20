CREATE TABLE IF NOT EXISTS `goods`
(`id` INT UNSIGNED AUTO_INCREMENT,
 `name` VARCHAR(64) NOT NULL,
 `desc` VARCHAR(120) NOT NULL,
 `avatar` VARCHAR(200) NOT NULL,
 `seller_id` INT not null,
 `create_time` timestamp not null default CURRENT_TIMESTAMP,
 PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into  goods(`id`,`name`,`desc` ,`avatar`,`seller_id`)
values (1,'口罩','医用外科口罩','https://img.alicdn.com/imgextra/i3/2207707567248/O1CN01NUVcoo23PdHdZaWkS_!!2207707567248.jpg_60x60q90.jpg',4),
(2,'消毒液','酒精消毒液',' https://img.alicdn.com/imgextra/i1/2405035918/O1CN01KiEuWI1taUVTonW2u_!!2405035918.jpg_60x60q90.jpg',4),
(3,'除湿袋','强力除湿袋，防霉',' https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i4/1658148935/O1CN01THVSKa2FsHPSLwSql_!!1658148935.jpg_60x60q90.jpg',4),
(4,'笔记本','优选纸质笔记本','https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i3/2087847987/O1CN011yOEZb28s5yZxj4jO_!!2087847987.jpg_60x60q90.jpg',4),
(5,'纸巾','柔顺面料纸巾','https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/4076482146/O1CN010DPeeg1RiuXdkWyaB_!!4076482146.jpg_60x60q90.jpg',4);