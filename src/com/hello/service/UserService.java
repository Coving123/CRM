package com.hello.service;

import com.hello.pojo.User;

public interface UserService {

	User checkCode(String user_name);

	void save(User user);

	User login(User user);

}
