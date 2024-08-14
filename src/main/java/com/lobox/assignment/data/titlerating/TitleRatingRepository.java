package com.lobox.assignment.data.titlerating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TitleRatingRepository extends JpaRepository<TitleRatingEntity, Long> {
}
