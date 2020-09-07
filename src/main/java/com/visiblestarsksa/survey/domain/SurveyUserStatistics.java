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
public class SurveyUserStatistics {

    private Long totalCompleted;
    private Long totalViewed;
    private Long totalNotViewed;

    public SurveyUserStatistics(Object[] columns) {
        this.totalCompleted = (columns[0] != null) ? ((BigInteger) columns[0]).longValue() : 0;
        this.totalViewed = (columns[1] != null) ? ((BigInteger) columns[1]).longValue() : 0;
        this.totalNotViewed = (columns[2] != null) ? ((BigInteger) columns[2]).longValue() : 0;
    }
}
