package com.osiris.food.event;

public class StudyFindEvent {
    private String studyFindMsg;

    public StudyFindEvent(String studyFindMsg) {
        this.studyFindMsg = studyFindMsg;
    }

    public String getStudyFindMsg() {
        return studyFindMsg;
    }

    public void setStudyFindMsg(String studyFindMsg) {
        this.studyFindMsg = studyFindMsg;
    }
}
