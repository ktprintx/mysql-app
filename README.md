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


1. Add to POM.xml file add liquibase dependency:
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>
2. Add to POM.xml file add h2 dependency for unit test:
		<!-- H2 Database Engine -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version> <!-- Use the latest version available -->
			<scope>test</scope> <!-- This makes the dependency available only for the test compilation and execution phases -->
		</dependency>		
3. Add to POM.xml file add liquibase plugin to support online changes:
			<plugin>
                <groupId>org.liquibase</groupId>
                <artifactId>liquibase-maven-plugin</artifactId>
                <version>4.24.0</version>
                <configuration>
                    <propertyFile>src/main/resources/liquibase.properties</propertyFile>
                    <changeLogFile>src/main/resources/db/changelog/db.initial-changelog.xml</changeLogFile>
                </configuration>
            </plugin>

4. create folder: scripts and add file make_db_snapshot.sh   (from IJ right click on file and Run 'make_db_snapshot.sh' - will generate the screenshot of database or can run it from BASH)

cd ..
mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile="src\main\resources\db\changelog\db.initial-changelog.xml"

4a. if project taken from git and new emptry database schema create (manually) then run: mvn liquibase:update to recreate all database objects
File: \src\main\resources\db\changelog\db.initial-changelog.xml  must contains complete database snapshot.


5. Add to aplication.yml liquibase dependency:
    liquibase:
        change-log: classpath:/db/changelog/app-changelog.xml
        default-schema: AppDbSchema
6. Create folder: db.changelog under src/main/resources

Generating an Initial Changelog with Liquibase 
	a. Ensure Liquibase and JDBC Driver are Installed: You need to have Liquibase and the appropriate JDBC driver for your database installed on your machine.
	b. Run the Generate ChangeLog Command: Use the Liquibase CLI to generate the changelog.

6: Configure the liquibase.properties File

Create a liquibase.properties file in your src/main/resources directory with the necessary configurations:

changeLogFile=src/main/resources/db/changelog/app-changelog.xml
url=jdbc:mysql://localhost:3306/AppDbSchema
username=root
password=Printix123!
driver=com.mysql.cj.jdbc.Driver
outputChangeLogFile=src/main/resources/db/changelog/db.initial-changelog.xml

7: Generate the Initial Changelog

Now, use Maven to run (in bash) the Liquibase generateChangeLog goal, which will analyze your existing database and create a changelog file that describes the current schema:

mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile=src/main/resources/db/changelog/db.initial-changelog.xml
mvn liquibase:generateChangeLog -Dliquibase.url="jdbc:mysql://localhost:3306/sbhm" -Dliquibase.username=root -Dliquibase.password=Kofax123! -Dliquibase.outputChangeLogFile=src/main/resources/db/changelog/db.initial-changelog.xml


8. Change app-changelog.xml to support initial db schema

<!-- db.changelog-master.xml -->
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">
    
    <include file="db.initial-changelog.xml" relativeToChangelogFile="true"/>

    <!-- Future changesets go here -->
</databaseChangeLog>


Optional: Automating Changelog Execution

If you want to automate the application of future changes when your application starts, 
you can configure the Liquibase Maven plugin to run the update goal as part of your build or deploy process:


Test the Configuration

To ensure that everything is set up correctly, you can run a Maven lifecycle phase that comes after or includes the phase you specified
mvn process-resources
or simply:
mvn install

This will trigger the Liquibase update as part of the build process, applying any pending database changes defined in your changelog file.

By setting this up, you ensure that every time your project is built, 
Liquibase will automatically apply the latest database changes, keeping your development, testing,
 or production databases up to date with your application's schema.

