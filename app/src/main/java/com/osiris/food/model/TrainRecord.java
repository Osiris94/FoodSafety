package com.osiris.food.model;

import java.util.List;

public class TrainRecord {


	/**
	 * status : success
	 * code : 200
	 * data : [{"lesson_id":3,"created_at":"2019-06-23 13:09:28","time":"00:00:12","task_time":"00:00:12","visited":"00:00:00","face_teaching":"00:00:00","name":"2018～2019年度高级食品安全培训","school_year":"2019"},{"lesson_id":2,"created_at":"2019-06-23 13:09:28","time":"05:25:00","task_time":"05:25:00","visited":"00:00:00","face_teaching":"00:00:00","name":"2018～2019年度中级食品安全培训","school_year":"2019"}]
	 */

	private String status;
	private int code;
	private List<DataBean> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * lesson_id : 3
		 * created_at : 2019-06-23 13:09:28
		 * time : 00:00:12
		 * task_time : 00:00:12
		 * visited : 00:00:00
		 * face_teaching : 00:00:00
		 * name : 2018～2019年度高级食品安全培训
		 * school_year : 2019
		 */

		private int lesson_id;
		private String created_at;
		private String time;
		private String task_time;
		private String visited;
		private String face_teaching;
		private String name;
		private String school_year;

		public int getLesson_id() {
			return lesson_id;
		}

		public void setLesson_id(int lesson_id) {
			this.lesson_id = lesson_id;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getTask_time() {
			return task_time;
		}

		public void setTask_time(String task_time) {
			this.task_time = task_time;
		}

		public String getVisited() {
			return visited;
		}

		public void setVisited(String visited) {
			this.visited = visited;
		}

		public String getFace_teaching() {
			return face_teaching;
		}

		public void setFace_teaching(String face_teaching) {
			this.face_teaching = face_teaching;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSchool_year() {
			return school_year;
		}

		public void setSchool_year(String school_year) {
			this.school_year = school_year;
		}
	}
}
