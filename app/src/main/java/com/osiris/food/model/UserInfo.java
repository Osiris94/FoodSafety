package com.osiris.food.model;

public class UserInfo {


	/**
	 * status : success
	 * code : 200
	 * data : {"name":"Ashin","phone":"18370894190","type":5,"avatar":"http://132.232.227.58/uploads/th.jpeg","gender":"男","birthday":"1999-08-11","address":"","company":"","identity_card_type":1,"sno":10000,"created_at":"148天","video_time":"0小时"}
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
		 * name : Ashin
		 * phone : 18370894190
		 * type : 5
		 * avatar : http://132.232.227.58/uploads/th.jpeg
		 * gender : 男
		 * birthday : 1999-08-11
		 * address :
		 * company :
		 * identity_card_type : 1
		 * sno : 10000
		 * created_at : 148天
		 * video_time : 0小时
		 */

		private String name;
		private String phone;
		private int type;
		private String avatar;
		private String gender;
		private String birthday;
		private String address;
		private String company;
		private int identity_card_type;
		private int sno;
		private String created_at;
		private String video_time;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public int getIdentity_card_type() {
			return identity_card_type;
		}

		public void setIdentity_card_type(int identity_card_type) {
			this.identity_card_type = identity_card_type;
		}

		public int getSno() {
			return sno;
		}

		public void setSno(int sno) {
			this.sno = sno;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getVideo_time() {
			return video_time;
		}

		public void setVideo_time(String video_time) {
			this.video_time = video_time;
		}
	}
}
