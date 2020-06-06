CREATE TABLE IF NOT EXISTS `goods`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(64) NOT NULL,
   `desc` VARCHAR(120) NOT NULL,
   `avatar` VARCHAR(200) NOT NULL,
   `seller_id` INT not null,
   `create_time` timestamp not null default CURRENT_TIMESTAMP,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into  goods ('id','name','desc','avatar','seller_id','create_time') values ('口罩','医用外科口罩','https://img.alicdn.com/imgextra/i3/2207707567248/O1CN01NUVcoo23PdHdZaWkS_!!2207707567248.jpg_60x60q90.jpg',3)