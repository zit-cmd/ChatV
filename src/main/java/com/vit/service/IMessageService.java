package com.vit.service;

import java.util.List;

import com.vit.model.MessageModel;

public interface IMessageService {

	MessageModel findOneMessage(Long senderId, Long receiverId);
	List<MessageModel> findTwelveMessage(Long senderId, Long receiverId);
	
}
