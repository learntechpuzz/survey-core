package com.visiblestarsksa.survey.repository;

import com.visiblestarsksa.survey.domain.IUserAnswer;
import com.visiblestarsksa.survey.model.UserResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReponseRepository extends JpaRepository<UserResponse, Long> {

    @Query(
            value =
                    "SELECT survey_user_id as surveyUserId,question_id as questionId,answer_id as answerId,comments as comments FROM survey_user_answers WHERE survey_user_id = :surveyUserId",
            nativeQuery = true)
    List<IUserAnswer> findAllAnswersBySurveyUserId(@Param("surveyUserId") Long surveyUserId);
}
