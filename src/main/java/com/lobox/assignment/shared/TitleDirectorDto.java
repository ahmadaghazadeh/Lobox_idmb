package com.lobox.assignment.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TitleDirectorDto {
    private Long titleId;

    private Long NameId;
}
