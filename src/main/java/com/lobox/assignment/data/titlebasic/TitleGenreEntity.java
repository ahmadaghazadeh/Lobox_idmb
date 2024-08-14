package com.lobox.assignment.data.titlebasic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "TitleGenre")
@IdClass(TitleGenreId.class)
public class TitleGenreEntity {
    @Id
    private Long titleId;
    @Id
    private Long genreId;

    @ManyToOne
    @JoinColumn(name = "titleId", insertable = false, updatable = false)
    private TitleBasicEntity titleBasic;

    @ManyToOne
    @JoinColumn(name = "genreId", insertable = false, updatable = false)
    private GenreEntity genre;
}


