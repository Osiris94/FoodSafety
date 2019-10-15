package com.osiris.food.model;

public class StudyCourse {

	private String courseName;
	private String startTime;
	private String endTime;
	private String courseTime;
	private String courseLook;
	private String category;
	private String thumb;
	private int lessonId;
	private int id;
	private int visited;

	public StudyCourse() {

	}


	public StudyCourse(String courseName, String startTime, String endTime, String courseTime, String courseLook) {
		this.courseName = courseName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseTime = courseTime;
		this.courseLook = courseLook;
	}

	public int getVisited() {
		return visited;
	}

	public void setVisited(int visited) {
		this.visited = visited;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseLook() {
		return courseLook;
	}

	public void setCourseLook(String courseLook) {
		this.courseLook = courseLook;
	}





}
