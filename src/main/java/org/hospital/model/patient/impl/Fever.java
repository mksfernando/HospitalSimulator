package org.hospital.model.patient.impl;

import org.hospital.APP_CONSTANTS;
import org.hospital.model.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class Fever implements Patient {
    @Override
    public String getState() {
        return APP_CONSTANTS.FEVER;
    }

    @Override
    public int order() {
        return 1;
    }
}
