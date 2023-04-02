DELIMITER $$

CREATE PROCEDURE `insert_random_user_data`(IN num_rows INT)
BEGIN
    DECLARE i INT DEFAULT 0;

    WHILE i < num_rows DO
        INSERT IGNORE INTO user (last_name, first_name, username, password, email, created_date, last_modified_date)
        VALUES (
            CONCAT('LN', FLOOR(RAND() * 100000)),
            CONCAT('FN', FLOOR(RAND() * 100000)),
            REPLACE(UUID(), '-', ''),
            UUID(),
            CONCAT('email', FLOOR(RAND() * 100000), '@example.com'),
            NOW(),
            NOW()
        );
        SET i = i + 1;
END WHILE;
END $$

DELIMITER ;

CALL insert_random_user_data(10000);