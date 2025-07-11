CREATE TABLE modules (
    id_module BIGINT NOT NULL AUTO_INCREMENT,
    module_name VARCHAR(255) NOT NULL UNIQUE,
    base_path VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id_module)
);