package com.vit.dao.impl;

import java.util.List;

import com.vit.dao.IFriendDAO;
import com.vit.mapper.FriendMapper;
import com.vit.model.FriendModel;

public class FriendDAO extends AbstractDAO<FriendModel> implements IFriendDAO {

	@Override
	public List<FriendModel> findFriends(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM friends ");
		sql.append("WHERE user_id = ? AND isfriend = 1;");
		return query(sql.toString(), new FriendMapper(), id);
	}

	@Override
	public FriendModel findOne(Long user, Long friend) {
		String sql = "SELECT * FROM friends WHERE user_id = ? AND friend_id = ?";
		List<FriendModel> friends = query(sql, new FriendMapper(), user, friend);
		return friends.isEmpty() ? null : friends.get(0);
	}

}