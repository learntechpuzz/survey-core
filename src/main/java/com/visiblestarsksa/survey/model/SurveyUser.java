package com.visiblestarsksa.survey.model;

import static com.visiblestarsksa.survey.util.SurveyConfig.SURVEY_URL_PREFIX;

import com.visiblestarsksa.survey.domain.enums.ESurveyUserStatus;
import com.visiblestarsksa.survey.util.CryptoUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Slf4j
@Entity
@Table(name = "survey_users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SurveyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "extraction_date")
    private Date extractionDate;

    @Column(name = "branch_code")
    private Long branchCode;

    @Column(name = "mask_party")
    private Long maskParty;

    @Column(name = "served_by")
    private String servedBy;

    @Column(name = "trans_desc")
    private String transDesc;

    @Column(name = "segment")
    private String segment;

    @Column(name = "gender")
    private String gender;

    @Column(name = "survey_url")
    private String surveyUrl;

    @Column(name = "viewed_on")
    private Timestamp viewedOn;

    @Column(name = "completed_on")
    private Timestamp completedOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "expire_on")
    private Timestamp expireOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ESurveyUserStatus status;

    @PostPersist
    public void generateSurveyData() throws Exception {
        this.surveyUrl = SURVEY_URL_PREFIX + "?st=" + CryptoUtil.encrypt(String.valueOf(this.id));
        log.debug("survey_url: {}", surveyUrl);
    }
}
