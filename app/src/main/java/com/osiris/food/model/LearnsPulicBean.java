package com.osiris.food.model;

import java.util.List;

public class LearnsPulicBean {


    /**
     * status : success
     * code : 200
     * data : {"courses":[{"id":2,"thumb":"http://132.232.227.58/uploads/lessons/20190625/8e17abd61c9ebb759a42c5e5f7aa114a.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":13,"category":"食品安全","title":"2018～2019年度中级食品安全培训","visited":1},{"id":3,"thumb":"http://132.232.227.58/uploads/lessons/20190625/701f9141efc960e371afb1d4407d19a4.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":13,"category":"食品安全","title":"2018～2019年度高级食品安全培训","visited":1},{"id":23,"thumb":"","start_time":"2019-07-05","end_time":"2023-04-30","category_id":13,"category":"食品安全","title":"海陵区学校食堂食品安全管理员 应知应会100条","visited":0},{"id":31,"thumb":"","start_time":"2019-07-08","end_time":"2023-04-30","category_id":13,"category":"食品安全","title":"泰州市食品药品监管 工作手册","visited":0},{"id":32,"thumb":"","start_time":"2019-07-08","end_time":"2023-04-30","category_id":13,"category":"食品安全","title":"餐饮服务食品安全操作规范","visited":0},{"id":33,"thumb":"","start_time":"2019-07-08","end_time":"2023-04-30","category_id":13,"category":"食品安全","title":"餐饮服务单位食品安全 基础管理手册","visited":0}],"video":[{"id":25,"name":"食品安全五要点-4","thumb":"http://132.232.227.58/uploads/lessons/20190707/de4e0b5ec830ca5a177f0bcb48510c7f.jpg","start_time":"2019-07-06","end_time":"2019-07-20","category_id":13,"category":"食品安全","time":"00:00:40","visited":"00:00:00"},{"id":30,"name":"食品安全五要点-1","thumb":"http://132.232.227.58/uploads/lessons/20190707/29a99d72ee8025b8f822bd6f4f555e76.jpg","start_time":"2019-07-07","end_time":"2019-09-15","category_id":13,"category":"食品安全","time":"00:00:40","visited":"00:00:00"},{"id":31,"name":"食品安全五要点-2","thumb":"http://132.232.227.58/uploads/lessons/20190707/94c997d21379a0cce6d0134a6b16bd5c.jpg","start_time":"2019-07-07","end_time":"2019-07-31","category_id":13,"category":"食品安全","time":"00:00:02","visited":"00:00:00"},{"id":33,"name":"食品安全五要点-3","thumb":"http://132.232.227.58/uploads/lessons/20190707/0864b5617a62061c379585c06bb214d5.jpg","start_time":"2019-07-01","end_time":"2019-10-27","category_id":13,"category":"食品安全","time":"00:11:00","visited":"00:00:00"},{"id":34,"name":"食品安全五要点-5","thumb":"http://132.232.227.58/uploads/lessons/20190707/f457bd3bad7b45dfbc21fca6ccc0c764.jpg","start_time":"2019-07-01","end_time":"2019-11-30","category_id":13,"category":"食品安全","time":"00:02:00","visited":"00:00:00"}],"content":[{"id":38,"thumb":"","start_time":"2019-07-01","end_time":"2019-07-28","category_id":13,"category":"食品安全","title":"测试专业课程","visited":0}]}
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
        private List<CoursesBean> courses;
        private List<VideoBean> video;
        private List<ContentBean> content;

        public List<CoursesBean> getCourses() {
            return courses;
        }

        public void setCourses(List<CoursesBean> courses) {
            this.courses = courses;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class CoursesBean {
            /**
             * id : 2
             * thumb : http://132.232.227.58/uploads/lessons/20190625/8e17abd61c9ebb759a42c5e5f7aa114a.jpg
             * start_time : 2019-06-01
             * end_time : 2020-06-01
             * category_id : 13
             * category : 食品安全
             * title : 2018～2019年度中级食品安全培训
             * visited : 1
             */

            private int id;
            private String thumb;
            private String start_time;
            private String end_time;
            private int category_id;
            private String category;
            private String title;
            private int visited;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getVisited() {
                return visited;
            }

            public void setVisited(int visited) {
                this.visited = visited;
            }
        }

        public static class VideoBean {
            /**
             * id : 25
             * name : 食品安全五要点-4
             * thumb : http://132.232.227.58/uploads/lessons/20190707/de4e0b5ec830ca5a177f0bcb48510c7f.jpg
             * start_time : 2019-07-06
             * end_time : 2019-07-20
             * category_id : 13
             * category : 食品安全
             * time : 00:00:40
             * visited : 00:00:00
             */

            private int id;
            private String name;
            private String thumb;
            private String start_time;
            private String end_time;
            private int category_id;
            private String category;
            private String time;
            private String visited;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
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

        public static class ContentBean {
            /**
             * id : 38
             * thumb :
             * start_time : 2019-07-01
             * end_time : 2019-07-28
             * category_id : 13
             * category : 食品安全
             * title : 测试专业课程
             * visited : 0
             */

            private int id;
            private String thumb;
            private String start_time;
            private String end_time;
            private int category_id;
            private String category;
            private String title;
            private int visited;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getVisited() {
                return visited;
            }

            public void setVisited(int visited) {
                this.visited = visited;
            }
        }
    }
}
