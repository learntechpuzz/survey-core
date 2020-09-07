package com.visiblestarsksa.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "survey_user_responses")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse implements Serializable {

    @EmbeddedId UserResponseId userResponseId;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "survey_user_answers",
            joinColumns = {
                @JoinColumn(name = "survey_user_id", referencedColumnName = "survey_user_id"),
                @JoinColumn(name = "question_id", referencedColumnName = "question_id")
            })
    private Set<UserAnswer> userAnswers;
}
