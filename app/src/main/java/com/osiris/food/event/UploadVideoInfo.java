package com.osiris.food.event;

public class UploadVideoInfo {


	private int id;
	private int startTime;
	private int endTime;
	private int score;
	private int lessonId;


	public UploadVideoInfo(int id, int startTime, int endTime, int score, int lessonId) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.score = score;
		this.lessonId = lessonId;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
}
