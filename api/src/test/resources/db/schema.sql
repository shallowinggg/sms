-- CREATE TABLE verification_configs
-- (
--     `id`             INT PRIMARY KEY AUTOINCREMENT,
--     `name`           VARCHAR(50)   NOT NULL UNIQUE,        -- '名称'
--     `description`    VARCHAR(200)  NOT NULL,               -- '描述'
--     `need_captcha`   BOOLEAN       NOT NULL DEFAULT false, -- '是否需要captcha'
--     `captcha_format` VARCHAR(10)   NULL,                   -- 'captcha形式',
--     `max_send_count` TINYINT(3)    NOT NULL DEFAULT 0,     -- '每天短信验证码最大发送次数'
--     `callback`       VARCHAR(1024) NULL,                   -- 'web hook配置'
--     `status`         TINYINT(1)    NOT NULL,               -- '配置状态，1: 审核中, 2: 上线, 3: 下线'
--     `add_time`       DATETIME      NOT NULL DEFAULT current_timestamp(),
--     `update_time`    DATETIME      NOT NULL DEFAULT current_timestamp()
-- );

CREATE TABLE verification_configs
(
    id             INTEGER PRIMARY KEY NOT NULL,
    name           TEXT                NOT NULL,
    description    TEXT                NOT NULL,
    need_captcha   INTEGER             NOT NULL,
    captcha_format TEXT                NULL,
    max_send_count INTEGER             NOT NULL,
    callback       TEXT                NULL,
    status         INTEGER             NOT NULL
);