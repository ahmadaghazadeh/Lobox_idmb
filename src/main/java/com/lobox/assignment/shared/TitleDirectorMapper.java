package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlecrew.TitleDirectorEntity;

public class TitleDirectorMapper {
    public static TitleDirectorEntity toTitleDirectorEntity(long titleId, long nameId) {
        return TitleDirectorEntity.builder()
                .nameId(nameId)
                .titleId(titleId)
                .build();
    }
}
