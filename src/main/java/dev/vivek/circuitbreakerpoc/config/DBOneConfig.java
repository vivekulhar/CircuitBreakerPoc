package dev.vivek.circuitbreakerpoc.config;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        basePackages = {"dev.vivek.circuitbreakerpoc.db1.repo"})
public class DBOneConfig {

        @Bean
        public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
                return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
        }
        @Primary
        @Bean(name= "dataSource")
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }
        @Primary
        @Bean(name = "entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
            return builder.dataSource(dataSource).packages("dev.vivek.circuitbreakerpoc.db1.entity").persistenceUnit("db1")
                    .build();
        }
        @Primary
        @Bean(name = "transactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

}
