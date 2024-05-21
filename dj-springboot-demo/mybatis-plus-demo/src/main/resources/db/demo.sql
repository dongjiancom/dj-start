DROP TABLE IF EXISTS dj;
CREATE TABLE dj(
                   `create_by` VARCHAR(32)    COMMENT '创建人' ,
                   `create_time` DATETIME    COMMENT '创建时间' ,
                   `update_by` VARCHAR(32)    COMMENT '更新人' ,
                   `update_time` DATETIME    COMMENT '更新时间' ,
                   `del_flag` CHAR(1)   DEFAULT 0 COMMENT '是否删除;0为未删除，1为已删除' ,
                   `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
                   `tenant_id` VARCHAR(20) NOT NULL  DEFAULT 0 COMMENT '租户号' ,
                   `phone` VARCHAR(16)    COMMENT '手机号' ,
                   `age` INT    COMMENT '年龄' ,
                   `money` BIGINT    COMMENT '收入' ,
                   `name` VARCHAR(32)    COMMENT '姓名' ,
                   `date_of_birth` DATETIME(255)    COMMENT '出生日期' ,
                   `append` JSON    COMMENT '附加参数JSON' ,
                   `attach` VARCHAR(255)    COMMENT '附件参数Object' ,
                   `card_no_enc` VARCHAR(128)    COMMENT '银行卡号（密文）' ,
                   `card_no_mask` VARCHAR(64)    COMMENT '银行卡号，掩码' ,
                   PRIMARY KEY (id)
)  COMMENT = 'demo-0-表';

DROP TABLE IF EXISTS dj1;
CREATE TABLE dj1(
                    `create_by` VARCHAR(32)    COMMENT '创建人' ,
                    `create_time` DATETIME    COMMENT '创建时间' ,
                    `update_by` VARCHAR(32)    COMMENT '更新人' ,
                    `update_time` DATETIME    COMMENT '更新时间' ,
                    `del_flag` CHAR(1)   DEFAULT 0 COMMENT '是否删除;0为未删除，1为已删除' ,
                    `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
                    `tenant_id` VARCHAR(20) NOT NULL  DEFAULT 0 COMMENT '租户号' ,
                    `phone` VARCHAR(16)    COMMENT '手机号' ,
                    `age` INT    COMMENT '年龄' ,
                    `money` BIGINT    COMMENT '收入' ,
                    `name` VARCHAR(32)    COMMENT '姓名' ,
                    `date_of_birth` DATETIME    COMMENT '出生日期' ,
                    `append` JSON    COMMENT '附加参数JSON' ,
                    `attach` VARCHAR(255)    COMMENT '附件参数Object' ,
                    `card_no_enc` VARCHAR(128)    COMMENT '银行卡号（密文）' ,
                    `card_no_mask` VARCHAR(64)    COMMENT '银行卡号，掩码' ,
                    PRIMARY KEY (id,tenant_id)
)  COMMENT = 'demo-1-表(联合主键)';
