package com.visiblestarsksa.survey.repository;

import com.visiblestarsksa.survey.domain.ISurvey;
import com.visiblestarsksa.survey.model.Survey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query(
            value =
                    "SELECT s.id as surveyId,title_en as title,category as category,to_char(s.expire_on, 'DD Mon YYYY HH:MI am') as expireOn,count(distinct sq.id) as totalQuestions,count(distinct su.id) as totalUsers FROM surveys s INNER JOIN survey_questions sq ON sq.survey_id = s.id INNER JOIN survey_users su ON su.survey_id = s.id GROUP BY s.id ORDER BY s.created_on desc",
            countQuery =
                    "SELECT count(*) FROM surveys s INNER JOIN survey_questions sq ON sq.survey_id = s.id INNER JOIN survey_users su ON su.survey_id = s.id GROUP BY s.id",
            nativeQuery = true)
    Page<ISurvey> listAllSurey(Pageable pageable);

    @Query(
            value =
                    "SELECT s.id as surveyId, title_en as title,category as category,to_char(s.expire_on, 'DD Mon YYYY HH:MM') as expireOn,count(distinct sq.id) as totalQuestions,count(distinct su.id) as totalUsers FROM surveys s INNER JOIN survey_questions sq ON sq.survey_id = s.id INNER JOIN survey_users su ON su.survey_id = s.id WHERE s.title_en like %:title_en% GROUP BY s.id ORDER BY s.created_on desc",
            countQuery =
                    "SELECT COUNT(*) FROM surveys s INNER JOIN survey_questions sq ON sq.survey_id = s.id INNER JOIN survey_users su ON su.survey_id = s.id WHERE s.title_en like %:title_en% GROUP BY s.id",
            nativeQuery = true)
    Page<ISurvey> listAllSureyByTitle(@Param("title_en") String title_en, Pageable pageable);
}
