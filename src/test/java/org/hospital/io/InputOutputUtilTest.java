package org.hospital.io;

import org.hospital.APP_CONSTANTS;
import org.hospital.TestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputUtilTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream actualOutput = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(actualOutput);
    }

    @Test
    void getPatientDrugInput_Valid_Inputs() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("T,F,D An,I"));

        // When
        List<String>[] patientDrugInput = inputOutputUtil.getPatientDrugInput();

        // Then
        assertEquals(2, patientDrugInput.length);
        assertEquals(3, patientDrugInput[0].size());
        assertEquals(2, patientDrugInput[1].size());
        assertTrue(patientDrugInput[0].containsAll(List.of("T", "F", "D")));
        assertTrue(patientDrugInput[1].containsAll(List.of("An", "I")));
    }

    @Test
    void getPatientDrugInput_InValid_Extra_Spaces() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("T,@,D  An,I"));

        // When
        try {
            inputOutputUtil.getPatientDrugInput();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.MSG_INVALID_INPUT));
        }
    }

    @Test
    void getPatientDrugInput_InValid_Extra_Comma() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("T,@,D  ,An,I"));

        // When
        try {
            inputOutputUtil.getPatientDrugInput();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.MSG_INVALID_INPUT));
        }
    }

    @Test
    void getPatientDrugInput_Empty_value() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(System.lineSeparator()));

        // When
        List<String>[] patientDrugInput = inputOutputUtil.getPatientDrugInput();

        // Then
        assertNull(patientDrugInput);
    }

    @Test
    void printResult() {
        // Given
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put("F", 2);
        resultMap.put("D", 0);
        resultMap.put("X", 1);
        resultMap.put("T", 0);

        // When
        InputOutputUtil inputOutputUtil = new InputOutputUtil();
        inputOutputUtil.printResult(resultMap);

        // Then
        assertEquals("F:2,D:0,X:1,T:0" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void showInvalidPatientMessage() {
        // Given
        String patientString = "F,H,D,T,X";

        // When
        InputOutputUtil inputOutputUtil = new InputOutputUtil();
        inputOutputUtil.showInvalidPatientMessage(patientString);

        // Then
        assertEquals(String.format(APP_CONSTANTS.MSG_INVALID_PATIENT + "%n", patientString), outputStream.toString());
    }

    @Test
    void showInvalidDrugMessage() {
        // Given
        String drugs = "As,An,I,P";

        // When
        InputOutputUtil inputOutputUtil = new InputOutputUtil();
        inputOutputUtil.showInvalidDrugMessage(drugs);

        // Then
        assertEquals(String.format(APP_CONSTANTS.MSG_INVALID_DRUG + "%n", drugs), outputStream.toString());
    }

    @Test
    void showExitMessage() {
        // When
        InputOutputUtil inputOutputUtil = new InputOutputUtil();
        inputOutputUtil.showExitMessage();

        // Then
        assertEquals(APP_CONSTANTS.MSG_EXIT + System.lineSeparator(), outputStream.toString());
    }
}