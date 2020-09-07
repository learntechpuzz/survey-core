package com.visiblestarsksa.survey.repository;

import com.visiblestarsksa.survey.domain.enums.ERole;
import com.visiblestarsksa.survey.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
