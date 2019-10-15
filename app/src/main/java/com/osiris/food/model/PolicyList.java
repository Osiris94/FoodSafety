package com.osiris.food.model;

import java.util.List;

public class PolicyList {


	/**
	 * status : success
	 * code : 200
	 * data : {"current_page":1,"data":[{"id":3,"title":"朝鲜友好艺术团将对中国进行访问演出-行业咨询","thumb":"","created_at":"2019-06-17 02:17:44","category_id":3,"category_name":"行业咨询"},{"id":2,"title":"朝鲜友好艺术团将对中国进行访问演出-创城动态","thumb":"","created_at":"2019-06-17 02:17:12","category_id":2,"category_name":"创城动态"},{"id":1,"title":"朝鲜友好艺术团将对中国进行访问演出-修改","thumb":"","created_at":"2019-06-17 02:16:30","category_id":1,"category_name":"政策法规"}],"first_page_url":"http://132.232.227.58/api/v1/contents?page=1","from":1,"last_page":1,"last_page_url":"http://132.232.227.58/api/v1/contents?page=1","next_page_url":null,"path":"http://132.232.227.58/api/v1/contents","per_page":10,"prev_page_url":null,"to":3,"total":3}
	 */

	private String status;
	private int code;
	private DataBeanX data;

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

	public DataBeanX getData() {
		return data;
	}

	public void setData(DataBeanX data) {
		this.data = data;
	}

	public static class DataBeanX {
		/**
		 * current_page : 1
		 * data : [{"id":3,"title":"朝鲜友好艺术团将对中国进行访问演出-行业咨询","thumb":"","created_at":"2019-06-17 02:17:44","category_id":3,"category_name":"行业咨询"},{"id":2,"title":"朝鲜友好艺术团将对中国进行访问演出-创城动态","thumb":"","created_at":"2019-06-17 02:17:12","category_id":2,"category_name":"创城动态"},{"id":1,"title":"朝鲜友好艺术团将对中国进行访问演出-修改","thumb":"","created_at":"2019-06-17 02:16:30","category_id":1,"category_name":"政策法规"}]
		 * first_page_url : http://132.232.227.58/api/v1/contents?page=1
		 * from : 1
		 * last_page : 1
		 * last_page_url : http://132.232.227.58/api/v1/contents?page=1
		 * next_page_url : null
		 * path : http://132.232.227.58/api/v1/contents
		 * per_page : 10
		 * prev_page_url : null
		 * to : 3
		 * total : 3
		 */

		private int current_page;
		private String first_page_url;
		private int from;
		private int last_page;
		private String last_page_url;
		private Object next_page_url;
		private String path;
		private int per_page;
		private Object prev_page_url;
		private int to;
		private int total;
		private List<DataBean> data;

		public int getCurrent_page() {
			return current_page;
		}

		public void setCurrent_page(int current_page) {
			this.current_page = current_page;
		}

		public String getFirst_page_url() {
			return first_page_url;
		}

		public void setFirst_page_url(String first_page_url) {
			this.first_page_url = first_page_url;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getLast_page() {
			return last_page;
		}

		public void setLast_page(int last_page) {
			this.last_page = last_page;
		}

		public String getLast_page_url() {
			return last_page_url;
		}

		public void setLast_page_url(String last_page_url) {
			this.last_page_url = last_page_url;
		}

		public Object getNext_page_url() {
			return next_page_url;
		}

		public void setNext_page_url(Object next_page_url) {
			this.next_page_url = next_page_url;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public int getPer_page() {
			return per_page;
		}

		public void setPer_page(int per_page) {
			this.per_page = per_page;
		}

		public Object getPrev_page_url() {
			return prev_page_url;
		}

		public void setPrev_page_url(Object prev_page_url) {
			this.prev_page_url = prev_page_url;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
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
			 * title : 朝鲜友好艺术团将对中国进行访问演出-行业咨询
			 * thumb :
			 * created_at : 2019-06-17 02:17:44
			 * category_id : 3
			 * category_name : 行业咨询
			 */

			private int id;
			private String title;
			private String thumb;
			private String created_at;
			private int category_id;
			private String category_name;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getThumb() {
				return thumb;
			}

			public void setThumb(String thumb) {
				this.thumb = thumb;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public int getCategory_id() {
				return category_id;
			}

			public void setCategory_id(int category_id) {
				this.category_id = category_id;
			}

			public String getCategory_name() {
				return category_name;
			}

			public void setCategory_name(String category_name) {
				this.category_name = category_name;
			}
		}
	}
}
