package com.sopovs.moradanen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTransactionalTest extends AbstractTransactionalTest {

	@Override
	protected TransactionalClass getTransactionalClass() {
		ApplicationContext appConext = new AnnotationConfigApplicationContext("com.sopovs.moradanen");
		return appConext.getBean(TransactionalClass.class);
	}
}
