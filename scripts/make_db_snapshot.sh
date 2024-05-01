cd ..
mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile="src\main\resources\db\changelog\db.initial-changelog.xml"
