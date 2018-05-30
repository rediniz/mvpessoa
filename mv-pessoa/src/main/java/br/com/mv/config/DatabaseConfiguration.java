package br.com.mv.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({ "br.com.mv.repository", "br.com.mv.model" })
@EnableTransactionManagement
@EnableJpaRepositories("br.com.mv.repository")
public class DatabaseConfiguration {

	@Bean(destroyMethod = "close")
	public DataSource dataSource(DataSourceProperties dataSourceProperties) throws Exception {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(dataSourceProperties.getDriverClassName());
		config.setJdbcUrl(dataSourceProperties.getUrl());
		config.setUsername(dataSourceProperties.getUsername());
		config.setPassword(dataSourceProperties.getPassword());

		return new HikariDataSource(config);
	}

	@Bean
	public JPAQueryFactory queryFactory(EntityManager entityManager) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		return queryFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		jpaVendorAdapter.setGenerateDdl(false);
		return jpaVendorAdapter;
	}

}
