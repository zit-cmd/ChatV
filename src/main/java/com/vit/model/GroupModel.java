package com.vit.model;

public class GroupModel extends AbstractModel {

	private String nameGroup;
	private Long adminId;
	private Long messengerId;

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getMessengerId() {
		return messengerId;
	}

	public void setMessengerId(Long messengerId) {
		this.messengerId = messengerId;
	}

}
