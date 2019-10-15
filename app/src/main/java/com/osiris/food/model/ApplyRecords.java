package com.osiris.food.model;

import java.util.List;

public class ApplyRecords {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":2,"object_id":1,"type":"餐饮行业","created_at":"2019-07-27 23:58:23","cert":"食品安全管理员证","pass":"未通过"}]
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
		 * object_id : 1
		 * type : 餐饮行业
		 * created_at : 2019-07-27 23:58:23
		 * cert : 食品安全管理员证
		 * pass : 未通过
		 */

		private int id;
		private int object_id;
		private String type;
		private String created_at;
		private String cert;
		private String pass;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getObject_id() {
			return object_id;
		}

		public void setObject_id(int object_id) {
			this.object_id = object_id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getCert() {
			return cert;
		}

		public void setCert(String cert) {
			this.cert = cert;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}
	}
}
