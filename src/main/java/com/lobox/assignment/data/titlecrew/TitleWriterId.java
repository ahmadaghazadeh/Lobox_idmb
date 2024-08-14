package com.lobox.assignment.data.titlecrew;

import java.io.Serializable;

public class TitleWriterId implements Serializable {
    private Long titleId;
    private Long nameId;

    public TitleWriterId() {}
    public TitleWriterId(Long titleId, Long nameId) {
        this.titleId = titleId;
        this.nameId = nameId;
    }
}

