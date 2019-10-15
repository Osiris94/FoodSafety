package com.osiris.food.model;

import java.util.List;

public class ProvinceDetail {


	/**
	 * status : success
	 * code : 200
	 * data : [{"id":11,"name":"北京市","parent_id":0},{"id":12,"name":"天津市","parent_id":0},{"id":13,"name":"河北省","parent_id":0},{"id":14,"name":"山西省","parent_id":0},{"id":15,"name":"内蒙古自治区","parent_id":0},{"id":21,"name":"辽宁省","parent_id":0},{"id":22,"name":"吉林省","parent_id":0},{"id":23,"name":"黑龙江省","parent_id":0},{"id":31,"name":"上海市","parent_id":0},{"id":32,"name":"江苏省","parent_id":0},{"id":33,"name":"浙江省","parent_id":0},{"id":34,"name":"安徽省","parent_id":0},{"id":35,"name":"福建省","parent_id":0},{"id":36,"name":"江西省","parent_id":0},{"id":37,"name":"山东省","parent_id":0},{"id":41,"name":"河南省","parent_id":0},{"id":42,"name":"湖北省","parent_id":0},{"id":43,"name":"湖南省","parent_id":0},{"id":44,"name":"广东省","parent_id":0},{"id":45,"name":"广西壮族自治区","parent_id":0},{"id":46,"name":"海南省","parent_id":0},{"id":50,"name":"重庆市","parent_id":0},{"id":51,"name":"四川省","parent_id":0},{"id":52,"name":"贵州省","parent_id":0},{"id":53,"name":"云南省","parent_id":0},{"id":54,"name":"西藏自治区","parent_id":0},{"id":61,"name":"陕西省","parent_id":0},{"id":62,"name":"甘肃省","parent_id":0},{"id":63,"name":"青海省","parent_id":0},{"id":64,"name":"宁夏回族自治区","parent_id":0},{"id":65,"name":"新疆维吾尔自治区","parent_id":0},{"id":71,"name":"台湾省","parent_id":0},{"id":81,"name":"香港特别行政区","parent_id":0},{"id":82,"name":"澳门特别行政区","parent_id":0}]
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
		 * id : 11
		 * name : 北京市
		 * parent_id : 0
		 */

		private int id;
		private String name;
		private int parent_id;

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

		public int getParent_id() {
			return parent_id;
		}

		public void setParent_id(int parent_id) {
			this.parent_id = parent_id;
		}
	}
}
