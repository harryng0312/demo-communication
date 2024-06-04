package org.harryng.demo.config;

import jakarta.persistence.ValidationMode;
import org.harryng.demo.impl.base.persistence.SimpleBasePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"org.harryng.demo"},
        repositoryBaseClass = SimpleBasePersistence.class,
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class PersistenceConfig {

    private static final Logger log = LoggerFactory.getLogger(PersistenceConfig.class);

    @Value("${spring.datasource.url}")
    private String dsUrl;
    @Value("${spring.datasource.dbcp2.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.dbcp2.max-total}")
    private int maxTotal;
    @Value("${spring.datasource.dbcp2.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.dbcp2.max-idle}")
    private int maxIdle;


    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManager(@Autowired DataSource dataSource) {
        log.info("db_url: {}", dsUrl);
        log.info("initialSize: {}, maxTotal: {}", initialSize, maxTotal);
        log.info("minIdle: {}, maxIdle: {}", minIdle, maxIdle);
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName("primary");
        em.setValidationMode(ValidationMode.NONE);
        em.setPackagesToScan("org.harryng.demo.impl.**.entity", "org.harryng.demo.impl.**.persistence");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaPropertyMap(hibernateProperties());
        return em;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        transactionManager.setNestedTransactionAllowed(true);
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        transactionManager.setRollbackOnCommitFailure(true);
        return transactionManager;
    }
}
