package pl.bolka.aleksander.schedule.planner.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.bolka.aleksander.schedule.planner.model.repository.CustomRepositoryFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

//import org.springframework.core.env.Environment;


@Configuration
@PropertySource(value = {"classpath:database/jdbc.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "pl.bolka.aleksander.schedule.planner.model.repository"},
        repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class PersistenceConfig {

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String[] ENTITY_MANAGER_PACKAGES_TO_SCAN = {"pl.bolka.aleksander.schedule.planner.model.entity"};


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

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_MANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

        return entityManagerFactoryBean;
    }


    private Properties jpaHibernateProperties() {

        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "false");
        properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
        properties.put(AvailableSettings.USE_CLASS_ENHANCER, "false");
        properties.put(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "update");
        properties.put(org.hibernate.cfg.AvailableSettings.FORMAT_SQL, "true");
//        properties.put(AvailableSettings.SCHEMA_GEN_SCRIPTS_ACTION,"create");
//        properties.put(AvailableSettings.SCHEMA_GEN_SCRIPTS_CREATE_TARGET,"D:/WorkSpace/schedule_planner/createSchema");

        return properties;
    }

}