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