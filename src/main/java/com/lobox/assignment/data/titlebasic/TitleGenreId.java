package com.lobox.assignment.data.titlebasic;

import java.io.Serializable;

public class TitleGenreId implements Serializable {
    private Long titleId;
    private Long genreId;

    public TitleGenreId() {}
    public TitleGenreId(Long titleId, Long genreId) {
        this.titleId = titleId;
        this.genreId = genreId;
    }
}

