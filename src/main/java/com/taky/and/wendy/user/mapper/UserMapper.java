package com.taky.and.wendy.user.mapper;

import org.springframework.stereotype.Repository;

import com.taky.and.wendy.user.model.User;

@Repository
public interface UserMapper {
	public User selectUser(User user) throws Exception;
}
