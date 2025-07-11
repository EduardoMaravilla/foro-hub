CREATE TABLE granted_permission (
    role_id BIGINT NOT NULL,
    operation_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, operation_id),
    CONSTRAINT fk_permission_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id_role)
        ON DELETE CASCADE,
    CONSTRAINT fk_permission_operation
        FOREIGN KEY (operation_id)
        REFERENCES operations(id_operation)
        ON DELETE CASCADE
);
