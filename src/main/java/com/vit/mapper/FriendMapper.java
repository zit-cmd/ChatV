package com.vit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vit.model.FriendModel;

public class FriendMapper implements RowMapper<FriendModel> {

	@Override
	public FriendModel mapRow(ResultSet rs) {
		try {
			FriendModel friend = new FriendModel();
			friend.setId(rs.getLong("id"));
			friend.setUserId(rs.getLong("user_id"));
			friend.setFriendId(rs.getLong("friend_id"));
			friend.setFriendName(rs.getString("friend_name"));
			friend.setFriendAvatar(rs.getString("friend_avt"));
			friend.setIsFriend(rs.getBoolean("isfriend"));
			return friend;
		} catch (SQLException e) {
			return null;
		}
	}

}
