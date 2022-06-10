DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
DELETE FROM user;
INSERT INTO user (id, name, age, email) VALUES
(1, 'Tom', 18, 'tom@rigerwu.com'),
(2, 'Lily', 20, 'lily@rigerwu.com'),
(3, 'Jack', 28, 'jack@rigerwu.com'),
(4, 'Rose', 21, 'rose@rigerwu.com'),
(5, 'Bob', 24, 'bob@rigerwu.com');