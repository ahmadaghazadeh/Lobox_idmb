package com.lobox.assignment.data.titlebasic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "Genre")
public class GenreEntity {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<TitleGenreEntity> titleGenres;
}
