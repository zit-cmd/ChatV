package com.vit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vit.model.MessageModel;

public class MessageMapper implements RowMapper<MessageModel> {

	@Override
	public MessageModel mapRow(ResultSet rs) {
		try {
			MessageModel mess = new MessageModel();
			mess.setId(rs.getLong("id"));
			mess.setContent(rs.getString("content"));
			mess.setSenderId(rs.getLong("userid"));
			mess.setReceiverId(rs.getLong("receiver"));
			mess.setTypeReceiver(rs.getInt("typereceiver"));
			mess.setRemove(rs.getBoolean("remove"));
			mess.setCreatedDate(rs.getTimestamp("createddate"));
			mess.setSeen(rs.getBoolean("seen"));
			return mess;
		} catch (SQLException e) {
			return null;
		}
	}
	
}
