package com.vit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vit.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel user = new UserModel();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("fullname"));
			user.setEmail(rs.getString("email"));
			user.setThumbnail(rs.getString("thumbnail"));
			return user;
		} catch (SQLException e) {
			return null;
		}
	}

}
