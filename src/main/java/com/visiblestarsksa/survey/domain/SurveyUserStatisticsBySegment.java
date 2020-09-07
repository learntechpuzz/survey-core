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
public class SurveyUserStatisticsBySegment {

    private String segment;
    private Long count;

    public SurveyUserStatisticsBySegment(Object[] columns) {
        this.segment = (columns[0] != null) ? ((String) columns[0]) : "";
        this.count = (columns[1] != null) ? ((BigInteger) columns[1]).longValue() : 0;
    }
}
