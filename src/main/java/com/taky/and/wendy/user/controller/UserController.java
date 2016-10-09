package com.taky.and.wendy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taky.and.wendy.user.model.User;
import com.taky.and.wendy.user.model.UserChecker;
import com.taky.and.wendy.user.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	@ResponseBody
	public UserChecker existUser(User user) throws Exception {
		return userService.existUser(user);
	}
}
