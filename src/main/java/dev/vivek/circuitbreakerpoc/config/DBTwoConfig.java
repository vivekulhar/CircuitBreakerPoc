package dev.vivek.circuitbreakerpoc.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2entityManagerFactory",
        transactionManagerRef = "db2transactionManager",
        basePackages = {"dev.vivek.circuitbreakerpoc.db2.repo"})
public class DBTwoConfig {
    @Primary
    @Bean(name= "db2dataSource")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "db2entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("db2dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("dev.vivek.circuitbreakerpoc.db2.entity").persistenceUnit("db2")
                .build();
    }
    @Primary
    @Bean(name = "db2transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("db2entityManagerFactory") EntityManagerFactory barentityManagerFactory) {
        return new JpaTransactionManager(barentityManagerFactory);
    }
}
