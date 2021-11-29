package com.vit.dao.impl;

import java.util.List;

import com.vit.dao.IMessageDAO;
import com.vit.mapper.MessageMapper;
import com.vit.model.MessageModel;

public class MessageDAO extends AbstractDAO<MessageModel> implements IMessageDAO {

	@Override
	public MessageModel findOne(Long senderId, Long receiverId) {
		StringBuilder sql = new StringBuilder("SELECT messengers.*, receivers.seen, receivers.receiver ");
		sql.append("FROM receivers ");
		sql.append("RIGHT OUTER JOIN chat_servlet.messengers ON receivers.messengerid = messengers.id ");
		sql.append("WHERE messengers.id IN (SELECT MAX(messengers.id) ");
		sql.append("FROM chat_servlet.receivers ");
		sql.append("RIGHT OUTER JOIN chat_servlet.messengers ON receivers.messengerid = messengers.id ");
		sql.append("WHERE (messengers.userid = ? AND receivers.receiver = ?) ");
		sql.append("OR (messengers.userid = ? AND receivers.receiver = ?))");
		List<MessageModel> friends = query(sql.toString(), new MessageMapper(), senderId, receiverId, receiverId, senderId);
		return friends.isEmpty() ? null : friends.get(0);
	}

	@Override
	public List<MessageModel> findTwelveMessage(Long senderId, Long receiverId) {
		StringBuilder sql = new StringBuilder("SELECT messengers.id, messengers.content, messengers.userid, receivers.receiver, messengers.typereceiver, messengers.remove, receivers.seen, messengers.createddate ");
		sql.append("FROM receivers RIGHT OUTER JOIN messengers ON receivers.messengerid = messengers.id ");
		sql.append("WHERE (userid = ? AND receiver = ?) OR (userid = ? AND receiver = ?) ");
		sql.append("ORDER BY receivers.id DESC LIMIT 12;");
		return query(sql.toString(), new MessageMapper(), senderId, receiverId, receiverId, senderId);
	}

	@Override
	public Long saveTableMessage(MessageModel message) {
		StringBuilder sql = new StringBuilder("INSERT INTO messengers ");
		sql.append("(content, userid, typereceiver, remove) ");
		sql.append("VALUES (?,?,?,?)");
		return insert(sql.toString(), message.getContent(), message.getSenderId(), message.getTypeReceiver(), message.isRemove());
	}

	@Override
	public Long saveTableReceiver(Long id, MessageModel message) {
		StringBuilder sql = new StringBuilder("INSERT INTO receivers ");
		sql.append("(receiver, messengerid, seen) ");
		sql.append("VALUES (?,?,?)");
		return insert(sql.toString(), message.getReceiverId(), id, message.getSeen());
	}

}
