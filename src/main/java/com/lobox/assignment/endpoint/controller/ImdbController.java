package com.lobox.assignment.endpoint.controller;

import com.lobox.assignment.data.GenreTitleRatingDto;
import com.lobox.assignment.filter.RequestCountFilter;
import com.lobox.assignment.service.TitleBasicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imdb")
public class ImdbController {
    private final TitleBasicService titleBasicService;

    public ImdbController(TitleBasicService titleBasicService) {
        this.titleBasicService = titleBasicService;
    }

    @GetMapping("/request-count")
    public int getRequestCount() {
        return RequestCountFilter.getRequestCount();
    }

    @GetMapping("/director-writer-same-alive")
    public List<String> getDirectorWriterSameAndAlive() {
        return titleBasicService.findTitlesByDirectorWriterSameAndAlive();
    }

    @GetMapping("/titles-by-actors")
    public List<String> getTitlesByTwoActors(@RequestParam String actor1, @RequestParam String actor2) {
        return titleBasicService.findTitlesByTwoActors(actor1, actor2);
    }

    @GetMapping("/best-titles-by-genre")
    public List<GenreTitleRatingDto> getBestTitlesByGenre(@RequestParam String genre) {
        return titleBasicService.findBestTitlesByGenre(genre);
    }
}
