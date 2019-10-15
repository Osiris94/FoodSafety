package com.osiris.food.model;

import java.util.List;

public class LearnsMajorBean {

    /**
     * status : success
     * code : 200
     * data : {"lesson_id":3,"lesson":{"name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01"},"list":[{"id":6,"time":"00:12","name":"2018～2019年度高级食品安全培训","category":"食品安全","thumb":"","visited":"00:00:00"}]}
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
         * lesson_id : 3
         * lesson : {"name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01"}
         * list : [{"id":6,"time":"00:12","name":"2018～2019年度高级食品安全培训","category":"食品安全","thumb":"","visited":"00:00:00"}]
         */

        private int lesson_id;
        private LessonBean lesson;
        private List<ListBean> list;

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public LessonBean getLesson() {
            return lesson;
        }

        public void setLesson(LessonBean lesson) {
            this.lesson = lesson;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class LessonBean {
            /**
             * name : 2018～2019年度高级食品安全培训
             * start_time : 2019-06-01
             * end_time : 2020-06-01
             */

            private String name;
            private String start_time;
            private String end_time;

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
        }

        public static class ListBean {
            /**
             * id : 6
             * time : 00:12
             * name : 2018～2019年度高级食品安全培训
             * category : 食品安全
             * thumb :
             * visited : 00:00:00
             */

            private int id;
            private String time;
            private String name;
            private String category;
            private String thumb;
            private String visited;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getVisited() {
                return visited;
            }

            public void setVisited(String visited) {
                this.visited = visited;
            }
        }
    }
}
