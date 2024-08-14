package com.lobox.assignment.shared;

import com.lobox.assignment.data.titlecrew.TitleWriterEntity;

public class TitleWriterMapper {
    public static TitleWriterEntity toTitleWriterEntity(long titleId, long nameId) {
        return TitleWriterEntity.builder()
                .nameId(nameId)
                .titleId(titleId)
                .build();
    }
}
