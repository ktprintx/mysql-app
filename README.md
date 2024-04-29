# mysql-app

DB scmema Creation: By default SpringBoot+JPA App create database objects on start if database objects are missed.

In a Spring Boot application using JPA (typically with Hibernate as the implementation), 
the framework automatically manages the creation and updating of the database schema based on the entity definitions in your application. 
This behavior is controlled by the spring.jpa.hibernate.ddl-auto property in your application.properties or application.yml file.

For security reason the best solution to disable this feature.

In file application.properties set next value:
spring.jpa.hibernate.ddl-auto=none

Setting ddl-auto to none means that Hibernate will not interact with the schema, leaving it unchanged during application startup. 
This is useful when you want full control over your database schema or when using a separate process to manage database migrations: Liquibase.