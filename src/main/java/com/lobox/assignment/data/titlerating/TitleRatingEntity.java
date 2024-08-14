package com.lobox.assignment.data.titlerating;

import com.lobox.assignment.data.titlebasic.TitleBasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity(name = "TitleRating")
public class TitleRatingEntity {
    @Id
    private Long titleId;
    private float averageRating;
    private long numVotes;

    @OneToOne
    @JoinColumn(name = "titleId", insertable = false, updatable = false)
    private TitleBasicEntity titleBasic;
}
