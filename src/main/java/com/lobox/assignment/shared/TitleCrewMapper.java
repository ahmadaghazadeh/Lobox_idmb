package com.lobox.assignment.shared;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TitleCrewMapper {
    public static TitleCrewDto toTitleCrewDto(String line) throws ParseException {
        String[] values = line.split("\t");
        var directorsString = values[1];
        List<Long> directors = new ArrayList<>();
        if (!directorsString.contains("\\N")) {
            directors.addAll(
                    Arrays.stream(
                            directorsString.split(","))
                            .map(w -> w.replace("nm",""))
                            .map(Long::parseLong).toList()
            );
        }

        var writersString = values[2];
        List<Long> writers = new ArrayList<>();
        if (!writersString.contains("\\N")) {
            writers.addAll(
                    Arrays.stream(
                            writersString.split(","))
                            .map(w -> w.replace("nm",""))
                            .map(Long::parseLong).toList()
            );
        }

        long titleId=Long.parseLong(values[0].replace("tt",""));

        return TitleCrewDto.builder()
                .titleId(titleId)
                .Directors(directors)
                .Writers(writers)
                .build();
    }

}
