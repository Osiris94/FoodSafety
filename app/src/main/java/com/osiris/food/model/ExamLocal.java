package com.osiris.food.model;

import io.realm.RealmObject;

public class ExamLocal extends RealmObject {


	private int examId;
	private int answerId;
	private String answer;
	private String selectAnswer;
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSelectAnswer() {
		return selectAnswer;
	}

	public void setSelectAnswer(String selectAnswer) {
		this.selectAnswer = selectAnswer;
	}
}
