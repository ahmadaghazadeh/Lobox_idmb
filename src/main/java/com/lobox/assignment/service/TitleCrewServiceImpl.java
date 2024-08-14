package com.lobox.assignment.service;

import com.lobox.assignment.data.titlecrew.TitleDirectorEntity;
import com.lobox.assignment.data.titlecrew.TitleDirectorRepository;
import com.lobox.assignment.data.titlecrew.TitleWriterEntity;
import com.lobox.assignment.data.titlecrew.TitleWriterRepository;
import com.lobox.assignment.exception.UnableParseFileException;
import com.lobox.assignment.shared.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitleCrewServiceImpl implements TitleCrewService {
    private static final Logger logger = LoggerFactory.getLogger(TitleCrewServiceImpl.class);
    private final int batchCount = 20000;
    private final TitleDirectorRepository titleDirectorRepository;
    private final TitleWriterRepository titleWriterRepository;

    @Autowired
    public TitleCrewServiceImpl(TitleDirectorRepository titleDirectorRepository, TitleWriterRepository titleWriterRepository) {
        this.titleDirectorRepository = titleDirectorRepository;
        this.titleWriterRepository = titleWriterRepository;
    }

    @Override
    public void saveAll(MultipartFile is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is.getInputStream(), "UTF-8"))) {
            List<TitleDirectorEntity> titleDirectorEntities = new ArrayList<>();
            List<TitleWriterEntity> titleWriterEntities = new ArrayList<>();
            var allCount=0;
            String line;
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                try {
                    var titleCrewDto = TitleCrewMapper.toTitleCrewDto(line);
                    var titleDirectories = titleCrewDto.getDirectors().stream().map(d -> TitleDirectorMapper.toTitleDirectorEntity(titleCrewDto.getTitleId(), d)).toList();
                    titleDirectorEntities.addAll(titleDirectories);

                    var titleWriters = titleCrewDto.getWriters().stream().map(d -> TitleWriterMapper.toTitleWriterEntity(titleCrewDto.getTitleId(), d)).toList();
                    titleWriterEntities.addAll(titleWriters);

                    if (titleDirectorEntities.size() == batchCount) {
                        titleDirectorRepository.saveAll(titleDirectorEntities);
                        titleWriterRepository.saveAll(titleWriterEntities);
                        titleDirectorEntities.clear();
                        titleWriterEntities.clear();
                        allCount=allCount+batchCount;
                        logger.info("inserted count "+allCount);
                    }
                }
                catch (Exception ex){
                    logger.warn(ex.getMessage());
                }
            }
            try {
                if (!titleDirectorEntities.isEmpty()) {
                    titleDirectorRepository.saveAll(titleDirectorEntities);
                }
                if (!titleWriterEntities.isEmpty()) {
                    titleWriterRepository.saveAll(titleWriterEntities);
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



