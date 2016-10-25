package com.taky.and.wendy.user.service;

import java.util.Base64;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.taky.and.wendy.common.mail.MailService;
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
public class UserServiceImpl implements UserService, UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MailService mailService;
	
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
		
		String text = "회원가입을 해주셔서 감사합니다. 최종 인증을 받으세요!";
		mailService.sendForCertificate(user, "[Taky & Wendy] 가입을 환영합니다!", text);
	}
	
	@Override
	public User findUser(User user) throws Exception {
		return userMapper.selectUser(user);
	}
	
	public void modifyUser(User user) throws Exception {
		userMapper.updateUser(user);
	}
	
	/**
	 * 유저의 인증 정보가 동일한지 체크한 뒤
	 * 동일하면 상태를 normal로 변경한다.
	 */
	@Override
	public boolean modifyUserCondition(User user) throws Exception {
		User foundUser = this.findUser(user);
		
		if (foundUser == null) {
			return false;
		}
		
		if (checkCertificationAndCondition(user, foundUser) == false) {
			return false;
		}
		
		User modifyUser = new User();
		modifyUser.setCondition(User.NORMAL);
		modifyUser.setEmail(foundUser.getEmail());
		
		this.modifyUser(modifyUser);
		
		return true;
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
	
	/**
	 * 유저 인증 정보와 상태가 '등록'인지 확인한다.
	 * 
	 * @param user
	 * @param foundUser
	 * @return
	 */
	private boolean checkCertificationAndCondition(User user, User foundUser) {
		return StringUtils.equals(foundUser.getCertification(), user.getCertification()) && StringUtils.equals(User.RECORD, foundUser.getCondition());
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = new User();
			user.setEmail(email);
			
			User foundUser = this.findUser(user);
			logger.debug(foundUser.getPassword());
			return foundUser;
		} catch(Exception e) {
			throw new UsernameNotFoundException("### 유저 정보를 찾는 과정에서 에러가 발생했습니다.", e);
		}
	}
}
