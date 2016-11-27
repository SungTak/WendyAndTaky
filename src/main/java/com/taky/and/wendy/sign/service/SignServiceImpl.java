package com.taky.and.wendy.sign.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.taky.and.wendy.user.model.User;

/**
 * 
 * @author SeongTak.Yoon
 *
 */
@Service
public class SignServiceImpl implements SignService {
	private static final String ANONYMOUS_USER = "anonymousUser";
	
	/**
	 * 로그인한 유저 정보를 반환한다.
	 */
	@Override
	public User getSigninUser() throws Exception {
		// 로그인하면 User 객체로 가져올 수 있고, 안했으면 "어나미머스유저" String으로 옴..
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user;
		if (auth == null || auth.getPrincipal() == null || StringUtils.equals(ANONYMOUS_USER, auth.getPrincipal())) {
			user = new User();
			user.setName(User.GUEST);
		} else {
			user = (User)auth.getPrincipal();
		}
		
		return user;
	}
}
