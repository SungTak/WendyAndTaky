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
	
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String showSignupView() throws Exception {
		return "sign/signup";
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String signup(User user) throws Exception {
		userService.saveUser(user);
		
		return "가입하신 e-mail로 인증 메일을 전송하였습니다. 확인해주세요.";
	}
}
