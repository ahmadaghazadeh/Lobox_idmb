package com.lobox.assignment.data.namebasic;

import com.lobox.assignment.data.titlecrew.TitleDirectorEntity;
import com.lobox.assignment.data.titlecrew.TitleWriterEntity;
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
@Entity(name = "NameBasic")
@Table(indexes = {
        @Index(name = "birthYearIndex",columnList = "birthYear"),
        @Index(name = "primaryNameIndex",columnList = "primaryName")
})
public class NameBasicEntity {
    @Id
    private Long id;

    @Column(nullable = false,length =1000)
    private String primaryName ;

    private int birthYear ;

    private int deathYear ;

    @OneToMany(mappedBy = "nameBasic")
    private List<TitleDirectorEntity> directedTitles;

    @OneToMany(mappedBy = "nameBasic")
    private List<TitleWriterEntity> writtenTitles;

    @OneToMany(mappedBy = "nameBasic")
    private List<NameProfessionEntity> professions;
}
