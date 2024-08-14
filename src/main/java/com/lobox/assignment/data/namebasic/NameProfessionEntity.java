package com.lobox.assignment.data.namebasic;

import com.lobox.assignment.data.titlebasic.TitleGenreId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "NameProfession")
@IdClass(NameProfessionId.class)
public class NameProfessionEntity {
    @Id
    private Long nameId;
    @Id
    private Long professionId;


    @ManyToOne
    @JoinColumn(name = "nameId", insertable = false, updatable = false)
    private NameBasicEntity nameBasic;

    @ManyToOne
    @JoinColumn(name = "professionId", insertable = false, updatable = false)
    private ProfessionEntity profession;
}
