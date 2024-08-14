package com.lobox.assignment.shared;

import com.lobox.assignment.data.namebasic.ProfessionEntity;

public class ProfessionMapper {
    public static ProfessionEntity toProfession(long id, String name) {
        return ProfessionEntity.builder()
                .name(name)
                .id(id)
                .build();
    }
}
