package com.osiris.food.model;

public class Message {


	/**
	 * status : success
	 * code : 200
	 * data : {"title":"食品安全标准护航舌尖上的安","id":16}
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
		 * title : 食品安全标准护航舌尖上的安
		 * id : 16
		 */

		private String title;
		private int id;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
