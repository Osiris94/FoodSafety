package com.osiris.food.model;

import java.util.List;

public class Certification {


	/**
	 * status : success
	 * code : 200
	 * data : [{"name":"初级食品安全考核","thumb":"http://132.232.227.58/uploads/certificates/20190620/02820a3fa35b970e1820f08a020796fa.png","cert_id":2},{"name":"初级食品安全考核","thumb":"http://132.232.227.58/uploads/certificates/20190620/02820a3fa35b970e1820f08a020796fa.png","cert_id":2}]
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
		 * name : 初级食品安全考核
		 * thumb : http://132.232.227.58/uploads/certificates/20190620/02820a3fa35b970e1820f08a020796fa.png
		 * cert_id : 2
		 */

		private String name;
		private String thumb;
		private int cert_id;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public int getCert_id() {
			return cert_id;
		}

		public void setCert_id(int cert_id) {
			this.cert_id = cert_id;
		}
	}
}
