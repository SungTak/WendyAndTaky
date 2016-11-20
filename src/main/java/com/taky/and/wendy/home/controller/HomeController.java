package com.taky.and.wendy.home.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import com.taky.and.wendy.user.model.User;

@Controller
public class HomeController {
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model) {
		// 로그인하면 User 객체로 가져올 수 있고, 안했으면 "어나미머스유저" String으로 옴..
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user;
		if (auth == null || auth.getPrincipal() == null || StringUtils.equals("anonymousUser", auth.getPrincipal())) {
			user = new User();
			user.setName("방문객");
		} else {
			user = (User)auth.getPrincipal();
		}
		
		model.addAttribute("user", user);
		
		return "home";
	}
}
