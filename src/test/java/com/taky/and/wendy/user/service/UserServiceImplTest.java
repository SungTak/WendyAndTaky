package com.taky.and.wendy.user.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImplTest {
	@Test
	public void testBcrypt() throws Exception {
		String password = "123가나abc%";
		
		String encrypt = BCrypt.hashpw(password, BCrypt.gensalt());
		
		System.out.println(encrypt);
		
		assertTrue(BCrypt.checkpw("123가나abc%", encrypt));
	}
}
