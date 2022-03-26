# Layer Login/Register users -> Form + API JWT
Spring Security + Spring MVC + Spring Data JPA + thymeleaf + PostgreSQL

###Backend

**Setup**

Dependencias:

- Servidor: PostgreSQL
- Dependencias (ver pom.xml):

#### Database PostgreSQL

Acciones:

- Crear base de datos, default -> users
- Crear rol/usuario owner del base de datos.
- Ejecutar script sql de inicializacion. Contiene estructura de BD con datos de usuarios prueba.
  - <a href="https://github.com/robertogarcor/seguridadLoginCustomJWT/blob/main/src/main/resources/db/dump-users.sql">dump-users.sql</a>
- Configurar fichero propiedades <a href="https://github.com/robertogarcor/seguridadLoginCustomJWT/blob/main/src/main/resources/application.properties">application.properties</a> con los datos de la BD.

Nota:<br>

- El proyecto Spring contiene una clase comentada dentro del paquete utils.CreateEntitiesUsersRoles, 
que permite crear 2 Roles ADMIN/USER y 2 usuarios con roles respectivos.
En una alternativa inicial si es usada en primera instancia, descomentar la clase y una vez inicializado
el proyecto comentar posteriormente.



