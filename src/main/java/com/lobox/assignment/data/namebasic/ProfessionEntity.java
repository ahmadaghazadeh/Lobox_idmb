package com.lobox.assignment.data.namebasic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "Profession")
public class ProfessionEntity {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "profession")
    private List<NameProfessionEntity> nameProfessions;
}
