package com.osiris.food.model;

import java.util.List;

public class ScoreDetail {


	/**
	 * status : success
	 * code : 200
	 * data : [{"task_name":"每日首次登录","score":1,"created_at":"2019-06-20 21:36:33"},{"task_name":"观看视频","score":2,"created_at":"2019-06-20 21:37:05"}]
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
		 * task_name : 每日首次登录
		 * score : 1
		 * created_at : 2019-06-20 21:36:33
		 */

		private String task_name;
		private int score;
		private String created_at;

		public String getTask_name() {
			return task_name;
		}

		public void setTask_name(String task_name) {
			this.task_name = task_name;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}
	}
}
