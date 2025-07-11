-- === GENERAL ===
INSERT INTO courses (name, category)
SELECT 'Introducción al Foro', 'GENERAL'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Introducción al Foro');

INSERT INTO courses (name, category)
SELECT 'Reglas de Participación', 'GENERAL'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Reglas de Participación');

INSERT INTO courses (name, category)
SELECT 'Guía de Usuario', 'GENERAL'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Guía de Usuario');

-- === PROGRAMMING ===
INSERT INTO courses (name, category)
SELECT 'Programación en Java', 'PROGRAMMING'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Programación en Java');

INSERT INTO courses (name, category)
SELECT 'Lógica de Programación', 'PROGRAMMING'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Lógica de Programación');

INSERT INTO courses (name, category)
SELECT 'Estructuras de Datos', 'PROGRAMMING'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Estructuras de Datos');

-- === DATABASES ===
INSERT INTO courses (name, category)
SELECT 'SQL Básico', 'DATABASES'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'SQL Básico');

INSERT INTO courses (name, category)
SELECT 'Modelado Relacional', 'DATABASES'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Modelado Relacional');

INSERT INTO courses (name, category)
SELECT 'Optimización de Consultas', 'DATABASES'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Optimización de Consultas');

-- === DEVOPS ===
INSERT INTO courses (name, category)
SELECT 'Integración Continua', 'DEVOPS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Integración Continua');

INSERT INTO courses (name, category)
SELECT 'Docker para Desarrolladores', 'DEVOPS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Docker para Desarrolladores');

INSERT INTO courses (name, category)
SELECT 'CI/CD con GitHub Actions', 'DEVOPS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'CI/CD con GitHub Actions');

-- === SECURITY ===
INSERT INTO courses (name, category)
SELECT 'Introducción a la Seguridad', 'SECURITY'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Introducción a la Seguridad');

INSERT INTO courses (name, category)
SELECT 'Buenas Prácticas en Autenticación', 'SECURITY'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Buenas Prácticas en Autenticación');

INSERT INTO courses (name, category)
SELECT 'Vulnerabilidades OWASP', 'SECURITY'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Vulnerabilidades OWASP');

-- === FRONTEND ===
INSERT INTO courses (name, category)
SELECT 'HTML y CSS desde Cero', 'FRONTEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'HTML y CSS desde Cero');

INSERT INTO courses (name, category)
SELECT 'JavaScript Moderno', 'FRONTEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'JavaScript Moderno');

INSERT INTO courses (name, category)
SELECT 'React Básico', 'FRONTEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'React Básico');

-- === BACKEND ===
INSERT INTO courses (name, category)
SELECT 'Spring Boot Essentials', 'BACKEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Spring Boot Essentials');

INSERT INTO courses (name, category)
SELECT 'Node.js con Express', 'BACKEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Node.js con Express');

INSERT INTO courses (name, category)
SELECT 'API REST Seguras', 'BACKEND'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'API REST Seguras');

-- === MOBILE ===
INSERT INTO courses (name, category)
SELECT 'Desarrollo Android con Kotlin', 'MOBILE'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Desarrollo Android con Kotlin');

INSERT INTO courses (name, category)
SELECT 'Flutter desde Cero', 'MOBILE'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Flutter desde Cero');

INSERT INTO courses (name, category)
SELECT 'Publicar en Play Store', 'MOBILE'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Publicar en Play Store');

-- === CLOUD ===
INSERT INTO courses (name, category)
SELECT 'Fundamentos de Cloud Computing', 'CLOUD'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Fundamentos de Cloud Computing');

INSERT INTO courses (name, category)
SELECT 'Introducción a AWS', 'CLOUD'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Introducción a AWS');

INSERT INTO courses (name, category)
SELECT 'Deploy en GCP', 'CLOUD'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Deploy en GCP');

-- === TOOLS ===
INSERT INTO courses (name, category)
SELECT 'Uso Profesional de Git', 'TOOLS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Uso Profesional de Git');

INSERT INTO courses (name, category)
SELECT 'Visual Studio Code Tips', 'TOOLS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Visual Studio Code Tips');

INSERT INTO courses (name, category)
SELECT 'Automatización con Makefile', 'TOOLS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Automatización con Makefile');

-- === ANNOUNCEMENTS ===
INSERT INTO courses (name, category)
SELECT 'Novedades del Foro', 'ANNOUNCEMENTS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Novedades del Foro');

INSERT INTO courses (name, category)
SELECT 'Cambios de Política', 'ANNOUNCEMENTS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Cambios de Política');

INSERT INTO courses (name, category)
SELECT 'Eventos y Retos', 'ANNOUNCEMENTS'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Eventos y Retos');

-- === OFF_TOPIC ===
INSERT INTO courses (name, category)
SELECT 'Charlas de Café', 'OFF_TOPIC'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Charlas de Café');

INSERT INTO courses (name, category)
SELECT 'Recomendaciones Personales', 'OFF_TOPIC'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Recomendaciones Personales');

INSERT INTO courses (name, category)
SELECT 'Comparte tu Setup', 'OFF_TOPIC'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE name = 'Comparte tu Setup');
