package com.lobox.assignment.data.titlecrew;

import java.io.Serializable;

public class TitleDirectorId implements Serializable {
    private Long titleId;
    private Long nameId;

    public TitleDirectorId() {}
    public TitleDirectorId(Long titleId, Long nameId) {
        this.titleId = titleId;
        this.nameId = nameId;
    }
}
