package com.vit.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.vit.dao.GenericDAO;
import com.vit.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/chat_servlet";
			String user = "root";
			String password = "0120311212";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	private void setParameter(PreparedStatement statement, Object[] parameters) {
		try {
			int index = 1;
			for(Object parameter : parameters) {
				if (parameter instanceof Long) {
					statement.setLong(index++, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index++, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index++, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index++, (Timestamp) parameter);
				} else if (parameter == null) {
					statement.setNull(index++, Types.NULL);
				} else if (parameter instanceof Boolean) {
					statement.setBoolean(index++, (boolean) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
//			set parameters
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			for(Object parameter : parameters) {
				System.out.println(parameter);
			}
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
//			set parameters
			setParameter(statement, parameters);
			System.out.println(statement);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = getConnection();
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			set parameters
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Long count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		long count = 0;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
//			set parameters
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getLong(1);
			}
			return count;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}
	
}
