package com.taky.and.wendy.user.service;

import com.taky.and.wendy.user.model.User;
import com.taky.and.wendy.user.model.UserChecker;

public interface UserService {
	public User findUser(User user) throws Exception;

	public UserChecker existUser(User user) throws Exception;

	public void saveUser(User user) throws Exception;

	public boolean modifyUserCondition(User user) throws Exception;
}
