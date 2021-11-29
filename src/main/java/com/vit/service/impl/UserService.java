package com.vit.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.vit.dao.IUserDAO;
import com.vit.model.UserModel;
import com.vit.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDao;

	@Override
	public UserModel saveAccount(UserModel user) {
		List<UserModel> users = userDao.findUserAndPass(user);
		Long index = null;
		if (users.isEmpty()) {
			user.setThumbnail("default.jpg");
			index = userDao.save(user);
		}
		return (index == null) ? null : userDao.findOne(index);
	}

	@Override
	public UserModel findAccount(UserModel user) {
		List<UserModel> users = userDao.findUserAndPass(user);
		return users.isEmpty() ? null : users.get(0);
	}

}
