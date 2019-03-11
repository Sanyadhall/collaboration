package com.backend.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.backend.model.ApplyJob;
import com.backend.model.Blog;
import com.backend.model.BlogComment;
import com.backend.model.Friend;
import com.backend.model.Job;
import com.backend.model.ProfilePicture;
import com.backend.model.User;

@Configuration
@ComponentScan("com.backend")
@EnableTransactionManagement
public class DBConfig {

	
	
		
	//DataSource object
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		
		System.out.println("Hello 1");
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("sanya");
		dataSource.setPassword("sanya");
		System.out.println("Hello 2");
		return dataSource;
		
	}

	//Create SessionFactory Bean
	@Bean(name="sessionFactory")	public SessionFactory getSessionFactory(){
		
		System.out.println("Hello 3");
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto","update");
		hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
		hibernateProperties.put("hibernate.show_sql",true);
		
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProperties);
		sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(User.class);
		sessionFactoryBuilder.addAnnotatedClass(Job.class);
		sessionFactoryBuilder.addAnnotatedClass(ProfilePicture.class);
		sessionFactoryBuilder.addAnnotatedClass(ApplyJob.class);
		sessionFactoryBuilder.addAnnotatedClass(Friend.class);		
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		System.out.println("====SessionFactory Object======");
		System.out.println("Hello 4");
		return sessionFactory;
		
	}
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
		System.out.println("---Creating Transaction Manager---");
		return new HibernateTransactionManager(sessionFactory);
	}

}


