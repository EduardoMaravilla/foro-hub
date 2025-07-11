-- GENERAL
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Bienvenida a General', 'Este es un espacio para todo tipo de charlas.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción al Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Bienvenida a General'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Introducción al foro', '¿Cómo funciona la comunidad en General?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción al Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Introducción al foro'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Dónde publicar dudas?', 'Guía para escoger la categoría correcta.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción al Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Dónde publicar dudas?'
);

-- PROGRAMMING
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Mi primer código en Java', 'Comparto mi primera experiencia programando.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Programación en Java' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Mi primer código en Java'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Por dónde empezar a programar?', 'Busco recomendaciones de lenguajes y recursos.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Programación en Java' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Por dónde empezar a programar?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Errores que cometí al aprender', 'Un resumen de mis tropiezos aprendiendo.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Programación en Java' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Errores que cometí al aprender'
);

-- DATABASES
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Problemas con JOINs', 'No entiendo cómo funcionan los INNER JOIN.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'SQL Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Problemas con JOINs'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Cuándo usar índices?', '¿Cómo decidir si conviene indexar una columna?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'SQL Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Cuándo usar índices?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Errores comunes en SQL', 'Listado de errores frecuentes y cómo evitarlos.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'SQL Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Errores comunes en SQL'
);

-- DEVOPS
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Qué es CI/CD?', 'No entiendo muy bien cómo funciona la integración continua.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Docker para Desarrolladores' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Qué es CI/CD?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Docker vs Podman', '¿Cuál prefieren y por qué?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Docker para Desarrolladores' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Docker vs Podman'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Errores al desplegar con Jenkins', 'Me sale error 403 en producción.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Docker para Desarrolladores' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Errores al desplegar con Jenkins'
);

-- SECURITY
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Cómo proteger APIs?', 'Quiero consejos de seguridad para una API pública.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Buenas Prácticas en Autenticación' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Cómo proteger APIs?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Errores comunes de seguridad', '¿Cuáles son las fallas más típicas en apps web?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Buenas Prácticas en Autenticación' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Errores comunes de seguridad'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Recomendaciones OWASP', '¿Cuál es la prioridad del top 10?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Buenas Prácticas en Autenticación' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Recomendaciones OWASP'
);

-- FRONTEND
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Problemas con React', 'No entiendo los hooks, ¿algún recurso?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'React Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Problemas con React'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'CSS Grid vs Flexbox', '¿Cuándo usar uno sobre otro?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'React Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'CSS Grid vs Flexbox'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Tailwind me confunde', '¿Algún tip para usarlo bien?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'React Básico' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Tailwind me confunde'
);

-- BACKEND
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Spring Boot o Quarkus?', 'Opiniones sobre cuál usar en proyectos reales.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Spring Boot Essentials' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Spring Boot o Quarkus?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Errores 500 en producción', '¿Cómo debuguearlos?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Spring Boot Essentials' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Errores 500 en producción'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Manejo de excepciones', '¿Centralizar o usar try-catch?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Spring Boot Essentials' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Manejo de excepciones'
);

-- MOBILE
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Flutter o React Native?', 'Ventajas de cada uno para un novato.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Desarrollo Android con Kotlin' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Flutter o React Native?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'App lenta en Android', '¿Qué herramientas para analizar el rendimiento?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Desarrollo Android con Kotlin' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'App lenta en Android'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Problemas con permisos', 'Android no me deja acceder a la cámara.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Desarrollo Android con Kotlin' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Problemas con permisos'
);

-- CLOUD
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Qué es Kubernetes?', 'No entiendo bien cómo se usa.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción a AWS' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Qué es Kubernetes?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'AWS vs GCP', '¿Cuál es mejor para empezar?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción a AWS' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'AWS vs GCP'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Problemas con despliegue en la nube', 'No me carga el balanceador.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Introducción a AWS' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Problemas con despliegue en la nube'
);

-- TOOLS
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Qué IDE prefieren?', 'Visual Studio Code vs IntelliJ', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Visual Studio Code Tips' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Qué IDE prefieren?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Postman: tips y trucos', '¿Cómo testean sus APIs?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Visual Studio Code Tips' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Postman: tips y trucos'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Extensiones útiles', '¿Qué plugins no pueden faltar en su IDE?', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Visual Studio Code Tips' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Extensiones útiles'
);

-- ANNOUNCEMENTS
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Nuevo diseño en el foro', '¡Esperamos que te guste!', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Novedades del Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Nuevo diseño en el foro'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Cambios en moderación', 'Explicamos las nuevas reglas del foro.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Novedades del Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Cambios en moderación'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Qué esperas del foro?', 'Queremos escucharte.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Novedades del Foro' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Qué esperas del foro?'
);

-- OFF_TOPIC
INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT '¿Qué música escuchan mientras programan?', 'Yo soy más de lo-fi.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user1'),
       c.id_course
FROM courses c
WHERE c.name = 'Charlas de Café' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = '¿Qué música escuchan mientras programan?'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Café o té para codear', 'Discutamos.', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user2'),
       c.id_course
FROM courses c
WHERE c.name = 'Charlas de Café' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Café o té para codear'
);

INSERT INTO topics (title, message, created_at, status_topic, user_id, course_id)
SELECT 'Mi escritorio de trabajo', 'Compartan sus setups 🔥', NOW(), 'OPEN',
       (SELECT id_user FROM users WHERE username = 'user3'),
       c.id_course
FROM courses c
WHERE c.name = 'Comparte tu Setup' AND NOT EXISTS (
    SELECT 1 FROM topics WHERE title = 'Mi escritorio de trabajo'
);
