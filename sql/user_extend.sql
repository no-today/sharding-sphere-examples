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

-- 新增加密列
ALTER TABLE user_extend ADD user_id_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD user_id_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_like_query VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field2_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field2_like_query VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_like_query VARCHAR(255) NULL;

-- 存量数据
UPDATE user_extend SET user_id_encrypted = 'YGw3JAwFT0jUgwMmhJiLmQ==', user_id_query_helper = '1', ext_field1_encrypted = 'dffS2tluDuvFQrcSnaD0hA==', ext_field1_query_helper = '1994140647', ext_field1_like_query = '@0A1D4', ext_field2_encrypted = 'kssBPvf0Yvuj5OjNpXvhVA==', ext_field2_like_query = 'E0D1E4', ext_field3_encrypted = 'Bm36nLynUa9wY+0GFJonlg==', ext_field3_query_helper = '2080117566', ext_field3_like_query = 'H0I1H4' WHERE user_id = 1;
UPDATE user_extend SET user_id_encrypted = 'CD26I0Q4S4KqwIh/3Mvecg==', user_id_query_helper = '2', ext_field1_encrypted = '7zo+qKvUMDvB5tkW0f0cUw==', ext_field1_query_helper = '1995065130', ext_field1_like_query = '@1A4D5', ext_field2_encrypted = 'Dlee4a3pDy5429P6/st8dw==', ext_field2_like_query = 'E1D4E5', ext_field3_encrypted = '9qftPRp5orAjdNHr0dZQHA==', ext_field3_query_helper = '2081042049', ext_field3_like_query = 'H1I4H5' WHERE user_id = 2;
UPDATE user_extend SET user_id_encrypted = 'xMilVAx+JYK5Jcbc9KKNRQ==', user_id_query_helper = '3', ext_field1_encrypted = 'T9HujfWlTrxXys2kv0zPDg==', ext_field1_query_helper = '1995989613', ext_field1_like_query = '@4A5D4', ext_field2_encrypted = 'OtNZBwiUVktgIp2+2SE20w==', ext_field2_like_query = 'E4D5E4', ext_field3_encrypted = 'ZJeOWA2qtfeF6mbN0l57ew==', ext_field3_query_helper = '2081966532', ext_field3_like_query = 'H4I5H4' WHERE user_id = 3;
UPDATE user_extend SET user_id_encrypted = 'mlxBfT5FluDXdVE4i+3t3A==', user_id_query_helper = '4', ext_field1_encrypted = '66B3uhWemixDdAs5UqpFcw==', ext_field1_query_helper = '1996914096', ext_field1_like_query = '@5A4D5', ext_field2_encrypted = 'yNbNUdWBciyxVfAiCin9FQ==', ext_field2_like_query = 'E5D4E5', ext_field3_encrypted = 'oaCMEp5DhKTJoiescC/8XQ==', ext_field3_query_helper = '2082891015', ext_field3_like_query = 'H5I4H5' WHERE user_id = 4;
UPDATE user_extend SET user_id_encrypted = 'OEjmIYlS3I+Nb5pHGVTyVQ==', user_id_query_helper = '5', ext_field1_encrypted = 'cTzDmJYTFBfoCrcb8eP6Yw==', ext_field1_query_helper = '1997838579', ext_field1_like_query = '@4A5D8', ext_field2_encrypted = 'MWfTQz2zwcpqe4GpJzxtgw==', ext_field2_like_query = 'E4D5E8', ext_field3_encrypted = '0AkLpyQoJ8L6D5Uu8R6Phg==', ext_field3_query_helper = '2083815498', ext_field3_like_query = 'H4I5H8' WHERE user_id = 5;
UPDATE user_extend SET user_id_encrypted = '423j+ar3RrOPOm4PEbqxpQ==', user_id_query_helper = '6', ext_field1_encrypted = 'PuQcTvATWig8qEO5UfN7eg==', ext_field1_query_helper = '1998763062', ext_field1_like_query = '@5A8D9', ext_field2_encrypted = 'wtcv1Z+xls6jUH9RnOn1yw==', ext_field2_like_query = 'E5D8E9', ext_field3_encrypted = '0Qxiw5U3+vR7iAxbMvrNpg==', ext_field3_query_helper = '2084739981', ext_field3_like_query = 'H5I8H9' WHERE user_id = 6;
UPDATE user_extend SET user_id_encrypted = '9g+ObpASGKU8TPzbJQfZQA==', user_id_query_helper = '7', ext_field1_encrypted = 'zFM+C1LWnVTFeAPWKMxAmQ==', ext_field1_query_helper = '1999687545', ext_field1_like_query = '@8A9D8', ext_field2_encrypted = 'FFh3QoGl22GhVVsOf8Nrfg==', ext_field2_like_query = 'E8D9E8', ext_field3_encrypted = 'lmvpt2xUOpjSrhLJ8pWgag==', ext_field3_query_helper = '2085664464', ext_field3_like_query = 'H8I9H8' WHERE user_id = 7;
UPDATE user_extend SET user_id_encrypted = 'Wi9bWXTtpMTMGyEmGUH6Yw==', user_id_query_helper = '8', ext_field1_encrypted = 'G/XXqZP+dJ0GLDhjxn1Uqg==', ext_field1_query_helper = '2000612018', ext_field1_like_query = '@9A8D1', ext_field2_encrypted = 'L33lVgfzB9f7NouJeqNpdQ==', ext_field2_like_query = 'E9D8E1', ext_field3_encrypted = 'PA7Oqng4hlb8hQ00RT2pnA==', ext_field3_query_helper = '2086588937', ext_field3_like_query = 'H9I8H1' WHERE user_id = 8;
UPDATE user_extend SET user_id_encrypted = '/Se4cJDWx3g8LYJN9BwicA==', user_id_query_helper = '9', ext_field1_encrypted = 'dvlaz3jDiHTbg5nJA7tz7A==', ext_field1_query_helper = '2001526891', ext_field1_like_query = '@8A1D0', ext_field2_encrypted = 'Ryy+0ttbjUR5M6ARqEDavA==', ext_field2_like_query = 'E8D1E0', ext_field3_encrypted = '9/bknk5eaFDBU9cwMsgavA==', ext_field3_query_helper = '2087503810', ext_field3_like_query = 'H8I1H0' WHERE user_id = 9;
UPDATE user_extend SET user_id_encrypted = '9yY1ZyTxTa2McsR0XkKirw==', user_id_query_helper = '10', ext_field1_encrypted = 'dF3iwm2+5QHPMmZ77/7hNg==', ext_field1_query_helper = '1993216164', ext_field1_like_query = '@1A0D1', ext_field2_encrypted = 'i60CuS0lKpyN3wzjgHAgCQ==', ext_field2_like_query = 'E1D0E1', ext_field3_encrypted = 'YKlNLPbd6OAqT8/eYZULFQ==', ext_field3_query_helper = '2079193083', ext_field3_like_query = 'H1I0H1' WHERE user_id = 10;
