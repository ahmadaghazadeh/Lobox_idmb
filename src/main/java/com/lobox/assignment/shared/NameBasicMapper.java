package com.lobox.assignment.shared;

import com.lobox.assignment.data.namebasic.NameBasicEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameBasicMapper {
    public static NameBasicDto toNameBasicDto(String line) throws ParseException {
        String[] values = line.split("\t");

        long id=Long.parseLong(values[0].replace("nm",""));
        var primaryName=values[1];

        var birthYearString=values[2];
        int birthYear=-1;
        if(!birthYearString.contains("\\N"))
        {
            birthYear=Integer.parseInt(birthYearString);
        }

        var deathYearString=values[3];
        int deathYear=-1;
        if(!deathYearString.contains("\\N"))
        {
            deathYear=Integer.parseInt(deathYearString);
        }


        var professionsString=values[4];
        List<String> professions=new ArrayList<>();
        if(!professionsString.contains("\\N"))
        {
            professions.addAll(Arrays.stream(professionsString.split(",")).toList());
        }

        return  NameBasicDto.builder()
                .id(id)
                .primaryName(primaryName)
                .birthYear(birthYear)
                .deathYear(deathYear)
                .professions(professions)
                .build();
    }

    public static NameBasicEntity toNameBasicEntity(NameBasicDto dto) throws ParseException {

        return  NameBasicEntity.builder()
                .id(dto.getId())
                .primaryName(dto.getPrimaryName())
                .birthYear(dto.getBirthYear())
                .deathYear(dto.getDeathYear())
                .build();
    }
}
