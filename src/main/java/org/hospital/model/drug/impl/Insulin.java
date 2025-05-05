package org.hospital.model.drug.impl;

import org.hospital.APP_CONSTANTS;
import org.hospital.model.drug.Drug;
import org.springframework.stereotype.Component;

@Component
public class Insulin implements Drug {
    @Override
    public String getCode() {
        return APP_CONSTANTS.INSULIN;
    }
}
