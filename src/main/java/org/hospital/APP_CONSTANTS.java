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
    String MSG_INPUT = "Enter patients' Health Status Codes and Drug Codes in [S1,S2,S3 D1,D2,D3] format or press Enter to Exit: ";
    String MSG_INVALID_INPUT = "Invalid input. Please enter patients' Health Status Codes and Drug Codes in [S1,S2,S3 D1,D2,D3] format.";
    String MSG_INVALID_PATIENT = "Invalid patients' Health Status Code. Please enter already existing codes [%s].";
    String MSG_INVALID_DRUG = "Invalid Drug Code. Please enter already existing codes [%s].";
}
