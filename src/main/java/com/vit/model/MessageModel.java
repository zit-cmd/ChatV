package com.vit.model;

public class MessageModel extends AbstractModel {

	private String content;
	private Long senderId;
	private Long receiverId;
	private int typeReceiver;
	private boolean seen;
	private boolean remove;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTypeReceiver() {
		return typeReceiver;
	}

	public void setTypeReceiver(int typeReceiver) {
		this.typeReceiver = typeReceiver;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public boolean getSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

}
