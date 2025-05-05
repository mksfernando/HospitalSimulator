package org.hospital;

public interface APP_CONSTANTS {
    // Patients' Status Codes
    String FEVER = "F";
    String HEALTHY = "H";
    String DIABETES = "D";
    String TUBERCULOSIS = "T";
    String DEAD = "X";

    // Drugs
    String ASPIRIN = "As";
    String ANTIBIOTIC = "An";
    String INSULIN = "I";
    String PARACETAMOL = "P";

    // Literals
    String SPACE = " ";
    String COMMA = ",";

    // Messages
    String MSG_EXIT = "Thank you for using Hospital Simulator. Bye!";
    String MSG_INPUT = "Enter patients' health status codes and drug codes in [S1,S2,S3 D1,D2,D3] format: ";
    String MSG_INVALID_INPUT = "Invalid input. Please enter patients' health status codes and drug codes in [S1,S2,S3 D1,D2,D3] format.";
    String MSG_INVALID_PATIENT = "Invalid patients' health status code. Please enter already existing codes [%s].";
    String MSG_INVALID_DRUG = "Invalid Drug. Please enter already existing Drug [%s].";
}
