package com.visiblestarsksa.survey.domain;

public interface ISurveyUserResponse {

    Long getSurveyUserId();

    String getSurveyUrl();

    Long getMaskParty();

    String getGender();

    Long getBranchCode();

    String getSegment();

    String getTransDesc();

    Long getTotalQuestions();

    Long getTotalQuestionsAnswered();
}
