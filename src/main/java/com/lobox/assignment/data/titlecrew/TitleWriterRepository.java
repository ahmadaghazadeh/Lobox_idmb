package com.lobox.assignment.data.titlecrew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TitleWriterRepository extends JpaRepository<TitleWriterEntity,Long> {
}
