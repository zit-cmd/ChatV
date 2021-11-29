package com.vit.dao.impl;

import java.util.List;

import com.vit.dao.IUserDAO;
import com.vit.mapper.UserMapper;
import com.vit.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public List<UserModel> findUserAndPassAndEmail(UserModel user) {
//		String sql = "SELECT * FROM users WHERE username='"+user.getUsername()+"' AND password='" + user. + "'";
		StringBuilder sql = new StringBuilder("SELECT * FROM users ");
		sql.append("WHERE username='"+user.getUsername() + "' OR email='"+user.getEmail() + "'");
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public Long save(UserModel user) {
		StringBuilder sql = new StringBuilder("INSERT INTO users ");
		sql.append("(username, password, fullname, email, thumbnail, code)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
		return insert(sql.toString(), user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(), 
				user.getThumbnail());
	}

	@Override
	public UserModel findOne(Long id) {
		String sql = "SELECT * FROM users WHERE id = " + id;
		List<UserModel> users = query(sql, new UserMapper());
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<UserModel> findUserAndPass(UserModel user) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users ");
		sql.append("WHERE username='"+user.getUsername() + "' AND password='"+user.getPassword() + "'");
		return query(sql.toString(), new UserMapper());
	}

}
