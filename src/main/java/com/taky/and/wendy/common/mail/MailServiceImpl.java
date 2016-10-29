package com.taky.and.wendy.common.mail;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.taky.and.wendy.user.model.User;

/**
 * 
 * @author seongtak.yoon
 *
 */
@Service
public class MailServiceImpl implements MailService {
	@Value("${wat.mail.certification}")
	private String host;
	
	/**
	 * https://www.google.com/settings/security/lesssecureapps
	 * - Please log in via your web browser라고 뜨면 보안이 낮은 앱은 원천적으로 차단 중
	 * - 개인 계정에서 차단을 풀어야함
	 */
	@Async
	@Override
	public void sendForCertificate(User user, String title, String text) throws Exception {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(title);
		simpleMailMessage.setTo(user.getEmail());
		
		String queryString = "?certification=" + user.getCertification() + "&email=" + UriUtils.encode(user.getEmail(), "utf-8");
		text += "<br><a href='" + host + queryString + "'>인증하러 가기</a>";
		
		simpleMailMessage.setText(text);

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.auth", true);

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		sender.setUsername("takymapmo@gmail.com");
		sender.setPassword("");
		sender.setJavaMailProperties(mailProperties);
		sender.send(simpleMailMessage);
	}
	
	@Override
	public boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
}
