package com.osiris.food.model;

import java.util.List;

public class VideoDetailBean {


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


        private int id;
        private String name;
        private String intro;
        private String path;
        private int comment_count;
        private List<CommentsBean> comments;
        private int lesson_id;
        private int score;

	    public int getLesson_id() {
		    return lesson_id;
	    }

	    public void setLesson_id(int lesson_id) {
		    this.lesson_id = lesson_id;
	    }

	    public int getScore() {
		    return score;
	    }

	    public void setScore(int score) {
		    this.score = score;
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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {


            private int topic_id;
            private String topic_type;
            private String content;
            private int from_uid;
            private String created_at;
            private OwnerBean owner;

            public int getTopic_id() {
                return topic_id;
            }

            public void setTopic_id(int topic_id) {
                this.topic_id = topic_id;
            }

            public String getTopic_type() {
                return topic_type;
            }

            public void setTopic_type(String topic_type) {
                this.topic_type = topic_type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getFrom_uid() {
                return from_uid;
            }

            public void setFrom_uid(int from_uid) {
                this.from_uid = from_uid;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
                this.owner = owner;
            }

            public static class OwnerBean {
                /**
                 * id : 1
                 * name : Ashin
                 */

                private int id;
                private String name;

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
            }
        }
    }
}
