package com.taky.and.wendy.sign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taky.and.wendy.user.model.User;
import com.taky.and.wendy.user.service.UserService;

@Controller
public class SignController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/signin", method = RequestMethod.GET)
	public String showSigninView() throws Exception {
		return "sign/signin";
	}
	
	@RequestMapping(path = "/signin/certification", method = RequestMethod.GET)
	public String showSigninCertification(User user) throws Exception {
		// TODO 주의 : 브라우저로 직접 치면 당연히 디코드됨, 링크 눌러도 디코드가 될지모름..
		boolean isValid = userService.modifyUserCondition(user);
		
		if (isValid) {
			return "sign/certification";
		} else {
			return "sign/invalid";
		}
	}
	
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String showSignupView() throws Exception {
		return "sign/signup";
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String signup(User user) throws Exception {
		userService.saveUser(user);
		
		return "가입하신 e-mail로 인증 메일을 전송했습니다. 전송 시간이 걸리거나 스팸메일함에 있을 수 있습니다.";
	}
}
