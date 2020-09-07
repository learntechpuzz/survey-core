package com.visiblestarsksa.survey.model;

import com.visiblestarsksa.survey.domain.enums.EQuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "survey_questions")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "step_no")
    private Integer stepNo;

    @NotBlank
    @Size(max = 255)
    @Column(name = "question_en")
    private String questionEn;

    @NotBlank
    @Size(max = 255)
    @Column(name = "question_ar")
    private String questionAr;

    @Column(name = "required")
    private boolean required;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EQuestionType type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    @OrderBy(value = "id ASC")
    private List<Answer> answers;
}
