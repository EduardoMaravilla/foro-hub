INSERT INTO modules(module_name, base_path)
SELECT 'USERS', '/users'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'USERS'
);

INSERT INTO modules(module_name, base_path)
SELECT 'ROLES', '/roles'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'ROLES'
);

INSERT INTO modules(module_name, base_path)
SELECT 'COURSES', '/courses'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'COURSES'
);

INSERT INTO modules(module_name, base_path)
SELECT 'TOPICS', '/topics'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'TOPICS'
);

INSERT INTO modules(module_name, base_path)
SELECT 'ANSWERS', '/answers'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'ANSWERS'
);

INSERT INTO modules(module_name, base_path)
SELECT 'MODULES', '/modules'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'MODULES'
);

INSERT INTO modules(module_name, base_path)
SELECT 'OPERATIONS', '/operations'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'OPERATIONS'
);

INSERT INTO modules(module_name, base_path)
SELECT 'AUTHENTICATE', '/authenticate'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'AUTHENTICATE'
);

INSERT INTO modules(module_name, base_path)
SELECT 'DOCUMENTATION', '/documentation'
WHERE NOT EXISTS (
    SELECT 1 FROM modules WHERE module_name = 'DOCUMENTATION'
);
