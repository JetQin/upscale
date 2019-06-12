package io.github.jetqin.upscale.config;

import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "io.github.jetqin.upscale.service", bootstrapMode = BootstrapMode.LAZY)
public class EclipseLinkConfig extends JpaBaseConfiguration {


    protected EclipseLinkConfig(DataSource dataSource, JpaProperties properties,
                          ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
                          ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }


    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        final Map<String, Object> ret = new HashMap<>();
//        ret.put(PersistenceUnitProperties.BATCH_WRITING_SIZE, BatchWriting.JDBC);
        ret.put(PersistenceUnitProperties.WEAVING,"false");
        ret.put(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, "TENANT_JET_001");
//        return ret;

        // Turn off dynamic weaving to disable LTW lookup in static weaving mode
        return ret;
    }

    private final Map<String, ?> initJpaProperties() {
        final Map<String, Object> ret = new HashMap<>();
        // Add any JpaProperty you are interested in and is supported by your Database and JPA implementation
//        ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
//        ret.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
        return ret;
    }

}
