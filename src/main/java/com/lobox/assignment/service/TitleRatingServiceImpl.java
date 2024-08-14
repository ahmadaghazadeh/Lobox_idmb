package com.lobox.assignment.service;

import com.lobox.assignment.data.titlerating.TitleRatingEntity;
import com.lobox.assignment.data.titlerating.TitleRatingRepository;
import com.lobox.assignment.exception.UnableParseFileException;
import com.lobox.assignment.shared.TitleRatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitleRatingServiceImpl implements TitleRatingService {

    private static final Logger logger = LoggerFactory.getLogger(TitleRatingServiceImpl.class);

    private final int batchCount = 20000;
    private final TitleRatingRepository titleRatingRepository;

    @Autowired
    public TitleRatingServiceImpl(TitleRatingRepository titleRatingRepository) {
        this.titleRatingRepository = titleRatingRepository;
    }

    @Override
    public void saveAll(MultipartFile is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is.getInputStream(), "UTF-8"))) {
            List<TitleRatingEntity> titleRatingEntities = new ArrayList<>();
            var allCount=0;
            String line;
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                try {
                    var titleRatingEntity = TitleRatingMapper.toTitleRatingEntity(line);
                    titleRatingEntities.add(titleRatingEntity);

                    if (titleRatingEntities.size() == batchCount) {
                        titleRatingRepository.saveAll(titleRatingEntities);
                        titleRatingEntities.clear();
                        allCount=allCount+batchCount;
                        logger.info("inserted count "+allCount);
                    }
                }
                catch (Exception ex){
                    logger.warn(ex.getMessage());
                }

            }
            try
            {
                if (!titleRatingEntities.isEmpty()) {
                    titleRatingRepository.saveAll(titleRatingEntities);
                }
            }
            catch (Exception ex){
                logger.warn(ex.getMessage());
            }


        } catch (IOException e) {
            throw new UnableParseFileException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
