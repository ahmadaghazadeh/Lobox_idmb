package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlebasic.GenreEntity;

public class GenreMapper {
    public static GenreEntity toGenreEntity(long id,String name) {
        return GenreEntity.builder()
                .name(name)
                .id(id)
                .build();
    }
}

