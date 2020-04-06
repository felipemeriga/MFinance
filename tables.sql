create table category (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL,created_when DATE);

create table planning (id bigint AUTO_INCREMENT PRIMARY KEY, value DECIMAL(10,2) NOT NULL, date DATE NOT NULL,
category_id bigint NOT NULL, FOREIGN KEY (category_id) REFERENCES category(id));

create table flux (id bigint AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, value DECIMAL(10,2),
category_id bigint NOT NULL, flow VARCHAR(10) NOT NULL, date DATE NOT NULL, FOREIGN KEY (category_id) REFERENCES category(id));

