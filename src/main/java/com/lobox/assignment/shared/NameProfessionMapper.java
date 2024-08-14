package com.lobox.assignment.shared;

import com.lobox.assignment.data.namebasic.NameProfessionEntity;

public class NameProfessionMapper {
    public static NameProfessionEntity toNameProfessionEntity(long nameId, long professionId) {
        return NameProfessionEntity.builder()
                .professionId(professionId)
                .nameId(nameId)
                .build();
    }
}
