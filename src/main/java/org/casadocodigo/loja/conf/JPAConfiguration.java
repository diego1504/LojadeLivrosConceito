package org.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //avisar ao spring para tomar conta das transacoes do banco de dados
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean EntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		
			JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
			factoryBean.setJpaVendorAdapter(vendorAdapter);
			factoryBean.setDataSource(dataSource);
		
	
	        Properties props = aditionalProperties();
	        factoryBean.setJpaProperties(props);

	        factoryBean.setPackagesToScan("org.casadocodigo.loja.model"); //informar as entidades (nao passar os daos)

	        return factoryBean;
	
	}

	private Properties aditionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
        //	props.setProperty("hibernate.hbm2ddl.auto", "validate");
		props.setProperty("hibernate.format_sql", "true");
		return props;
	}

	@Bean
	@Profile("dev")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/casadocodigo?useTimezone=true&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	@Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
	
		return transactionManager;
		
	}
	
}
