CREATE DATABASE ds1;
USE ds1;

CREATE TABLE user
(
    id                 BIGINT       NOT NULL AUTO_INCREMENT,
    last_name          VARCHAR(16)  NOT NULL,
    first_name         VARCHAR(16)  NOT NULL,
    username           VARCHAR(32)  NOT NULL,
    password           VARCHAR(128) NOT NULL,
    email              VARCHAR(32)  NOT NULL,
    created_date       DATETIME     NOT NULL,
    last_modified_date DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX user_username ON user(username);

INSERT INTO user (username, password, last_name, first_name, email, created_date, last_modified_date) VALUES
    ('user1', 'password1', 'Smith', 'John', 'john.smith@example.com', '2023-03-28 10:00:00', '2023-03-28 10:00:00'),
    ('user2', 'password2', 'Doe', 'Jane', 'jane.doe@example.com', '2023-03-28 10:15:00', '2023-03-28 10:15:00'),
    ('user3', 'password3', 'Johnson', 'Michael', 'michael.johnson@example.com', '2023-03-28 10:30:00', '2023-03-28 10:30:00'),
    ('user4', 'password4', 'Brown', 'Emily', 'emily.brown@example.com', '2023-03-28 10:45:00', '2023-03-28 10:45:00'),
    ('user5', 'password5', 'Lee', 'Daniel', 'daniel.lee@example.com', '2023-03-28 11:00:00', '2023-03-28 11:00:00'),
    ('user6', 'password6', 'Patel', 'Sarah', 'sarah.patel@example.com', '2023-03-28 11:15:00', '2023-03-28 11:15:00'),
    ('user7', 'password7', 'Rodriguez', 'William', 'william.rodriguez@example.com', '2023-03-28 11:30:00', '2023-03-28 11:30:00'),
    ('user8', 'password8', 'Jackson', 'Jessica', 'jessica.jackson@example.com', '2023-03-28 11:45:00', '2023-03-28 11:45:00'),
    ('user9', 'password9', 'Garcia', 'David', 'david.garcia@example.com', '2023-03-28 12:00:00', '2023-03-28 12:00:00'),
    ('user10', 'password10', 'Lopez', 'Olivia', 'olivia.lopez@example.com', '2023-03-28 12:15:00', '2023-03-28 12:15:00');

-- 新增加密列
alter table user add email_encrypted varchar(255) null;
alter table user add email_query_helper varchar(255) null;
alter table user add email_like_query varchar(255) null;

-- 存量数据
UPDATE user SET email_encrypted = 'ew7fvqbv+/IVrwRmCZ5wlgpwnT0oEE3XtxhvqJiTeWI=', email_query_helper = '846575587', email_like_query = 'ipim-tlhuiAdy`lqmd-dpl' WHERE id = 1;
UPDATE user SET email_encrypted = 'Py4ae/T5azZUnPXYBGYEV6tnIfZwyw3reLHIRaXo8Ms=', email_query_helper = '1133967415', email_like_query = 'i`md-epdAdy`lqmd-dpl' WHERE id = 2;
UPDATE user SET email_encrypted = '74Q9a/vSMZPRGoIsHLk4Mg9YEAdVt6n+ziow171Lov4=', email_query_helper = '523231901', email_like_query = 'lhdi`dm-ipimtpmAdy`lqmd-dpl' WHERE id = 3;
UPDATE user SET email_encrypted = 'qWfasymrnDX26wkePvzUJUcekcRU+bdbRTgedOX+vtQ=', email_query_helper = '877810451', email_like_query = 'dlhmx-aqpxmAdy`lqmd-dpl' WHERE id = 4;
UPDATE user SET email_encrypted = 'phc+BMhb385qVmThXbzoGgpwnT0oEE3XtxhvqJiTeWI=', email_query_helper = '380052358', email_like_query = 'e`mhdm-mddAdy`lqmd-dpl' WHERE id = 5;
UPDATE user SET email_encrypted = 'tSbD9uTkH/W/luU8cLuC00cekcRU+bdbRTgedOX+vtQ=', email_query_helper = '992289828', email_like_query = 't`q`i-q`udmAdy`lqmd-dpl' WHERE id = 6;
UPDATE user SET email_encrypted = 'dSXoAlgtpYYKMj0jwkgJooM2YuresVTYVWxLguu5LIs=', email_query_helper = '2050727987', email_like_query = 'xhmmh`l-qpeqhhtdyAdy`lqmd-dpl' WHERE id = 7;
UPDATE user SET email_encrypted = 'JpUdlZTaqSmOX3ESp7PJBQ9YEAdVt6n+ziow171Lov4=', email_query_helper = '1155191406', email_like_query = 'idtthd`-i`dltpmAdy`lqmd-dpl' WHERE id = 8;
UPDATE user SET email_encrypted = 'jTCc/7LvR/e41ZHEaqc6FvaV7Lt/Ae5Vp+3AbcseUd8=', email_query_helper = '2141330138', email_like_query = 'e`uhe-h`qdh`Ady`lqmd-dpl' WHERE id = 9;
UPDATE user SET email_encrypted = 'MQBXIc+n8myYWBJuCEmLNvaV7Lt/Ae5Vp+3AbcseUd8=', email_query_helper = '863892467', email_like_query = 'pmhuh`-mpqdyAdy`lqmd-dpl' WHERE id = 10;