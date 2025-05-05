package org.hospital.model.patient.impl;

import org.hospital.APP_CONSTANTS;
import org.hospital.model.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class Tuberculosis implements Patient {
    @Override
    public String getState() {
        return APP_CONSTANTS.TUBERCULOSIS;
    }

    @Override
    public int order() {
        return 4;
    }
}
