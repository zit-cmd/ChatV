package com.vit.model;

import java.util.List;

public class FriendModel extends AbstractModel {

	private Long id;
	private Long userId;
	private Long friendId;
	private String friendName;
	private String friendAvatar;
	private Boolean isFriend;
	private List<MessageModel> message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendAvatar() {
		return friendAvatar;
	}

	public void setFriendAvatar(String friendAvatar) {
		this.friendAvatar = friendAvatar;
	}

	public Boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Boolean isFriend) {
		this.isFriend = isFriend;
	}

	public List<MessageModel> getMessage() {
		return message;
	}

	public void setMessage(List<MessageModel> message) {
		this.message = message;
	}

}
