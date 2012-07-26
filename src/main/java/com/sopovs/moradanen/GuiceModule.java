package com.sopovs.moradanen;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;

public class GuiceModule implements Module {

	@Override
	public void configure(Binder binder) {
		DataSource dataSource = Utils.createDataSource();
		binder.bind(DataSource.class).toInstance(dataSource);

		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionAttributeSource(new AnnotationTransactionAttributeSource());
		transactionInterceptor.setTransactionManager(new DataSourceTransactionManager(dataSource));

		binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), transactionInterceptor);

	}

}
