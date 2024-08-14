package com.lobox.assignment.data.titlebasic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TitleGenreRepository extends JpaRepository<TitleGenreEntity,Long> {
}
