CREATE TABLE courses (
    id_course BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_course)
);
