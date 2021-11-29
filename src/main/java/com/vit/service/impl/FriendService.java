package com.vit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.vit.dao.IFriendDAO;
import com.vit.model.FriendModel;
import com.vit.model.MessageModel;
import com.vit.service.IFriendService;
import com.vit.service.IMessageService;

public class FriendService implements IFriendService {
	
	@Inject
	private IFriendDAO friendDao;
	@Inject
	private IMessageService messageService;

	@Override
	public List<FriendModel> findListFriends(Long id) {
		List<FriendModel> friends = friendDao.findFriends(id);
		for (int index = 0 ; index < friends.size() ; index++) {
			List<MessageModel> m = new ArrayList<MessageModel>();
			m.add(messageService.findOneMessage(id, friends.get(index).getFriendId()));
			friends.get(index).setMessage(m);
		}
		return friends;
	}

	@Override
	public FriendModel findOne(Long user, Long friend) {
		FriendModel f = friendDao.findOne(user, friend);
		return f;
	}

}
