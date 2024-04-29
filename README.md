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

-- Add liquibase support to provide migration
download and install from https://www.liquibase.com/download


1. To POM.xml file add:
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>
2. Create folder: db.changelog under src/main/resources
3. inside folder: src/main/resources/db.changelog create file: app_changelog.xml - this will be the main file for database migrations

Generating an Initial Changelog with Liquibase 
	a. Ensure Liquibase and JDBC Driver are Installed: You need to have Liquibase and the appropriate JDBC driver for your database installed on your machine.
	b. Run the Generate ChangeLog Command: Use the Liquibase CLI to generate the changelog.

Add Liquibase Maven Plugin to Your Project

Step 1: First, ensure that the Liquibase Maven plugin is included in your pom.xml:
<plugin>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-maven-plugin</artifactId>
    <version>4.27.0</version>  <!-- Use the latest version available -->
    <configuration>
        <propertyFile>src/main/resources/liquibase.properties</propertyFile>
    </configuration>
</plugin>

Step 2: Configure the liquibase.properties File

Create a liquibase.properties file in your src/main/resources directory with the necessary configurations:
changeLogFile=src/main/resources/db/changelog/db.initial-changelog.xml
url=jdbc:mysql://localhost:3306/yourdatabase
username=yourdatabaseuser
password=yourdatabasepassword
driver=com.mysql.cj.jdbc.Driver

Step 3: Generate the Initial Changelog

Now, use Maven to run (in bash) the Liquibase generateChangeLog goal, which will analyze your existing database and create a changelog file that describes the current schema:

mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile=src/main/resources/db/changelog/db.initial-changelog.xml

 
Optional: Automating Changelog Execution

If you want to automate the application of future changes when your application starts, 
you can configure the Liquibase Maven plugin to run the update goal as part of your build or deploy process:

<executions>
    <execution>
        <phase>process-resources</phase>
        <goals>
            <goal>update</goal>
        </goals>
    </execution>
</executions>

