package com.mypocket.storeManagement.dataSources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysql-container"
                       ,transactionManagerRef = "mysql-transaction-manager"
                       ,basePackages = {"com.mypocket.database.repositories"})
public class DataSourceConfig {

    @Autowired
    Environment environment;

    @Bean(name = "mysql-datasource")
    @ConfigurationProperties("mysql.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysql-vendor-adapter")
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "mysql-container")
    public LocalContainerEntityManagerFactoryBean mySqlEntityFactory(@Qualifier("mysql-datasource") DataSource mySqlDataSource
                                                                     ,@Qualifier("mysql-vendor-adapter") JpaVendorAdapter vendorAdapter){

        LocalContainerEntityManagerFactoryBean localContainer = new LocalContainerEntityManagerFactoryBean();

        localContainer.setDataSource(mySqlDataSource);
        localContainer.setJpaVendorAdapter(vendorAdapter);
        localContainer.setPersistenceUnitName("mySqlFactory");
        localContainer.setJpaProperties(properties());
        localContainer.setPackagesToScan(environment.getProperty("mysql.scan-package"));
        localContainer.afterPropertiesSet();

        return localContainer;
    }

    @Bean(name = "mysql-transaction-manager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("mysql-container") LocalContainerEntityManagerFactoryBean factory) {

        return new JpaTransactionManager(factory.getObject());
    }

    private Properties properties(){
        return new Properties(){{
           put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
           put("hibernate.hbm2ddl.auto" ,environment.getProperty("hibernate.hbm2ddl.auto"));
        }};
    }


}
