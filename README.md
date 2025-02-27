# SpringMultiDataSources

Spring and multiple data sources, isolated and configured from individual property files with a common, abstract base
class

# Goal
- have a reliable way to derive a new data source and have it configured against a standard to make it easy to add new and isolated data sources

# Requirements
- two data sources, isolated in both code and configuration property files
- no special case code
- all components in support of each data source have unique bean names, related to the name of the data source { audit, augment } 
- use annotations with static String text for configuration
- ability configure Sqlite, MySQL, Postgres or other data connections from the property files only (no code changes)
- supports creating schema from entity classes on startup when missing (default behavior of spring.jpa.XXX.hibernate.ddl-auto=update)

# Issues
- dataSource() files to create a connection, interrupted necessary operations
- Focus Package: config
- Focus Classes: AuditConfig, AugmentConfig, AbstractDataSourceConfig
- Focus method: dataSource()

# Interesting Tech
- abstract dto class AbstractDao : tested in other code bases and appears to work reliably
- the XXXConfig classes are using annotations to replace/override the default spring.datasource and spring.jpa behaviors
-   * see annotations for the class
- most of the internal components are named beans and configured to be isolated using static strings at the top of each class
    * only need to update "name" to get a set of unique beans

