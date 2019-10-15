package com.osiris.food.model;

import java.util.List;

public class Notification {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":2,"message_id":2,"created_at":"2019-06-22 10:00:00","subject":"市场监督总局关于调整《食品经营许可证》...","type":"首页轮播"}]
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
		 * id : 2
		 * message_id : 2
		 * created_at : 2019-06-22 10:00:00
		 * subject : 市场监督总局关于调整《食品经营许可证》...
		 * type : 首页轮播
		 */

		private int id;
		private int message_id;
		private String created_at;
		private String subject;
		private String type;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getMessage_id() {
			return message_id;
		}

		public void setMessage_id(int message_id) {
			this.message_id = message_id;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
}
