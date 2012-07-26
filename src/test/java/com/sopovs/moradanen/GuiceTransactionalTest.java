package com.sopovs.moradanen;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceTransactionalTest extends AbstractTransactionalTest {

	@Override
	protected TransactionalClass getTransactionalClass() {
		Injector injector = Guice.createInjector(new GuiceModule());
		return injector.getInstance(TransactionalClass.class);
	}
}
