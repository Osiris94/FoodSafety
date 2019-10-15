package com.osiris.food.model;

import java.util.List;

public class EduList {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":1,"name":"2018～2019年度初级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","lesson_no":"2019J00001","price":"100.00","level":"初级"},{"id":2,"name":"2018～2019年度中级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","lesson_no":"2019J00002","price":"150.00","level":"中级"},{"id":3,"name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","lesson_no":"2019J00003","price":"200.00","level":"高级"}]
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
		 * id : 1
		 * name : 2018～2019年度初级食品安全培训
		 * start_time : 2019-06-01
		 * end_time : 2020-06-01
		 * lesson_no : 2019J00001
		 * price : 100.00
		 * level : 初级
		 */

		private int id;
		private String name;
		private String train_no;
		private String price;
		private int level;
		private int type;
		private int order_num;

		public int getOrder_num() {
			return order_num;
		}

		public void setOrder_num(int order_num) {
			this.order_num = order_num;
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


		public String getTrain_no() {
			return train_no;
		}

		public void setTrain_no(String train_no) {
			this.train_no = train_no;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
	}
}
