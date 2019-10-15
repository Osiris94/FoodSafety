package com.osiris.food.model;

import java.util.List;

public class MyStudy {


	/**
	 * status : success
	 * code : 200
	 * data : {"video_course":[{"lesson_id":1,"category_name":"食品安全","name":"2018～2019年度初级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"http://132.232.227.58/uploads/lessons/20190625/fec54c21685fbf483a21b6212277caa8.jpg","time":"02:56:03","visited":"00:00:00"},{"lesson_id":2,"category_name":"食品安全","name":"2018～2019年度中级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"http://132.232.227.58/uploads/lessons/20190625/8e17abd61c9ebb759a42c5e5f7aa114a.jpg","time":"03:03:20","visited":"00:00:00"}],"public_course":[{"id":21,"category_id":1,"title":"公共场所卫生管理条例实施细则（卫生部令第80号）","thumb":"http://132.232.227.58/uploads/contents/20190702/104d77d21d589df0fc43cb223bafe460.jpeg","created_at":"2019-07-02","category_name":"政策法规"}]}
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
		private List<VideoCourseBean> video_course;
		private List<PublicCourseBean> public_course;

		public List<VideoCourseBean> getVideo_course() {
			return video_course;
		}

		public void setVideo_course(List<VideoCourseBean> video_course) {
			this.video_course = video_course;
		}

		public List<PublicCourseBean> getPublic_course() {
			return public_course;
		}

		public void setPublic_course(List<PublicCourseBean> public_course) {
			this.public_course = public_course;
		}

		public static class VideoCourseBean {
			/**
			 * lesson_id : 1
			 * category_name : 食品安全
			 * name : 2018～2019年度初级食品安全培训
			 * start_time : 2019-06-01
			 * end_time : 2020-06-01
			 * thumb : http://132.232.227.58/uploads/lessons/20190625/fec54c21685fbf483a21b6212277caa8.jpg
			 * time : 02:56:03
			 * visited : 00:00:00
			 */

			private int lesson_id;
			private String category_name;
			private String name;
			private String start_time;
			private String end_time;
			private String thumb;
			private String time;
			private String visited;

			public int getLesson_id() {
				return lesson_id;
			}

			public void setLesson_id(int lesson_id) {
				this.lesson_id = lesson_id;
			}

			public String getCategory_name() {
				return category_name;
			}

			public void setCategory_name(String category_name) {
				this.category_name = category_name;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getStart_time() {
				return start_time;
			}

			public void setStart_time(String start_time) {
				this.start_time = start_time;
			}

			public String getEnd_time() {
				return end_time;
			}

			public void setEnd_time(String end_time) {
				this.end_time = end_time;
			}

			public String getThumb() {
				return thumb;
			}

			public void setThumb(String thumb) {
				this.thumb = thumb;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public String getVisited() {
				return visited;
			}

			public void setVisited(String visited) {
				this.visited = visited;
			}
		}

		public static class PublicCourseBean {
			/**
			 * id : 21
			 * category_id : 1
			 * title : 公共场所卫生管理条例实施细则（卫生部令第80号）
			 * thumb : http://132.232.227.58/uploads/contents/20190702/104d77d21d589df0fc43cb223bafe460.jpeg
			 * created_at : 2019-07-02
			 * category_name : 政策法规
			 */

			private int id;
			private int category_id;
			private String title;
			private String thumb;
			private String created_at;
			private String category_name;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getCategory_id() {
				return category_id;
			}

			public void setCategory_id(int category_id) {
				this.category_id = category_id;
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

			public String getCategory_name() {
				return category_name;
			}

			public void setCategory_name(String category_name) {
				this.category_name = category_name;
			}
		}
	}
}
