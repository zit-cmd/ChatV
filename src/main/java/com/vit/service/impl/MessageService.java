package com.vit.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.vit.dao.IMessageDAO;
import com.vit.model.MessageModel;
import com.vit.service.IMessageService;

public class MessageService implements IMessageService {

	@Inject
	private IMessageDAO messageDao;

	@Override
	public MessageModel findOneMessage(Long senderId, Long receiverId) {
		return messageDao.findOne(senderId, receiverId);
	}

	@Override
	public List<MessageModel> findTwelveMessage(Long senderId, Long receiverId) {
		return messageDao.findTwelveMessage(senderId, receiverId);
	}
	
}
