package com.visiblestarsksa.survey.domain;

public interface IUserAnswer {

    Long getSurveyUserId();

    Long getQuestionId();

    Long getAnswerId();

    String getComments();
}
