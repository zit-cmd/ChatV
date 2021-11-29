package com.vit.service;

import java.util.List;

import com.vit.model.FriendModel;

public interface IFriendService {

	List<FriendModel> findListFriends(Long id);
	FriendModel findOne(Long user, Long friend);
	
}
