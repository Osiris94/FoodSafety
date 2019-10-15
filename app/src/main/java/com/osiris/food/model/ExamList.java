package com.osiris.food.model;

import java.util.List;

public class ExamList {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":3,"name":"2018～2019年度食品安全管理员中级考试","is_simulate":0,"question_numbers":20,"history_score":0,"times":0},{"id":8,"name":"食品安全高级考试","is_simulate":1,"question_numbers":20,"history_score":0,"times":0}]
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
		 * id : 3
		 * name : 2018～2019年度食品安全管理员中级考试
		 * is_simulate : 0
		 * question_numbers : 20
		 * history_score : 0
		 * times : 0
		 */

		private int id;
		private String name;
		private int is_simulate;
		private int question_numbers;
		private int history_score;
		private int times;

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

		public int getIs_simulate() {
			return is_simulate;
		}

		public void setIs_simulate(int is_simulate) {
			this.is_simulate = is_simulate;
		}

		public int getQuestion_numbers() {
			return question_numbers;
		}

		public void setQuestion_numbers(int question_numbers) {
			this.question_numbers = question_numbers;
		}

		public int getHistory_score() {
			return history_score;
		}

		public void setHistory_score(int history_score) {
			this.history_score = history_score;
		}

		public int getTimes() {
			return times;
		}

		public void setTimes(int times) {
			this.times = times;
		}
	}
}
