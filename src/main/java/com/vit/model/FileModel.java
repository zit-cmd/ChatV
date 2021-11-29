package com.vit.model;

public class FileModel extends AbstractModel {

	private String fileName;
	private String path;
	private Long messengerId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getMessengerId() {
		return messengerId;
	}

	public void setMessengerId(Long messengerId) {
		this.messengerId = messengerId;
	}

}
