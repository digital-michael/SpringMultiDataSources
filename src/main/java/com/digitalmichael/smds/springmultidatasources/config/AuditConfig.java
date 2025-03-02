package com.digitalmichael.smds.springmultidatasources.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Provides data source definition for audit as defined in resources/dev/audit-db.properties.
 *
 * STATUS: datasource() fails, everything else tested and seems ok
 */
@Profile( {"all","audit"})
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.digitalmichael.smds.springmultidatasources.audit",  // << Associates repositories in this package
        entityManagerFactoryRef = "auditEntityManagerFactory", transactionManagerRef = "auditTransactionManager")
public class AuditConfig extends AbstractDataSourceConfig {

    //SET THIS:
    static final private String NAME = "audit";

    // USING THESE:
    static final private String[] PACKAGES = {"com.digitalmichael.smds.springmultidatasources." + NAME + ".dto"};
    static final private String PERSISTENCE_UNIT_NAME = NAME + "Db";
    static final private String DATA_SOURCE_BEAN_NAME = NAME + "DataSource";
    static final private String ENTITY_MANAGER_FACTORY_BEAN_NAME = NAME + "EntityManagerFactory";
    static final private String ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME = ENTITY_MANAGER_FACTORY_BEAN_NAME + "Builder";
    static final private String TRANSACTION_MANAGER_BEAN_NAME = NAME + "TransactionManager";
    static final private String DATASOURCE_PREFIX = "spring.datasource." + NAME;
    static final private String JPA_PREFIX = "spring.jpa." + NAME;
    static final private String HIBERNATE_PREFIX = "spring.jpa.hibernate." + NAME;
    static final private String HIBERNATE_BEAN = "hibernate" + NAME;
    static final private String JPA_BEAN = "jpa" + NAME;

    public AuditConfig() {
        super();
        System.out.println("reading from: " + DATASOURCE_PREFIX);
    }

    //STATUS: tested and appears to be working fine.
    @Bean( name = JPA_BEAN )
    @ConfigurationProperties(prefix = JPA_PREFIX)
    public JpaProperties jpaProperties() {
        JpaProperties result = super.jpaProperties();
        return result;
    }

    //STATUS: tested and appears to be working fine.
    @Bean(name = HIBERNATE_BEAN)
    @ConfigurationProperties(prefix = HIBERNATE_PREFIX)
    public HibernateProperties hibernateProperties() {
        HibernateProperties result = super.hibernateProperties();
        return result;
    }

    // STATUS: fails. I have tried moving the function to this class but exact same result.
    @Bean(name = DATA_SOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX)
    public DataSource dataSource() {
        return super.dataSource();
    }

    //STATUS: tested and appears to be working fine.
    @Bean(name = ENTITY_MANAGER_FACTORY_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME) EntityManagerFactoryBuilder builder, @Qualifier(DATA_SOURCE_BEAN_NAME) DataSource dataSource) {
        return builder.dataSource(dataSource).packages(PACKAGES) // Entities
                .persistenceUnit(PERSISTENCE_UNIT_NAME).properties(jpaProperties().getProperties()).build();
    }

    //STATUS: untested but no reason to doubt it works at this point.
    @Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY_BEAN_NAME) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    //STATUS: tested and appears to be working fine.
    @Bean(name = ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME)
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            @Qualifier(JPA_BEAN) JpaProperties jpaProperties,
            @Qualifier(HIBERNATE_BEAN) HibernateProperties hibernateProperties) {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), null);
    }
}
