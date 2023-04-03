CREATE DATABASE ds2;
USE ds2;

CREATE TABLE user_extend
(
    user_id            BIGINT   NOT NULL,
    ext_field1         VARCHAR(255) NULL,
    ext_field2         VARCHAR(255) NULL,
    ext_field3         VARCHAR(255) NULL,
    last_modified_date DATETIME NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO user_extend (user_id, ext_field1, ext_field2, ext_field3, last_modified_date) VALUES
    (1, 'A1B2C3', 'D1E2F3', 'G1H2I3', '2023-03-21 12:45:32'),
    (2, 'A2B3C4', 'D2E3F4', 'G2H3I4', '2023-03-22 11:23:45'),
    (3, 'A3B4C5', 'D3E4F5', 'G3H4I5', '2023-03-20 14:56:12'),
    (4, 'A4B5C6', 'D4E5F6', 'G4H5I6', '2023-03-19 09:27:38'),
    (5, 'A5B6C7', 'D5E6F7', 'G5H6I7', '2023-03-21 15:48:23'),
    (6, 'A6B7C8', 'D6E7F8', 'G6H7I8', '2023-03-22 16:59:08'),
    (7, 'A7B8C9', 'D7E8F9', 'G7H8I9', '2023-03-18 13:21:45'),
    (8, 'A8B9C0', 'D8E9F0', 'G8H9I0', '2023-03-19 18:30:52'),
    (9, 'A9B0C1', 'D9E0F1', 'G9H0I1', '2023-03-20 17:42:37'),
    (10, 'A0B1C2', 'D0E1F2', 'G0H1I2', '2023-03-23 11:34:56');