package com.taky.and.wendy.common.mail;

import static org.junit.Assert.*;

import org.junit.Test;

public class MailServiceImplTest {
	@Test
	public void testValidEmail() throws Exception {
		String email = "gogogo@naver.com";
		MailService sut = new MailServiceImpl();
		boolean result = sut.isValidEmail(email);
		assertTrue(result);

		email = "gogogonaver.com";
		result = sut.isValidEmail(email);
		assertFalse(result);
	}
}
