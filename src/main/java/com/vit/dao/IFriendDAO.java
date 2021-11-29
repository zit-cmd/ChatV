package com.vit.dao;

import java.util.List;

import com.vit.model.FriendModel;

public interface IFriendDAO extends GenericDAO<FriendModel> {

	List<FriendModel> findFriends(Long id);
	FriendModel findOne(Long user, Long friend);
	
}
