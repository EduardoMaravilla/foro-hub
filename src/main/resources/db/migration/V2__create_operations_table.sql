CREATE TABLE operations (
    id_operation BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    http_method VARCHAR(20) NOT NULL,
    path VARCHAR(255),
    permit_all BOOLEAN NOT NULL,
    module_id BIGINT NOT NULL,
    PRIMARY KEY (id_operation),
    CONSTRAINT fk_operations_module
        FOREIGN KEY (module_id)
        REFERENCES modules(id_module)
        ON DELETE CASCADE
);
