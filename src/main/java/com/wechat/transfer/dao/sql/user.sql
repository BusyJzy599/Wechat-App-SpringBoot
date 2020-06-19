CREATE TABLE IF NOT EXISTS `user`
(
    `id`              INT UNSIGNED auto_increment,
    `name`            VARCHAR(64)  NOT NULL,
    `avatar`          VARCHAR(200) NOT NULL,
    `type`            int          not null default 0,
    `create_time`     timestamp    not null default CURRENT_TIMESTAMP,
    `last_login_time` timestamp    not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

