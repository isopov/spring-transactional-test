package com.sopovs.moradanen;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractTransactionalTest {

	private TransactionalClass test;

	protected abstract TransactionalClass getTransactionalClass();

	@Before
	public void prepare() {
		test = getTransactionalClass();
		test.prepareData();
	}

	@Test
	public void testInsertSimple() {
		test.insertSimple();
		Assert.assertEquals(Arrays.asList("test"), test.getData());
	}

	@Test
	public void testInsertWithException() {
		try {
			test.insertWithException();
			Assert.fail();
		} catch (RuntimeException e) {
			Assert.assertEquals("Dummy exception", e.getMessage());
		}
		Assert.assertEquals(Collections.emptyList(), test.getData());

	}
}
