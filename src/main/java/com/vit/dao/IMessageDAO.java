package com.vit.dao;

import java.util.List;

import com.vit.model.MessageModel;

public interface IMessageDAO extends GenericDAO<MessageModel> {

	MessageModel findOne(Long senderId, Long receiverId);
	List<MessageModel> findTwelveMessage(Long senderId, Long receiverId);
	Long saveTableMessage(MessageModel message);
	Long saveTableReceiver(Long id, MessageModel message);
	
}
