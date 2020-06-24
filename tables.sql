create table category (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL,created_when DATE, updated_when datetime not null);

create table planning (id bigint AUTO_INCREMENT PRIMARY KEY, value DECIMAL(10,2) NOT NULL, date DATE NOT NULL,
category_id bigint NOT NULL, updated_when datetime not null, FOREIGN KEY (category_id) REFERENCES category(id));

create table cash_flow (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, value DECIMAL(10,2),
category_id bigint NOT NULL, flow VARCHAR(10) NOT NULL, date DATE NOT NULL, FOREIGN KEY (category_id) REFERENCES category(id));

DELIMITER ;;
CREATE FUNCTION FIRST_DAY(day DATE)
RETURNS DATE DETERMINISTIC
BEGIN
  RETURN ADDDATE(LAST_DAY(SUBDATE(day, INTERVAL 1 MONTH)), 1);
END;;
DELIMITER ;

