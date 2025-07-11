INSERT INTO granted_permission (role_id, operation_id)
SELECT
    (SELECT id_role FROM roles WHERE name = 'ADMIN') AS role_id,
    op.id_operation
FROM operations op
WHERE op.permit_all = FALSE
  AND NOT EXISTS (
    SELECT 1
    FROM granted_permission gp
    WHERE gp.role_id = (SELECT id_role FROM roles WHERE name = 'ADMIN')
      AND gp.operation_id = op.id_operation
);

INSERT INTO granted_permission (role_id, operation_id)
SELECT
    (SELECT id_role FROM roles WHERE name = 'USER') AS role_id,
    op.id_operation
FROM operations op
WHERE op.name IN ('CHANGE_PASSWORD', 'DISABLE_SELF')
  AND NOT EXISTS (
    SELECT 1
    FROM granted_permission gp
    WHERE gp.role_id = (SELECT id_role FROM roles WHERE name = 'USER')
      AND gp.operation_id = op.id_operation
);


INSERT INTO granted_permission (role_id, operation_id)
SELECT
    (SELECT id_role FROM roles WHERE name = 'USER') AS role_id,
    op.id_operation
FROM operations op
JOIN modules m ON op.module_id = m.id_module
WHERE op.permit_all = FALSE
  AND m.module_name IN ('COURSES', 'TOPICS', 'ANSWERS')
  AND NOT EXISTS (
    SELECT 1
    FROM granted_permission gp
    WHERE gp.role_id = (SELECT id_role FROM roles WHERE name = 'USER')
      AND gp.operation_id = op.id_operation
);
