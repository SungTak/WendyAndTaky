package com.taky.and.wendy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taky.and.wendy.user.mapper.UserMapper;
import com.taky.and.wendy.user.model.User;
import com.taky.and.wendy.user.model.UserChecker;

/**
 * 
 * @author SeongTak.Yoon
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserChecker existUser(User user) throws Exception {
		User userResult = userMapper.selectUser(user);
		UserChecker userChecker = new UserChecker();
		
		if (userResult == null) {
			userChecker.setExist(false);
		} else {
			userChecker.setExist(true);
		}
		return userChecker;
	}
}
