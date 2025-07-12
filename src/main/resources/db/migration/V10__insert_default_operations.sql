-- Module Operations
INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_MODULES', 'GET', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_MODULES'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_MODULE_BY_ID', 'GET', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_MODULE_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_MODULE_OPERATIONS', 'GET', '/[0-9]*/operations', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_MODULE_OPERATIONS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_MODULE', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_MODULE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_MODULE', 'PUT', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_MODULE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_MODULE', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'MODULES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_MODULE'
);

-- Operation Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_OPERATIONS', 'GET', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'OPERATIONS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_OPERATIONS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_OPERATION_BY_ID', 'GET', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'OPERATIONS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_OPERATION_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_OPERATION', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'OPERATIONS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_OPERATION'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_OPERATION', 'PUT', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'OPERATIONS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_OPERATION'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_OPERATION', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'OPERATIONS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_OPERATION'
);

-- Role Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_ROLES', 'GET', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_ROLES'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ROLE_BY_ID', 'GET', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ROLE_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ROLE_OPERATIONS', 'GET', '/[0-9]*/operations', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ROLE_OPERATIONS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_ROLE', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_ROLE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_ROLE', 'PUT', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_ROLE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'ADD_ROLE_OPERATIONS', 'PUT', '/[0-9]*/operations', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'ADD_ROLE_OPERATIONS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_ROLE', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ROLES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_ROLE'
);

-- User Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'REGISTER_USER', 'POST', '', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'REGISTER_USER'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CHANGE_PASSWORD', 'PUT', '/change-password', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CHANGE_PASSWORD'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DISABLE_SELF', 'PUT', '/disable', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DISABLE_SELF'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_USERS', 'GET', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_USERS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_USER_BY_ID', 'GET', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_USER_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_USER_BY_USERNAME', 'GET', '/by-username/.+', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_USER_BY_USERNAME'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'ENABLE_USER', 'PUT', '/[0-9]*/enable', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'ENABLE_USER'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DISABLE_USER', 'PUT', '/[0-9]*/disable', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DISABLE_USER'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_USER', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_USER'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_USER_ROLE', 'PUT', '/[0-9]*/role', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'USERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_USER_ROLE'
);

-- Authentication Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'LOGIN', 'POST', '/login', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'AUTHENTICATE')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'LOGIN'
);

-- Swagger Documentations operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'SWAGGER_DOCUMENTATION', 'GET', '/.*', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'DOCUMENTATION')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'SWAGGER_DOCUMENTATION'
);

-- Course Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_COURSES', 'GET', '', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'COURSES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_COURSES'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_COURSE_BY_ID', 'GET', '/[0-9]*', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'COURSES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_COURSE_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_COURSE', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'COURSES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_COURSE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_COURSE', 'PUT', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'COURSES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_COURSE'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_COURSE', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'COURSES')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_COURSE'
);

-- Topics Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_TOPIC', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_TOPIC'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_ALL_TOPICS', 'GET', '', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_ALL_TOPICS'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_TOPIC_BY_ID', 'GET', '/[0-9]*', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_TOPIC_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'UPDATE_TOPIC_BY_ID', 'PUT', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'UPDATE_TOPIC_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'DELETE_TOPIC_BY_ID', 'DELETE', '/[0-9]*', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'DELETE_TOPIC_BY_ID'
);

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'GET_TOPIC_ANSWERS', 'GET', '/[0-9]*/answers', TRUE,
       (SELECT id_module FROM modules WHERE module_name = 'TOPICS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'GET_TOPIC_ANSWERS'
);

-- Answer Operations

INSERT INTO operations (name, http_method, path, permit_all, module_id)
SELECT 'CREATE_ANSWER', 'POST', '', FALSE,
       (SELECT id_module FROM modules WHERE module_name = 'ANSWERS')
WHERE NOT EXISTS (
    SELECT 1 FROM operations WHERE name = 'CREATE_ANSWER'
);

