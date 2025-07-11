-- Inserta usuario root con rol ADMIN
INSERT INTO users (username, password, email, role_id)
SELECT 'root', '$2b$12$bgh53O36Mvc/m4SJFvrfgekjMJhrgbnSgFK9SYLa/HOqvvttt0ATO', 'root@email.com',
       (SELECT id_role FROM roles WHERE name = 'ADMIN')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'root'
);

-- Inserta user1 con rol USER
INSERT INTO users (username, password, email, role_id)
SELECT 'user1', '$2b$12$LKb1kOsLuNiB5yBuWTP0W.c793D3xePxHQIVI3i64vTWPzBVI7k7K', 'user1@email.com',
       (SELECT id_role FROM roles WHERE name = 'USER')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'user1'
);

-- Inserta user2 con rol USER
INSERT INTO users (username, password, email, role_id)
SELECT 'user2', '$2b$12$vzmTnzojxcoZlVL/UYF4eOIFLgfZtXG7JNdHIFzyenO8demDe/PYa', 'user2@email.com',
       (SELECT id_role FROM roles WHERE name = 'USER')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'user2'
);

-- Inserta user3 con rol USER
INSERT INTO users (username, password, email, role_id)
SELECT 'user3', '$2b$12$mxEUp6i/f/Xo4xBtZo6ZG.ceuvISlaJU1SExrT1y2moGxnYS9y.xG', 'user3@email.com',
       (SELECT id_role FROM roles WHERE name = 'USER')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'user3'
);
