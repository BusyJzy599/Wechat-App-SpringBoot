CREATE TABLE IF NOT EXISTS `address`
(
    `id`          INT UNSIGNED AUTO_INCREMENT,
    `user_id`     int          not null,
    `name`        VARCHAR(64)  NOT NULL,
    `phone`       VARCHAR(20)  NOT NULL,
    `address`     VARCHAR(240) not null,
    `location`    varchar(30)  not null,
    `type`        int          not null comment '0:自己,1:亲友,2:他人',
    `create_time` timestamp    not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into address(id, user_id, name, phone,address, location, type, create_time)
values (1, 4, '小喆', '13773665423', '重庆市,重庆市,沙坪坝区,重庆师范大学', '106.309062,29.613748', 0, '2020-05-31 17:06:06'),
       (2, 4, '小文', '13773665423', '北京市,北京市,海淀区,北京大学', '116.316833,39.998877', 1, '2020-05-31 19:06:06');


