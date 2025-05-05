package org.hospital.model.patient.impl;

import org.hospital.APP_CONSTANTS;
import org.hospital.model.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class Diabetes implements Patient {
    @Override
    public String getState() {
        return APP_CONSTANTS.DIABETES;
    }

    @Override
    public int order() {
        return 3;
    }
}
