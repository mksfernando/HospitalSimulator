package org.hospital.io;

import org.hospital.APP_CONSTANTS;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InputOutputUtil {
    private final Scanner scanner;

    public InputOutputUtil() {
        this.scanner = new Scanner(System.in);
    }

    public InputOutputUtil(Scanner scanner) {
        this.scanner = scanner;
    }


    public List<String>[] getPatientDrugInput() {
        List<String>[] patientDrugArray = null;
        do {
            System.out.println();
            System.out.print(APP_CONSTANTS.MSG_INPUT);
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty())
                return null;
            String[] inputs = userInput.split(APP_CONSTANTS.SPACE);
            if (inputs.length > 0 && inputs.length <= 2) {
                patientDrugArray = new ArrayList[2];
                patientDrugArray[0] = Arrays.stream(inputs[0].split(APP_CONSTANTS.COMMA))
                        .collect(Collectors.toList());
                patientDrugArray[1] = inputs.length == 1 ?
                        new ArrayList<>() : Arrays.stream(inputs[1].split(APP_CONSTANTS.COMMA))
                        .collect(Collectors.toList());
            } else {
                System.out.println(APP_CONSTANTS.MSG_INVALID_INPUT);
            }
        } while (patientDrugArray == null);
        return patientDrugArray;
    }

    public void printResult(Map<String, Integer> resultMap) {
        System.out.println(
                resultMap.entrySet().stream()
                        .map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(APP_CONSTANTS.COMMA))
        );
    }

    public void showInvalidPatientMessage(String patientString) {
        System.out.printf(APP_CONSTANTS.MSG_INVALID_PATIENT + "%n", patientString);
    }

    public void showInvalidDrugMessage(String drugString) {
        System.out.printf(APP_CONSTANTS.MSG_INVALID_DRUG + "%n", drugString);
    }

    public void showExitMessage() {
        System.out.println(APP_CONSTANTS.MSG_EXIT);
    }
}
