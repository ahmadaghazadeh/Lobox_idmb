package com.lobox.assignment.service;

import com.lobox.assignment.data.namebasic.*;
import com.lobox.assignment.exception.UnableParseFileException;
import com.lobox.assignment.shared.NameBasicMapper;
import com.lobox.assignment.shared.NameProfessionMapper;
import com.lobox.assignment.shared.ProfessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NameBasicServiceImpl implements NameBasicService {

    private static final Logger logger = LoggerFactory.getLogger(NameBasicServiceImpl.class);
    private final int batchCount=20000;
    private final NameBasicRepository nameBasicRepository;
    private final NameProfessionRepository nameProfessionRepository;
    private final ProfessionRepository professionRepository;
    @Autowired
    public NameBasicServiceImpl(NameBasicRepository nameBasicRepository,
                                NameProfessionRepository nameProfessionRepository,
                                ProfessionRepository professionRepository){
        this.nameBasicRepository=nameBasicRepository;
        this.nameProfessionRepository = nameProfessionRepository;
        this.professionRepository = professionRepository;
    }

    @Override
    public void saveAll(MultipartFile is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is.getInputStream(), "UTF-8"))) {
            List<NameBasicEntity> basicEntities=new ArrayList<>();
            List<NameProfessionEntity> nameProfessionEntities=new ArrayList<>();
            HashMap<String, ProfessionEntity> professions = new HashMap<>();
            var allCount=0;
            String line;
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                try {
                    var nameBasicDto = NameBasicMapper.toNameBasicDto(line);
                    basicEntities.add(NameBasicMapper.toNameBasicEntity(nameBasicDto));
                    for (String profession:nameBasicDto.getProfessions()) {
                        if(!professions.containsKey(profession))
                        {
                            long newId= professions.size()+1;
                            var professionEntity= ProfessionMapper.toProfession(newId,profession);
                            professions.put(profession,professionEntity);
                            professionRepository.save(professionEntity);
                        }
                        ProfessionEntity professionEntity= professions.get(profession);
                        NameProfessionEntity nameProfessionEntity= NameProfessionMapper.toNameProfessionEntity(nameBasicDto.getId(),professionEntity.getId());
                        nameProfessionEntities.add(nameProfessionEntity);
                    }

                    if(basicEntities.size()==batchCount){
                        nameBasicRepository.saveAll(basicEntities);
                        nameProfessionRepository.saveAll(nameProfessionEntities);
                        basicEntities.clear();
                        nameProfessionEntities.clear();
                        allCount=allCount+batchCount;
                        logger.info("inserted count "+allCount);
                    }
                }
                catch (Exception ex){
                    logger.warn(ex.getMessage());
                }
            }
            if(!basicEntities.isEmpty()){
                nameBasicRepository.saveAll(basicEntities);
            }
            if(!nameProfessionEntities.isEmpty()){
                nameProfessionRepository.saveAll(nameProfessionEntities);
            }
        } catch (IOException e) {
            throw new UnableParseFileException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
