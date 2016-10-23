package com.taky.and.wendy.user.service;

import java.util.Base64;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) throws Exception {
		this.validUserParameter(user.getEmail(), "### 회원가입시 email은 반드시 존재해야합니다.");
		this.validUserParameter(user.getPassword(), "### 회원가입시 비밀번호는 반드시 존재해야합니다.");
		this.validUserParameter(user.getName(), "### 회원가입시 이름은 반드시 존재해야합니다.");
		
		/*
		 * BCrypt 암호화 참고
		 * - http://d2.naver.com/helloworld/318732
		 * - http://jsonobject.tistory.com/239
		 */
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.setCertification(this.createCertification(user.getName()));
		
		userMapper.insertUser(user);
	}
	
	private void validUserParameter(String userInfo, String errorMessage) {
		if (StringUtils.isEmpty(userInfo)) {
			logger.error(errorMessage);
			throw new UserSignupException(errorMessage);
		}
	}
	
	/**
	 * 유저의 이메일에 인증 URL을 보내기 위한 인증데이터
	 * 
	 * @param name
	 * @return
	 */
	private String createCertification(String name) {
		String normal = new Date().getTime() + name;
		String encrypt = BCrypt.hashpw(normal, BCrypt.gensalt());
		
		return Base64.getEncoder().encodeToString(encrypt.getBytes());
	}
}
