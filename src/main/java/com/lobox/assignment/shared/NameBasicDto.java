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
public class NameBasicDto {
    private long id;
    private String primaryName ;
    private int birthYear ;
    private int deathYear ;
    private List<String> professions;
}

