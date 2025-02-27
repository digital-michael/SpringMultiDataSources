package com.digitalmichael.smds.springmultidatasources.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Provides an abstract base class so that the various data source config classes
 * have some commonality.
 *
 * Note: in most or all cases, methods in this class must be overriding and
 * have @Bean and related annotations applied in the derived classes to function properly.
 *
 * STATUS:
 *   - jpaProperties in derived classes - tested and appears working fine
 *   - hibernateProperties in derived classes - tested and appears working fine
 *   - datasource - is not reacting to the configuration in *-db.properties and falls over into the
 *                  Sqlite special case code which I can't have.
 */
abstract class AbstractDataSourceConfig {

    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }

    /*
       ISSUE METHOD: This method appears to be the only issue to solve for.
       WANT: simple "DataSourceBuilder.create().build();"
       HAVE: fails to process *-db.properties configuration in this method and aborts
       MUST NOT HAVE: special case code as shown below with partially works but
            breaks entity-to-table-on-start-up
     */
    public DataSource dataSource() {
        DataSource result = null;
        try {
            // Supported JDBC/JPA data source creation
            result = DataSourceBuilder.create().build();
        } catch (Exception e) {
            System.err.println( this.getClass().getCanonicalName() + ": " + e.getMessage() + "; Defaulting to Sqlite datasource");
            final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass(org.sqlite.JDBC.class);
            dataSource.setUrl("jdbc:sqlite:data/audit-db.sqlite");
            result = dataSource;
        }

        if (result instanceof SimpleDriverDataSource) {
            // to verify: Assumed Sqlite JDBC/JPA data source creation
            String url = ((SimpleDriverDataSource) result).getUrl();
            if (!url.startsWith("jdbc:sqlite:"))
                throw new IllegalArgumentException("Data source configuration could not be determined or is improper.");
        }

//        try {
//            System.out.println(result.getConnection().getMetaData().toString());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return result;
    }
}
