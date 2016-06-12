package pl.bolka.aleksander.schedule.planner.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
//import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 

@Configuration
@PropertySource(value = { "classpath:database/jdbc.properties" })
@EnableTransactionManagement
public class PersistenceConfig {

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"pl.bolka.aleksander.schedule.planner.model.entity"};

 

     @Bean(destroyMethod = "close")
     @Primary
     public DataSource dataSource() {
         BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName("org.postgresql.Driver");
         dataSource.setUrl("jdbc:postgresql://localhost:5432/schedule_planner");
         dataSource.setUsername("postgres");
         dataSource.setPassword("asdqwe1234");
         return dataSource;
     }

     @Bean
     public JpaTransactionManager jpaTransactionManager() {
         JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
         return transactionManager;
     }

    private HibernateJpaVendorAdapter vendorAdaptor() {
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         vendorAdapter.setShowSql(true);
         return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
         entityManagerFactoryBean.setDataSource(dataSource());
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);             
         entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
         
         return entityManagerFactoryBean;
     }

     private Properties jpaHibernateProperties() {

         Properties properties = new Properties();

//         properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH, env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
//         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
//         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
         properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "false");

         properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
         properties.put(AvailableSettings.USE_CLASS_ENHANCER, "false");   
         properties.put(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "update");
         return properties;       
     }

}