package com.vit.service;

import com.vit.model.UserModel;

public interface IUserService {

	UserModel saveAccount(UserModel user);
	UserModel findAccount(UserModel user);
	
}
