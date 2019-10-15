package com.osiris.food.model;

import java.util.List;

public class OrderList {


	/**
	 * status : success
	 * code : 200
	 * data : [{"name":"2018~2019食品安全中级培训课程","price":"100.00","type":"未支付","created_at":"2019-06-18 21:45:30"}]
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
		 * name : 2018~2019食品安全中级培训课程
		 * price : 100.00
		 * type : 未支付
		 * created_at : 2019-06-18 21:45:30
		 */

		private String name;
		private String price;
		private String type;
		private String created_at;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
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
	}
}
