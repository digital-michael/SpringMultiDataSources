# LESSONS LEARNED

- for Log: if the properties are duplicated (as in #Data uncommented), the startup for log stops
- for Log: if the dataSource() is defined, it doesn't access the properties it appears.
- for all: regardless of @Profile, the databases are created using (the first?) database configuration and tables are created event if the entities are not on profile


# Categories of Mistakes & Corrections

## Misconfigured DataSource Properties
        Mistake: Used spring.datasource.audit.url instead of spring.datasource.audit.jdbc-url, which is required by HikariCP.
        Correction: Replaced url with jdbc-url to properly configure HikariCP.

## Conflicting & Redundant Properties
        Mistake: Defined both spring.datasource.audit.hikari.* and spring.datasource.audit.*, causing conflicts.
        Correction: Removed unnecessary hikari.* properties, relying on standard spring.datasource.audit.jdbc-url.

## Incorrect Hibernate Dialect Configuration
        Mistake: Used spring.jpa.audit.properties.hibernate.dialect without spring.jpa.audit.database-platform, which Hibernate expects.
        Correction: Added spring.jpa.audit.database-platform=org.hibernate.dialect.PostgreSQLDialect.

##  Bean Configuration Issues
        Mistake: AuditConfig.java may not have been correctly binding the properties to the DataSource.
        Correction: Ensured @ConfigurationProperties(prefix = "spring.datasource.audit") was applied to the DataSource bean in AuditConfig.java.

## Final Outcome
- Properly structured application.properties to prevent misconfigurations and ensure Hibernate & HikariCP work correctly.
