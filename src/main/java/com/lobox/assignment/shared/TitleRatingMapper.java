package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlerating.TitleRatingEntity;
import java.text.ParseException;

public class TitleRatingMapper {
    public static TitleRatingEntity toTitleRatingEntity(String line) throws ParseException {
        String[] values = line.split("\t");
        long titleId = Long.parseLong(values[0].replace("tt", ""));
        float averageRating = Float.parseFloat(values[1]);
        long numVotes = Long.parseLong(values[1]);
        return TitleRatingEntity
                .builder()
                .titleId(titleId)
                .averageRating(averageRating)
                .numVotes(numVotes)
                .build();
    }
}
