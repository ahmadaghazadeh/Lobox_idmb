package com.lobox.assignment.data.namebasic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface NameProfessionRepository extends JpaRepository<NameProfessionEntity, Long> {
}


