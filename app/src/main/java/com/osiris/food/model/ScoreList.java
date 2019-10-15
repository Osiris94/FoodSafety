package com.osiris.food.model;

import java.util.List;

public class ScoreList {


	/**
	 * status : success
	 * code : 200
	 * data : [{"name":"每日首次登录","score":1,"max_score":1,"bounce":0},{"name":"阅读文章","score":1,"max_score":1,"bounce":1},{"name":"观看视频","score":2,"max_score":6,"bounce":0},{"name":"文章学习市场","score":3,"max_score":8,"bounce":1},{"name":"视频学习市场","score":1,"max_score":10,"bounce":1}]
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
		 * name : 每日首次登录
		 * score : 1
		 * max_score : 1
		 * bounce : 0
		 */

		private String name;
		private int score;
		private int max_score;
		private int bounce;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getMax_score() {
			return max_score;
		}

		public void setMax_score(int max_score) {
			this.max_score = max_score;
		}

		public int getBounce() {
			return bounce;
		}

		public void setBounce(int bounce) {
			this.bounce = bounce;
		}
	}
}
