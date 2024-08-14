package com.lobox.assignment.data.titlecrew;

import com.lobox.assignment.data.namebasic.NameBasicEntity;
import com.lobox.assignment.data.titlebasic.TitleBasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "TitleDirector")
@IdClass(TitleDirectorId.class)
public class TitleDirectorEntity {
    @Id
    private Long titleId;
    @Id
    private Long nameId;

    @ManyToOne
    @JoinColumn(name = "titleId", insertable = false, updatable = false)
    private TitleBasicEntity titleBasic;

    @ManyToOne
    @JoinColumn(name = "nameId", insertable = false, updatable = false)
    private NameBasicEntity nameBasic;
}
