package com.lobox.assignment.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TitleWriterDto {
    private Long titleId;

    private Long NameId;
}


