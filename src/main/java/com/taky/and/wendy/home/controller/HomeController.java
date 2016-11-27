package com.taky.and.wendy.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taky.and.wendy.sign.service.SignService;
import com.taky.and.wendy.user.model.User;

@Controller
public class HomeController {
	@Autowired
	private SignService signService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		User user = signService.getSigninUser();
		model.addAttribute("user", user);
		
		return "home";
	}
}
