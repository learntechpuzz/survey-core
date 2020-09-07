package com.visiblestarsksa.survey.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SurveyUserStatisticsByGender {

    private String interval;
    private String gender;
    private Long count;

    public SurveyUserStatisticsByGender(Object[] columns) {
        this.interval = (columns[0] != null) ? ((String) columns[0]) : "";
        this.gender = (columns[1] != null) ? ((String) columns[1]) : "";
        this.count = (columns[2] != null) ? ((BigInteger) columns[2]).longValue() : 0;
    }
}
