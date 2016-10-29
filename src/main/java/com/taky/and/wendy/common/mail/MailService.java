package com.taky.and.wendy.common.mail;

import com.taky.and.wendy.user.model.User;

public interface MailService {
	public void sendForCertificate(User user, String title, String text) throws Exception;
	
	public boolean isValidEmail(String email);
}
