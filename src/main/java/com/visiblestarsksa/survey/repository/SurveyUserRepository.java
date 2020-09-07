package com.visiblestarsksa.survey.repository;

import com.visiblestarsksa.survey.model.SurveyUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, Long> {

    List<SurveyUser> findBySurveyId(Long surveyId);

    Page<SurveyUser> findAll(Pageable pageable);

    Page<SurveyUser> findByMaskParty(Pageable pageable, Long maskParty);
}
