package com.sample.ejb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import th.go.dlt.utils.string.MyUtils;

public class UtilsTest {

	@Test
	public void callPadZeroLeftShouldBeReturn0000000123() {
		assertEquals("0000000123",MyUtils.getPadZeroLeft("123",10));
	}
	
	@Test
	public void callIsBlankOrNullShouldBeGetReturnTrue() {
		assertTrue(MyUtils.isBlankOrNull(""));
	}

	
}
