package com.osiris.food.model;

import java.util.List;

public class ExamDetail  {


	/**
	 * status : success
	 * code : 200
	 * data : {"id":4,"name":"2018～2019年度食品安全管理员高 级考试","questions":{"DXT":"1,2,3,4,5,6,7,8","MDXT":"3,11","BDXT":"0","PDT":"0","JDT":"0"},"question_items":[{"id":1,"question":"以下哪类因素是食物中中毒的主要原因？","select":["化学性危害和物理性危害","细菌和病毒","寄生虫和霉菌"],"answer":"B","type":"1"},{"id":2,"question":"大部分餐饮食品中能够实际运用的控制食品中细菌生长繁殖最有效的措施是：","select":["控制时间和温度","控制PH和氧气","控制温度和水分活性"],"answer":"B","type":"2"},{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D|","type":"3"},{"id":4,"question":"餐饮服务提供者要建立保证餐饮食品安全的各项制度，配备经考核合格的（）","select":["对","错"],"answer":"B","type":"4"},{"id":5,"question":"违反《食品安全法》规定，构成犯罪的，依法追究  （）","select":["  道德谴责 ","民事责任","刑事责任  "],"answer":"A","type":"1"},{"id":6,"question":"以下哪类因素是食物中中毒的主要原因？","select":["化学性危害和物理性危害","细菌和病毒","寄生虫和霉菌"],"answer":"B","type":"1"},{"id":7,"question":"以下哪种食物最可能引起亚硝酸盐食物中毒？","select":["变质的鱼肉","制作不当的腌肉，肴肉","霉变的花生"],"answer":"B","type":"1"},{"id":8,"question":"大部分餐饮食品中能够实际运用的控制食品中细菌生长繁殖最有效的措施是：","select":["控制时间和温度","控制PH和氧气","控制温度和水分活性"],"answer":"A","type":"1"},{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D|","type":"3"},{"id":11,"question":"常见的能够产生芽孢的致病菌包括：","select":["&nbsp;肉毒梭菌","蜡样芽孢杆菌","沙门菌","产气荚膜杆菌"],"answer":"A|B|D|","type":"2"}]}
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
		 * id : 4
		 * name : 2018～2019年度食品安全管理员高 级考试
		 * questions : {"DXT":"1,2,3,4,5,6,7,8","MDXT":"3,11","BDXT":"0","PDT":"0","JDT":"0"}
		 * question_items : [{"id":1,"question":"以下哪类因素是食物中中毒的主要原因？","select":["化学性危害和物理性危害","细菌和病毒","寄生虫和霉菌"],"answer":"B","type":"1"},{"id":2,"question":"大部分餐饮食品中能够实际运用的控制食品中细菌生长繁殖最有效的措施是：","select":["控制时间和温度","控制PH和氧气","控制温度和水分活性"],"answer":"B","type":"2"},{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D|","type":"3"},{"id":4,"question":"餐饮服务提供者要建立保证餐饮食品安全的各项制度，配备经考核合格的（）","select":["对","错"],"answer":"B","type":"4"},{"id":5,"question":"违反《食品安全法》规定，构成犯罪的，依法追究  （）","select":["  道德谴责 ","民事责任","刑事责任  "],"answer":"A","type":"1"},{"id":6,"question":"以下哪类因素是食物中中毒的主要原因？","select":["化学性危害和物理性危害","细菌和病毒","寄生虫和霉菌"],"answer":"B","type":"1"},{"id":7,"question":"以下哪种食物最可能引起亚硝酸盐食物中毒？","select":["变质的鱼肉","制作不当的腌肉，肴肉","霉变的花生"],"answer":"B","type":"1"},{"id":8,"question":"大部分餐饮食品中能够实际运用的控制食品中细菌生长繁殖最有效的措施是：","select":["控制时间和温度","控制PH和氧气","控制温度和水分活性"],"answer":"A","type":"1"},{"id":3,"question":"根据增值税法律制度的规定，下列各项中，应按照\u201c销售服务 \u2014\u2014建筑服务\u201d税目计缴增值税的是（）。（2017年）","select":["平整土地","出售住宅","出租办公楼","转让土地使用权"],"answer":"A|B|C|D|","type":"3"},{"id":11,"question":"常见的能够产生芽孢的致病菌包括：","select":["&nbsp;肉毒梭菌","蜡样芽孢杆菌","沙门菌","产气荚膜杆菌"],"answer":"A|B|D|","type":"2"}]
		 */

		private int id;
		private String name;
		private QuestionsBean questions;
		private List<QuestionItemsBean> question_items;

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

		public QuestionsBean getQuestions() {
			return questions;
		}

		public void setQuestions(QuestionsBean questions) {
			this.questions = questions;
		}

		public List<QuestionItemsBean> getQuestion_items() {
			return question_items;
		}

		public void setQuestion_items(List<QuestionItemsBean> question_items) {
			this.question_items = question_items;
		}

		public static class QuestionsBean {
			/**
			 * DXT : 1,2,3,4,5,6,7,8
			 * MDXT : 3,11
			 * BDXT : 0
			 * PDT : 0
			 * JDT : 0
			 */

			private String DXT;
			private String MDXT;
			private String BDXT;
			private String PDT;
			private String JDT;

			public String getDXT() {
				return DXT;
			}

			public void setDXT(String DXT) {
				this.DXT = DXT;
			}

			public String getMDXT() {
				return MDXT;
			}

			public void setMDXT(String MDXT) {
				this.MDXT = MDXT;
			}

			public String getBDXT() {
				return BDXT;
			}

			public void setBDXT(String BDXT) {
				this.BDXT = BDXT;
			}

			public String getPDT() {
				return PDT;
			}

			public void setPDT(String PDT) {
				this.PDT = PDT;
			}

			public String getJDT() {
				return JDT;
			}

			public void setJDT(String JDT) {
				this.JDT = JDT;
			}
		}

		public static class QuestionItemsBean{
			/**
			 * id : 1
			 * question : 以下哪类因素是食物中中毒的主要原因？
			 * select : ["化学性危害和物理性危害","细菌和病毒","寄生虫和霉菌"]
			 * answer : B
			 * type : 1
			 */

			private int id;
			private String question;
			private String answer;
			private String type;
			private List<String> select;
			private String selectAnswer;

			public String getSelectAnswer() {
				return selectAnswer;
			}

			public void setSelectAnswer(String selectAnswer) {
				this.selectAnswer = selectAnswer;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getQuestion() {
				return question;
			}

			public void setQuestion(String question) {
				this.question = question;
			}

			public String getAnswer() {
				return answer;
			}

			public void setAnswer(String answer) {
				this.answer = answer;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public List<String> getSelect() {
				return select;
			}

			public void setSelect(List<String> select) {
				this.select = select;
			}
		}
	}
}
