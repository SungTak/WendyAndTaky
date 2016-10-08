package com.taky.and.wendy.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignController {
	@RequestMapping(path = "/signin", method = RequestMethod.GET)
	public String showSigninView() {
		return "sign/signin";
	}
	
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String showSignupView() {
		return "sign/signup";
	}
}
