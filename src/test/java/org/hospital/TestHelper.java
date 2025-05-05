package org.hospital;

import org.hospital.model.drug.Drug;
import org.hospital.model.drug.impl.Antibiotic;
import org.hospital.model.drug.impl.Aspirin;
import org.hospital.model.drug.impl.Insulin;
import org.hospital.model.drug.impl.Paracetamol;
import org.hospital.model.patient.Patient;
import org.hospital.model.patient.impl.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestHelper {
    public static Scanner getScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    public static List<Patient> getPatients() {
        return List.of(new Fever(), new Healthy(), new Diabetes(), new Tuberculosis(), new Dead());
    }

    public static List<Drug> getDrugs() {
        return List.of(new Aspirin(), new Antibiotic(), new Insulin(), new Paracetamol());
    }

    public static List<String>[] getPatientDrugArray_T_F_D_An_I() {
        List<String> patients = List.of("T", "F", "D");
        List<String> drugs = List.of("An", "I");
        return new List[]{patients, drugs};
    }

    public static List<String>[] getPatientDrugArray_D_D() {
        List<String> patients = List.of("D", "D");
        List<String> drugs = List.of();
        return new List[]{patients, drugs};
    }

    public static List<String>[] getPatientDrugArray_Invalid_Patient() {
        List<String> patients = List.of("T", "F", "#");
        List<String> drugs = List.of("An", "I");
        return new List[]{patients, drugs};
    }

    public static List<String>[] getPatientDrugArray_Invalid_Drug() {
        List<String> patients = List.of("T", "F", "D");
        List<String> drugs = List.of("An", "@");
        return new List[]{patients, drugs};
    }

    public static String getPatientsStr() {
        return getPatients().stream().map(Patient::getState).collect(Collectors.joining(APP_CONSTANTS.COMMA));
    }

    public static String getDrugsStr() {
        return getDrugs().stream().map(Drug::getCode).collect(Collectors.joining(APP_CONSTANTS.COMMA));
    }
}
