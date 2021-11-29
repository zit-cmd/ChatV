package com.vit.service;

import com.vit.model.UserModel;

public interface ICallAPI {

	UserModel login(UserModel user);
	UserModel register(UserModel user);
	
}
