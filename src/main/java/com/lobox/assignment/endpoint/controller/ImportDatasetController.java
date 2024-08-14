package com.lobox.assignment.endpoint.controller;


import com.lobox.assignment.endpoint.model.ResponseMessage;
import com.lobox.assignment.filter.RequestCountFilter;
import com.lobox.assignment.service.NameBasicService;
import com.lobox.assignment.service.TitleBasicService;
import com.lobox.assignment.service.TitleCrewService;
import com.lobox.assignment.service.TitleRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import-dataset")
public class ImportDatasetController {


    private final NameBasicService nameBasicService;
    private final TitleBasicService titleBasicService;
    private final TitleCrewService titleCrewService;
    private final TitleRatingService titleRatingService;
    public ImportDatasetController(NameBasicService nameBasicService, TitleBasicService titleBasicService, TitleCrewService titleCrewService, TitleRatingService titleRatingService){
        this.nameBasicService = nameBasicService;
        this.titleBasicService = titleBasicService;
        this.titleCrewService = titleCrewService;
        this.titleRatingService = titleRatingService;
    }

    @PostMapping(value = "step-1-name-basic",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> uploadNameBasic(@RequestParam("file") MultipartFile file) {
        try {
            nameBasicService.saveAll(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()+"Please upload a csv file!"));
        }
        return ResponseEntity.ok(new ResponseMessage("All Name Basic have imported."));
    }

    @PostMapping(value = "step-2-title-basic",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> uploadTitleBasic(@RequestParam("file") MultipartFile file) {
        try {
            titleBasicService.saveAll(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()+"Please upload a csv file!"));
        }
        return ResponseEntity.ok(new ResponseMessage("All Title Basic have imported."));
    }

    @PostMapping(value = "step-3-title-crew",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> uploadTitleCrew(@RequestParam("file") MultipartFile file) {
        try {
            titleCrewService.saveAll(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()+"Please upload a csv file!"));
        }
        return ResponseEntity.ok(new ResponseMessage("All Title Crews have imported."));
    }

    @PostMapping(value = "step-4-title-rating",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> uploadTitleRating(@RequestParam("file") MultipartFile file) {
        try {
            titleRatingService.saveAll(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()+"Please upload a csv file!"));
        }
        return ResponseEntity.ok(new ResponseMessage("All Title Rating have imported."));
    }




}
