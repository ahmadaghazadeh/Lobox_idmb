package com.lobox.assignment.service;

import com.lobox.assignment.data.GenreTitleRatingDto;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TitleBasicService {

    void saveAll(MultipartFile is);
    List<String> findTitlesByDirectorWriterSameAndAlive();

    List<String> findTitlesByTwoActors(String actor1, String actor2);

    List<GenreTitleRatingDto> findBestTitlesByGenre(String genre);
}
