package com.taky.and.wendy.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.taky.and.wendy.user.exception.UserSignupException;
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
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
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

	@Override
	public void saveUser(User user) throws Exception {
		this.validUserParameter(user.getEmail(), "### 회원가입시 email은 반드시 존재해야합니다.");
		this.validUserParameter(user.getPassword(), "### 회원가입시 비밀번호는 반드시 존재해야합니다.");
		this.validUserParameter(user.getName(), "### 회원가입시 이름은 반드시 존재해야합니다.");
		
		userMapper.insertUser(user);
	}
	
	private void validUserParameter(String userInfo, String errorMessage) {
		if (StringUtils.isEmpty(userInfo)) {
			logger.error(errorMessage);
			throw new UserSignupException(errorMessage);
		}
	}
}
