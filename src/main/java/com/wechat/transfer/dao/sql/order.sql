CREATE TABLE IF NOT EXISTS `orders`
(
    `id`            INT UNSIGNED not null,
    `user_id`       int          not null,
    `seller_id`     int          not null,
    `goods_id`      int          not null,
    `dest`          int          not null,
    `ware`          int          not null,
    `status`        int          not null comment '0:商家,1:仓库,2:派送,3:签收',
    `curr_location` varchar(30)  null,
    `create_time`   timestamp    not null ,
    `change_time`   timestamp    not null ,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into orders(id, user_id, seller_id, goods_id, dest, ware, status, curr_location, create_time, change_time)
values (12302, 4, 4, 3, 2, 0, 1, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (12303, 4, 4, 1, 2, 1, 2, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (12304, 4, 4, 2, 2, 1, 3, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (12305, 4, 4, 4, 1, 325, 2, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (12306, 4, 4, 5, 1, 325, 2, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)