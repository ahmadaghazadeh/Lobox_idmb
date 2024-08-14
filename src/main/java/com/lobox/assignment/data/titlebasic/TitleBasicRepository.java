package com.lobox.assignment.data.titlebasic;

import com.lobox.assignment.data.GenreTitleRatingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface TitleBasicRepository extends JpaRepository<TitleBasicEntity,Long> {

    @Query("SELECT t.primaryTitle FROM TitleBasic t " +
            "JOIN TitleDirector td ON t.id = td.titleId " +
            "JOIN TitleWriter tw ON t.id = tw.titleId " +
            "JOIN NameBasic n ON td.nameId = n.id AND tw.nameId = n.id " +
            "WHERE n.deathYear =-1")
    List<String> findTitlesByDirectorWriterSameAndAlive();

    @Query("SELECT t.primaryTitle FROM TitleBasic t " +
            "JOIN TitleDirector td ON t.id = td.titleId " +
            "JOIN TitleWriter tw ON t.id = tw.titleId " +
            "JOIN NameBasic n1 ON td.nameId = n1.id " +
            "JOIN NameBasic n2 ON tw.nameId = n2.id " +
            "JOIN NameProfession np1 ON n1.id = np1.nameId " +
            "JOIN NameProfession np2 ON n2.id = np2.nameId " +
            "JOIN Profession p1 ON np1.professionId = p1.id " +
            "JOIN Profession p2 ON np2.professionId = p2.id " +
            "WHERE p1.name = 'actor' AND p2.name = 'actor' AND n1.primaryName = :actor1 AND n2.primaryName = :actor2")
    List<String> findTitlesByTwoActors(@Param("actor1") String actor1, @Param("actor2") String actor2);

    @Query("SELECT t.primaryTitle, t.startYear, tr.averageRating, tr.numVotes FROM TitleBasic t " +
            "JOIN TitleGenre tg ON t.id = tg.titleId " +
            "JOIN Genre g ON tg.genreId = g.id " +
            "JOIN TitleRating tr ON t.id = tr.titleId " +
            "WHERE g.name = :genre " +
            "GROUP BY t.startYear " +
            "ORDER BY tr.numVotes DESC, tr.averageRating DESC")
    List<GenreTitleRatingDto> findBestTitlesByGenre(@Param("genre") String genre);
}

