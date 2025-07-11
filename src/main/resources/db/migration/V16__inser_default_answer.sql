
INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT '¡Gracias por la bienvenida! Emocionado por empezar a participar.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Bienvenida a General'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = '¡Gracias por la bienvenida! Emocionado por empezar a participar.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Espero aprender mucho con ustedes. ¡Saludos a todos!',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Bienvenida a General'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Espero aprender mucho con ustedes. ¡Saludos a todos!'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT '¿Hay algún post de reglas del foro que deba leer primero?',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Bienvenida a General'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = '¿Hay algún post de reglas del foro que deba leer primero?'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Este foro tiene muy buena pinta. ¡Felicidades al equipo!',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Bienvenida a General'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Este foro tiene muy buena pinta. ¡Felicidades al equipo!'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT '¿Cómo ganamos reputación o puntos aquí?',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Bienvenida a General'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = '¿Cómo ganamos reputación o puntos aquí?'
  );


INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Te recomiendo comenzar con Python, es sencillo y muy popular.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Por dónde empezar a programar?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Te recomiendo comenzar con Python, es sencillo y muy popular.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Java también es una excelente opción para aprender programación orientada a objetos.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Por dónde empezar a programar?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Java también es una excelente opción para aprender programación orientada a objetos.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Hay muchos cursos gratuitos en YouTube y plataformas como FreeCodeCamp.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Por dónde empezar a programar?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Hay muchos cursos gratuitos en YouTube y plataformas como FreeCodeCamp.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Comienza con lógica de programación antes de meterte con frameworks.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Por dónde empezar a programar?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Comienza con lógica de programación antes de meterte con frameworks.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Yo inicié con Java y me funcionó muy bien para entender estructuras.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Por dónde empezar a programar?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Yo inicié con Java y me funcionó muy bien para entender estructuras.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Un INNER JOIN une filas cuando hay coincidencia en ambas tablas según la condición.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con JOINs'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Un INNER JOIN une filas cuando hay coincidencia en ambas tablas según la condición.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Prueba con una consulta sencilla entre dos tablas y observa el resultado.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con JOINs'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Prueba con una consulta sencilla entre dos tablas y observa el resultado.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'INNER JOIN solo devuelve filas cuando hay coincidencia en ambas tablas, a diferencia de LEFT JOIN.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con JOINs'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'INNER JOIN solo devuelve filas cuando hay coincidencia en ambas tablas, a diferencia de LEFT JOIN.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Muestra tu consulta para ayudarte mejor, JOINs pueden ser confusos al principio.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con JOINs'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Muestra tu consulta para ayudarte mejor, JOINs pueden ser confusos al principio.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Revisa los nombres de las columnas, a veces el error es una mala referencia.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con JOINs'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Revisa los nombres de las columnas, a veces el error es una mala referencia.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'CI/CD significa Integración Continua y Entrega Continua, automatiza la construcción y despliegue.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es CI/CD?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'CI/CD significa Integración Continua y Entrega Continua, automatiza la construcción y despliegue.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Con CI/CD, cada cambio de código pasa por pruebas automáticas y se despliega si todo está correcto.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es CI/CD?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Con CI/CD, cada cambio de código pasa por pruebas automáticas y se despliega si todo está correcto.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Herramientas populares para CI/CD incluyen Jenkins, GitLab CI y GitHub Actions.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es CI/CD?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Herramientas populares para CI/CD incluyen Jenkins, GitLab CI y GitHub Actions.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'CI/CD ayuda a reducir errores manuales y acelera la entrega de software.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es CI/CD?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'CI/CD ayuda a reducir errores manuales y acelera la entrega de software.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Puedes empezar creando pipelines básicos para automatizar pruebas y despliegues simples.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es CI/CD?'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Puedes empezar creando pipelines básicos para automatizar pruebas y despliegues simples.'
  );
INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Una falla común es la falta de validación de entrada, que permite inyecciones SQL.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Errores comunes de seguridad'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Una falla común es la falta de validación de entrada, que permite inyecciones SQL.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'No cifrar datos sensibles como contraseñas o tokens es otro error grave.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Errores comunes de seguridad'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'No cifrar datos sensibles como contraseñas o tokens es otro error grave.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'El manejo incorrecto de sesiones puede llevar a secuestro de sesiones.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Errores comunes de seguridad'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'El manejo incorrecto de sesiones puede llevar a secuestro de sesiones.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Falta de actualización y parches puede exponer vulnerabilidades conocidas.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Errores comunes de seguridad'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'Falta de actualización y parches puede exponer vulnerabilidades conocidas.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'No implementar controles de acceso adecuados puede exponer datos a usuarios no autorizados.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Errores comunes de seguridad'
  AND NOT EXISTS (
      SELECT 1 FROM answers
      WHERE message = 'No implementar controles de acceso adecuados puede exponer datos a usuarios no autorizados.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Te recomiendo la documentación oficial de React para entender bien los hooks.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con React'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Te recomiendo la documentación oficial de React para entender bien los hooks.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Los hooks pueden parecer difíciles al principio, pero hay muchos tutoriales en YouTube que ayudan mucho.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con React'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Los hooks pueden parecer difíciles al principio, pero hay muchos tutoriales en YouTube que ayudan mucho.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Un buen libro que me ayudó fue Learning React de O.Reilly, muy completo y claro.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con React'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Un buen libro que me ayudó fue Learning React de O.Reilly, muy completo y claro.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Prueba practicar creando pequeños proyectos usando hooks, la práctica es clave.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con React'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Prueba practicar creando pequeños proyectos usando hooks, la práctica es clave.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Si quieres, puedo compartirte algunos ejemplos de código que uso para entender hooks mejor.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con React'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Si quieres, puedo compartirte algunos ejemplos de código que uso para entender hooks mejor.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Spring Boot es muy maduro y tiene una gran comunidad, ideal para proyectos estables.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Spring Boot o Quarkus?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Spring Boot es muy maduro y tiene una gran comunidad, ideal para proyectos estables.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Quarkus es excelente para microservicios y optimización en tiempo de ejecución, muy ligero.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Spring Boot o Quarkus?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Quarkus es excelente para microservicios y optimización en tiempo de ejecución, muy ligero.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Depende mucho del equipo y la experiencia previa, ambos son muy buenos frameworks.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Spring Boot o Quarkus?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Depende mucho del equipo y la experiencia previa, ambos son muy buenos frameworks.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Personalmente prefiero Spring Boot por su ecosistema y plugins.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Spring Boot o Quarkus?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Personalmente prefiero Spring Boot por su ecosistema y plugins.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Para proyectos en la nube, Quarkus puede dar ventajas interesantes por su rapidez.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Spring Boot o Quarkus?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Para proyectos en la nube, Quarkus puede dar ventajas interesantes por su rapidez.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT '¿Ya verificaste que el permiso de cámara esté declarado en el AndroidManifest.xml?',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con permisos'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = '¿Ya verificaste que el permiso de cámara esté declarado en el AndroidManifest.xml?'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Recuerda pedir el permiso en tiempo de ejecución para Android 6.0+ con ActivityCompat.requestPermissions.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con permisos'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Recuerda pedir el permiso en tiempo de ejecución para Android 6.0+ con ActivityCompat.requestPermissions.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Si el usuario deniega el permiso, debes manejar la lógica para solicitarlo nuevamente o explicar la necesidad.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con permisos'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Si el usuario deniega el permiso, debes manejar la lógica para solicitarlo nuevamente o explicar la necesidad.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'También revisa que no haya restricciones de privacidad en el dispositivo o configuraciones de seguridad.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con permisos'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'También revisa que no haya restricciones de privacidad en el dispositivo o configuraciones de seguridad.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Puedes usar el método shouldShowRequestPermissionRationale para explicar al usuario por qué necesitas el permiso.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Problemas con permisos'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Puedes usar el método shouldShowRequestPermissionRationale para explicar al usuario por qué necesitas el permiso.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Kubernetes es un sistema de orquestación para automatizar el despliegue, escalado y gestión de aplicaciones en contenedores.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es Kubernetes?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Kubernetes es un sistema de orquestación para automatizar el despliegue, escalado y gestión de aplicaciones en contenedores.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Permite administrar clústeres de contenedores, facilitando la alta disponibilidad y escalabilidad.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es Kubernetes?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Permite administrar clústeres de contenedores, facilitando la alta disponibilidad y escalabilidad.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Se utiliza ampliamente para desplegar aplicaciones microservicios en ambientes de producción.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es Kubernetes?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Se utiliza ampliamente para desplegar aplicaciones microservicios en ambientes de producción.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Kubernetes maneja el ciclo de vida de los contenedores y balancea la carga entre ellos automáticamente.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es Kubernetes?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Kubernetes maneja el ciclo de vida de los contenedores y balancea la carga entre ellos automáticamente.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Para comenzar, es útil conocer conceptos como pods, servicios, y deployments dentro de Kubernetes.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué es Kubernetes?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Para comenzar, es útil conocer conceptos como pods, servicios, y deployments dentro de Kubernetes.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Yo prefiero Visual Studio Code porque es ligero y tiene muchas extensiones.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué IDE prefieren?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Yo prefiero Visual Studio Code porque es ligero y tiene muchas extensiones.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'IntelliJ es genial para proyectos grandes en Java, su refactorización es muy potente.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué IDE prefieren?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'IntelliJ es genial para proyectos grandes en Java, su refactorización es muy potente.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Depende del lenguaje, para Python uso VS Code, para Java prefiero IntelliJ.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué IDE prefieren?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Depende del lenguaje, para Python uso VS Code, para Java prefiero IntelliJ.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Me gusta que VS Code es muy personalizable y tiene integración con Git.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué IDE prefieren?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Me gusta que VS Code es muy personalizable y tiene integración con Git.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'IntelliJ ofrece muchas herramientas integradas que aceleran el desarrollo.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué IDE prefieren?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'IntelliJ ofrece muchas herramientas integradas que aceleran el desarrollo.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Espero que sea un lugar activo y amigable donde podamos aprender juntos.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué esperas del foro?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Espero que sea un lugar activo y amigable donde podamos aprender juntos.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Me gustaría encontrar respuestas claras y ayuda rápida en mis dudas.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué esperas del foro?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Me gustaría encontrar respuestas claras y ayuda rápida en mis dudas.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Espero que los usuarios participen activamente y compartan sus conocimientos.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué esperas del foro?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Espero que los usuarios participen activamente y compartan sus conocimientos.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Un foro ordenado y respetuoso donde todos puedan expresarse.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué esperas del foro?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Un foro ordenado y respetuoso donde todos puedan expresarse.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Que se convierta en un recurso esencial para la comunidad de desarrolladores.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = '¿Qué esperas del foro?'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Que se convierta en un recurso esencial para la comunidad de desarrolladores.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Me encanta tener dos monitores y una buena silla ergonómica para largas sesiones.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Mi escritorio de trabajo'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Me encanta tener dos monitores y una buena silla ergonómica para largas sesiones.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Uso un teclado mecánico y luces RGB, hace el ambiente mucho más agradable.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Mi escritorio de trabajo'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Uso un teclado mecánico y luces RGB, hace el ambiente mucho más agradable.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Tengo una planta y una taza de café siempre a mano, eso me ayuda a concentrarme.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user3'),
       FALSE
FROM topics t
WHERE t.title = 'Mi escritorio de trabajo'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Tengo una planta y una taza de café siempre a mano, eso me ayuda a concentrarme.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Trabajo con una laptop ultraligera y una pantalla externa para mejorar la productividad.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user1'),
       FALSE
FROM topics t
WHERE t.title = 'Mi escritorio de trabajo'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Trabajo con una laptop ultraligera y una pantalla externa para mejorar la productividad.'
  );

INSERT INTO answers (message, topic_id, created_at, user_id, is_solution)
SELECT 'Prefiero un setup minimalista, sin muchas distracciones y con buena iluminación natural.',
       t.id_topic, NOW(),
       (SELECT id_user FROM users WHERE username = 'user2'),
       FALSE
FROM topics t
WHERE t.title = 'Mi escritorio de trabajo'
  AND NOT EXISTS (
      SELECT 1 FROM answers WHERE message = 'Prefiero un setup minimalista, sin muchas distracciones y con buena iluminación natural.'
  );
