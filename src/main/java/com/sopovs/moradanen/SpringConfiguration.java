package com.sopovs.moradanen;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@ImportResource("transactions.xml")
public class SpringConfiguration {

	@Bean
	@Singleton
	public DataSource getDataSource() {
		return Utils.createDataSource();
	}

	@Bean(name = "transactionManager")
	@Inject
	public DataSourceTransactionManager createTransactionManager(DataSource ds) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(ds);
		return txManager;
	}

	@Bean
	@Inject
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager txm, TransactionAttributeSource tas) {
		return new TransactionInterceptor(txm, tas);
	}

}
