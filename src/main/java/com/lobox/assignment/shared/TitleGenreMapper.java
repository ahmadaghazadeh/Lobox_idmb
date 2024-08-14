package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlebasic.TitleGenreEntity;

public class TitleGenreMapper {
    public static TitleGenreEntity toTitleGenreEntity(long titleId, long genreId) {
        return TitleGenreEntity.builder()
                .genreId(genreId)
                .titleId(titleId)
                .build();
    }
}

