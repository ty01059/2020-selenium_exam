package com.sbs.selenium.exam.dto;

public class NaverFlashNews {
	private String title;
	private String body;
	private String newspaper;
	private String photoUrl;

	public NaverFlashNews(String title, String body, String newspaper, String photoUrl) {
		this.title = title;
		this.body = body;
		this.newspaper = newspaper;
		this.photoUrl = photoUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(String newspaper) {
		this.newspaper = newspaper;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "NaverFlashNews [title = " + title + ", body = " + body + ", newspaper = " + newspaper + ", photoUrl = "
				+ photoUrl + "]";
	}
}