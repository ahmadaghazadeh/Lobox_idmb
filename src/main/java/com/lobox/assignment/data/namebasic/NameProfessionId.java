package com.lobox.assignment.data.namebasic;

import java.io.Serializable;

public class NameProfessionId implements Serializable {
    private Long nameId;
    private Long professionId;
    public NameProfessionId() {}
    public NameProfessionId(Long nameId, Long professionId) {
        this.nameId = nameId;
        this.professionId = professionId;
    }
}
