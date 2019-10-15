package com.osiris.food.model;

public class LessonDetail {


	/**
	 * status : success
	 * code : 200
	 * data : {"id":2,"name":"2018～2019年度中级食品安全培训","summary":"简介","text":"<p><\/p><p>月卡:30:128<\/p>","model_code":"public_course"}
	 */

	private String status;
	private int code;
	private DataBean data;

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

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 2
		 * name : 2018～2019年度中级食品安全培训
		 * summary : 简介
		 * text : <p></p><p>月卡:30:128</p>
		 * model_code : public_course
		 */

		private int id;
		private String name;
		private String summary;
		private String text;
		private String model_code;
		private int score;

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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getModel_code() {
			return model_code;
		}

		public void setModel_code(String model_code) {
			this.model_code = model_code;
		}
	}
}
