package com.osiris.food.model;

public class ContenDetail {


	/**
	 * status : success
	 * code : 200
	 * data : {"title":"国务院关于加强食品等产品安全监督管理的特别规定(国务院令第503号)","thumb":"http://132.232.227.58/uploads/contents/20190624/6ef1931b5e4fcf38008bd95c94f2b8c6.jpg","summary":"《国务院关于加强食品等产品安全监督管理的特别规定》已经2007年7月25日国务院第186次常务会议通过，现予公布，自公布之日起施行。","content":"content","created_at":"2019-06-17 02:16:30","category_id":1,"author":"Administrator","category_name":"政策法规"}
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
		 * title : 国务院关于加强食品等产品安全监督管理的特别规定(国务院令第503号)
		 * thumb : http://132.232.227.58/uploads/contents/20190624/6ef1931b5e4fcf38008bd95c94f2b8c6.jpg
		 * summary : 《国务院关于加强食品等产品安全监督管理的特别规定》已经2007年7月25日国务院第186次常务会议通过，现予公布，自公布之日起施行。
		 * content : content
		 * created_at : 2019-06-17 02:16:30
		 * category_id : 1
		 * author : Administrator
		 * category_name : 政策法规
		 */

		private String title;
		private String thumb;
		private String summary;
		private String content;
		private String created_at;
		private int category_id;
		private String author;
		private String category_name;

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

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
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

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getCategory_name() {
			return category_name;
		}

		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}
	}
}
