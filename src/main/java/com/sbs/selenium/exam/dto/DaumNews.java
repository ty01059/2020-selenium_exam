package com.sbs.selenium.exam.dto;

public class DaumNews {
	private int id;
	private String regDate;
	private String title;
	private String newsPaper;

	public DaumNews(int id, String regDate, String title, String newsPaper) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.newsPaper = newsPaper;
	}

	@Override
	public String toString() {
		return "DaumNews [\n번호=" + id + "\n시간=" + regDate + "\n제목=" + title + "\n신문사=" + newsPaper + "\n]";
	}

}
