package com.lobox.assignment.data.titlebasic;

import com.lobox.assignment.data.titlecrew.TitleDirectorEntity;
import com.lobox.assignment.data.titlecrew.TitleWriterEntity;
import com.lobox.assignment.data.titlerating.TitleRatingEntity;
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
@Entity(name = "TitleBasic")
public class TitleBasicEntity {
    @Id
    private Long id;

    @Column(nullable = false,length =1000)
    private String primaryTitle;

    private int startYear;

    @OneToMany(mappedBy = "titleBasic")
    private List<TitleDirectorEntity> directors;

    @OneToMany(mappedBy = "titleBasic")
    private List<TitleWriterEntity> writers;

    @OneToMany(mappedBy = "titleBasic")
    private List<TitleGenreEntity> genres;

    @OneToOne(mappedBy = "titleBasic")
    private TitleRatingEntity rating;
}
