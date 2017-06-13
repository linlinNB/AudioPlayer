package com.actionimpl;

import java.io.InputStream;


import com.javabean.User;

public interface UserImpl {

	int register(User user);
	int login(String username,String password);
	void remove (int uid);
	int update(User user);
	User find(int uid);
	InputStream returnImage(int id);
}
