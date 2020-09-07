package com.visiblestarsksa.survey.domain;

public interface ISurvey {

    Long getSurveyId();

    String getTitle();

    String getCategory();

    Long getTotalQuestions();

    Long getTotalUsers();

    String getExpireOn();
}
