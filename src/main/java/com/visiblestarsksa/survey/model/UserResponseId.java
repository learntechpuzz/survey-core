package com.visiblestarsksa.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "survey_user_id")
    private Long surveyUserId;

    @Column(name = "question_id")
    private Long questionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponseId)) return false;
        UserResponseId userResponseId = (UserResponseId) o;
        return Objects.equals(getSurveyUserId(), userResponseId.getSurveyUserId())
                && Objects.equals(getQuestionId(), userResponseId.getQuestionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurveyUserId(), getQuestionId());
    }
}
