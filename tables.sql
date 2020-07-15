create table category (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL,created_when DATE, updated_when datetime not null,
user_id VARCHAR(50), FOREIGN KEY (user_id) REFERENCES user(id));

create table planning (id bigint AUTO_INCREMENT PRIMARY KEY, value DECIMAL(10,2) NOT NULL, date DATE NOT NULL,
category_id bigint NOT NULL, updated_when datetime not null, user_id VARCHAR(50),
 FOREIGN KEY (category_id) REFERENCES category(id), FOREIGN KEY (user_id) REFERENCES user(id));

create table cash_flow (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, value DECIMAL(10,2),
category_id bigint NOT NULL, flow VARCHAR(10) NOT NULL, date DATE NOT NULL, user_id VARCHAR(50),
FOREIGN KEY (category_id) REFERENCES category(id),
FOREIGN KEY (user_id) REFERENCES user(id));

create table user (id VARCHAR(50) PRIMARY KEY, email VARCHAR(254), name VARCHAR(50));

DELIMITER ;;
CREATE FUNCTION FIRST_DAY(day DATE)
RETURNS DATE DETERMINISTIC
BEGIN
  RETURN ADDDATE(LAST_DAY(SUBDATE(day, INTERVAL 1 MONTH)), 1);
END;;
DELIMITER ;

