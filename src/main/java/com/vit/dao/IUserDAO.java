package com.vit.dao;

import java.util.List;

import com.vit.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {

	List<UserModel> findUserAndPassAndEmail(UserModel user);
	List<UserModel> findUserAndPass(UserModel user);
	UserModel findOne(Long id);
	Long save(UserModel user);
	
}
