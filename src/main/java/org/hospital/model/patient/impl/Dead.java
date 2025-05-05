package org.hospital.model.patient.impl;

import org.hospital.APP_CONSTANTS;
import org.hospital.model.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class Dead implements Patient {
    @Override
    public String getState() {
        return APP_CONSTANTS.DEAD;
    }

    @Override
    public int order() {
        return 5;
    }
}
