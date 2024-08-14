package com.lobox.assignment.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TitleGenreDto {
    private Long titleId;

    private Long genreId;
}
