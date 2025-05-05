package org.hospital.component;

import org.hospital.APP_CONSTANTS;
import org.hospital.io.InputOutputUtil;
import org.hospital.model.Pair;
import org.hospital.model.drug.Drug;
import org.hospital.model.patient.Patient;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimulationEngine {
    private final InputOutputUtil inputOutputUtil;
    private final Map<String, List<Pair<List<String>, String>>> drugEffects = new HashMap<>();
    private final Map<String, Patient> patientMap = new LinkedHashMap<>();
    private final Map<String, Drug> drugsMap = new LinkedHashMap<>();
    private final String patientString;
    private final String drugString;

    public SimulationEngine(List<Patient> patients, List<Drug> drugs, InputOutputUtil inputOutputUtil) {
        this.inputOutputUtil = inputOutputUtil;
        patients.stream().sorted(Comparator.comparingInt(Patient::order)).forEach(p -> patientMap.put(p.getState(), p));
        drugs.forEach(d -> drugsMap.put(d.getCode(), d));
        patientString = String.join(APP_CONSTANTS.COMMA, patientMap.keySet());
        drugString = String.join(APP_CONSTANTS.COMMA, drugsMap.keySet());
        initDrugEffects();
    }

    public void start() {
        List<String>[] patientDrugArray;
        do {
            patientDrugArray = inputOutputUtil.getPatientDrugInput();
            if (patientDrugArray == null) {
                inputOutputUtil.showExitMessage();
            } else {
                List<String> patients = patientDrugArray[0];
                List<String> drugs = patientDrugArray[1];
                if (!validatePatients(patients)) {
                    inputOutputUtil.showInvalidPatientMessage(patientString);
                    continue;
                }
                if (!validateDrugs(drugs)) {
                    inputOutputUtil.showInvalidDrugMessage(drugString);
                    continue;
                }

                Map<String, Integer> resultMap = new LinkedHashMap<>();
                patientMap.values().forEach(patient -> resultMap.put(patient.getState(), 0));
                for (String patient : patients) {
                    String newState = dfs(patient, new HashSet<>(drugs));
                    if (newState != null)
                        resultMap.put(newState, resultMap.get(newState) + 1);
                }
                inputOutputUtil.printResult(resultMap);
            }
        } while (patientDrugArray != null);
    }

    private boolean validateDrugs(List<String> drugs) {
        return drugs.stream().allMatch(drugsMap::containsKey);
    }

    private boolean validatePatients(List<String> patients) {
        return patients.stream().allMatch(patientMap::containsKey);
    }

    private String dfs(String start, HashSet<String> drugs) {
        Queue<String> patientQueue = new ArrayDeque<>();
        Set<String> visitedDrugs = new HashSet<>();
        Set<String> visitedPatient = new HashSet<>();
        String currentStatus = start;
        patientQueue.offer(currentStatus);

        while (!patientQueue.isEmpty()) {
            currentStatus = patientQueue.poll();
            List<Pair<List<String>, String>> patientDrugEffects = drugEffects.get(currentStatus);
            for (Pair<List<String>, String> pair : patientDrugEffects) {
                if (drugs.isEmpty() && pair.key().isEmpty() && !visitedPatient.contains(pair.value())) {
                    visitedPatient.add(pair.value());
                    patientQueue.offer(pair.value());
                } else if (!visitedDrugs.containsAll(pair.key()) && !visitedPatient.contains(pair.value()) && drugs.containsAll(pair.key())) {
                    visitedDrugs.addAll(pair.key());
                    visitedPatient.add(pair.value());
                    patientQueue.offer(pair.value());
                }
            }
        }
        return currentStatus;
    }

    private void initDrugEffects() {
        patientMap.keySet().forEach(patient -> {
            drugEffects.computeIfAbsent(patient, v -> new ArrayList<>());

            // Rule -> Any Patient
            drugEffects.get(patient).add(new Pair<>(List.of(APP_CONSTANTS.PARACETAMOL, APP_CONSTANTS.ANTIBIOTIC), APP_CONSTANTS.DEAD));

            // Rule -> Patients without any drug
            if (APP_CONSTANTS.DIABETES.equalsIgnoreCase(patient))
                drugEffects.get(patient).add(new Pair<>(List.of(), APP_CONSTANTS.DEAD));
            else
                drugEffects.get(patient).add(new Pair<>(List.of(), patient));
        });

        // Rule -> Fever Patient
        drugEffects.get(APP_CONSTANTS.FEVER).add(new Pair<>(List.of(APP_CONSTANTS.ASPIRIN), APP_CONSTANTS.HEALTHY));
        drugEffects.get(APP_CONSTANTS.FEVER).add(new Pair<>(List.of(APP_CONSTANTS.PARACETAMOL), APP_CONSTANTS.HEALTHY));

        // Rule -> Tuberculosis Patient
        drugEffects.get(APP_CONSTANTS.TUBERCULOSIS).add(new Pair<>(List.of(APP_CONSTANTS.ANTIBIOTIC), APP_CONSTANTS.HEALTHY));

        // Rule -> Diabetes Patient
        drugEffects.get(APP_CONSTANTS.DIABETES).add(new Pair<>(List.of(APP_CONSTANTS.INSULIN), APP_CONSTANTS.DIABETES));

        // Rule -> Healthy Patient
        drugEffects.get(APP_CONSTANTS.HEALTHY).add(new Pair<>(List.of(APP_CONSTANTS.INSULIN, APP_CONSTANTS.ANTIBIOTIC), APP_CONSTANTS.FEVER));

        // Sort result-state by the size of the drug combination
        drugEffects.values().forEach(list ->
                list.sort(Comparator.comparingInt(pair -> ((Pair<List<String>, String>) pair).key().size()).reversed())
        );
    }
}
