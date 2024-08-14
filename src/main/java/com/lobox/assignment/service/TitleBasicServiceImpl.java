package com.lobox.assignment.service;

import com.lobox.assignment.data.GenreTitleRatingDto;
import com.lobox.assignment.data.titlebasic.GenreEntity;
import com.lobox.assignment.data.titlebasic.GenreRepository;
import com.lobox.assignment.data.titlebasic.TitleBasicEntity;
import com.lobox.assignment.data.titlebasic.TitleBasicRepository;
import com.lobox.assignment.data.titlebasic.TitleGenreEntity;
import com.lobox.assignment.data.titlebasic.TitleGenreRepository;
import com.lobox.assignment.exception.UnableParseFileException;
import com.lobox.assignment.shared.GenreMapper;
import com.lobox.assignment.shared.TitleBasicMapper;
import com.lobox.assignment.shared.TitleGenreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

@Service
public class TitleBasicServiceImpl  implements TitleBasicService{

    private static final Logger logger = LoggerFactory.getLogger(TitleBasicServiceImpl.class);
    private final int batchCount=20000;
    private final TitleBasicRepository titleBasicRepository;
    private final GenreRepository genreRepository;
    private final TitleGenreRepository titleGenreRepository;
    @Autowired
    public TitleBasicServiceImpl(TitleBasicRepository titleBasicRepository,GenreRepository genreRepository,TitleGenreRepository titleGenreRepository){
        this.titleBasicRepository=titleBasicRepository;
        this.genreRepository=genreRepository;
        this.titleGenreRepository=titleGenreRepository;
    }

    @Override
    public void saveAll(MultipartFile is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is.getInputStream(), "UTF-8"))) {
            var allCount=0;
            List<TitleBasicEntity> titleBasicEntities=new ArrayList<>();
            List<TitleGenreEntity> titleGenreEntities=new ArrayList<>();
            HashMap<String,GenreEntity> genres = new HashMap<>();
            String line;
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                var titleBasicDto = TitleBasicMapper.toTitleBasicDto(line);
                titleBasicEntities.add(TitleBasicMapper.toTitleBasicEntity(titleBasicDto));
                try {
                    for (String genre:titleBasicDto.getGenres()) {
                        if(!genres.containsKey(genre))
                        {
                            long newId= genres.size()+1;
                            var genreEntity= GenreMapper.toGenreEntity(newId,genre);
                            genres.put(genre,genreEntity);
                            genreRepository.save(genreEntity);
                        }
                        GenreEntity genreEntity= genres.get(genre);
                        TitleGenreEntity titleGenreEntity=TitleGenreMapper.toTitleGenreEntity(titleBasicDto.getId(),genreEntity.getId());
                        titleGenreEntities.add(titleGenreEntity);
                    }

                    if(titleBasicEntities.size()==batchCount){
                        titleBasicRepository.saveAll(titleBasicEntities);
                        titleGenreRepository.saveAll(titleGenreEntities);
                        titleBasicEntities.clear();
                        titleGenreEntities.clear();
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
                if (!titleBasicEntities.isEmpty()) {
                    titleBasicRepository.saveAll(titleBasicEntities);
                }
                if (!titleGenreEntities.isEmpty()) {
                    titleGenreRepository.saveAll(titleGenreEntities);
                }
            }
            catch (Exception ex){
                  logger.warn(ex.getMessage());
            }


        } catch (IOException e) {
            throw new UnableParseFileException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new UnableParseFileException("The format is invalid: " + e.getMessage());
        }
    }

    @Override
    public List<String> findTitlesByDirectorWriterSameAndAlive() {
        return titleBasicRepository.findTitlesByDirectorWriterSameAndAlive();
    }

    @Override
    public List<String> findTitlesByTwoActors(String actor1, String actor2) {
        return titleBasicRepository.findTitlesByTwoActors(actor1, actor2);
    }

    @Override
    public List<GenreTitleRatingDto> findBestTitlesByGenre(String genre) {
        return titleBasicRepository.findBestTitlesByGenre(genre);
    }
}



