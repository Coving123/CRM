package com.hello.dao;

import com.hello.pojo.User;

public interface UserDao {

	public User checkCode(String user_name);

	public void save(User user);

	public User login(User user);

}
