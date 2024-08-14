package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlebasic.TitleBasicEntity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TitleBasicMapper {
    public static TitleBasicDto toTitleBasicDto(String line) throws ParseException {
        String[] values = line.split("\t");
        long id=Long.parseLong(values[0].replace("tt",""));

        var primaryTitle= values[2];

        var startYearString=values[5];
        int startYear=-1;
        if(!startYearString.contains("\\N"))
        {
            startYear=Integer.parseInt(startYearString);
        }

        var genresString=values[8];
        List<String> genres=new ArrayList<>();
        if(!genresString.contains("\\N"))
        {
            genres.addAll(Arrays.stream(genresString.split(",")).toList());
        }

        return  TitleBasicDto.builder()
                .id(id)
                .primaryTitle(primaryTitle)
                .startYear(startYear)
                .genres(genres)
                .build();
    }

    public static TitleBasicEntity toTitleBasicEntity(TitleBasicDto dto) {
        return TitleBasicEntity.builder()
                .primaryTitle(dto.getPrimaryTitle())
                .id(dto.getId())
                .build();
    }
}

