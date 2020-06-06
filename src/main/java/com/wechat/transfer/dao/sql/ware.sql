/**
  个人仓库表
 */
CREATE TABLE IF NOT EXISTS `my_ware`
(
    `id`          INT       not null,
    `seller_id`   int       not null,
    `my_size`     int       not null,
    `my_all_size` int       not null comment '100:3x4,200:6x4,400:12x4',
    `create_time` timestamp not null default CURRENT_TIMESTAMP,
    `change_time` timestamp not null default CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into my_ware (id, seller_id, my_size, my_all_size, create_time, change_time)
values (1, 4, 60, 400, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (325, 4, 45, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

/**
  个人商品表
  对应商品-商家-仓库
 */
CREATE TABLE IF NOT EXISTS `my_goods`
(
    `goods_id`    INT       not null,
    `ware_id`     int       not null,
    `number`      int       not null comment '100:3x4,200:6x4,400:12x4',
    `create_time` timestamp not null default CURRENT_TIMESTAMP,
    `change_time` timestamp not null default CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into my_goods(goods_id, ware_id, number, create_time, change_time)
values (1, 1, 123, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, 51, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1, 325, 520, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 325, 140, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)


