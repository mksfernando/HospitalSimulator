package org.hospital.component;

import org.hospital.APP_CONSTANTS;
import org.hospital.TestHelper;
import org.hospital.io.InputOutputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SimulationEngineTest {
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
    void start_Valid_Patients_No_Drugs() {
        // Given
        InputOutputUtil inputOutputUtil = mock(InputOutputUtil.class);
        SimulationEngine simulationEngine = new SimulationEngine(TestHelper.getPatients(), TestHelper.getDrugs(), inputOutputUtil);

        // When
        when(inputOutputUtil.getPatientDrugInput()).thenReturn(TestHelper.getPatientDrugArray_D_D()).thenReturn(null);
        doCallRealMethod().when(inputOutputUtil).printResult(any());

        // Then
        simulationEngine.start();

        assertTrue(outputStream.toString().contains("F:0,H:0,D:0,T:0,X:2"));
    }

    @Test
    void start_Valid_Patients_And_Drugs() {
        // Given
        InputOutputUtil inputOutputUtil = mock(InputOutputUtil.class);
        SimulationEngine simulationEngine = new SimulationEngine(TestHelper.getPatients(), TestHelper.getDrugs(), inputOutputUtil);

        // When
        when(inputOutputUtil.getPatientDrugInput()).thenReturn(TestHelper.getPatientDrugArray_T_F_D_An_I()).thenReturn(null);
        doCallRealMethod().when(inputOutputUtil).printResult(any());

        // Then
        simulationEngine.start();

        assertTrue(outputStream.toString().contains("F:2,H:0,D:1,T:0,X:0"));
    }

    @Test
    void start_Null_PatientDrugArray() {
        // Given
        InputOutputUtil inputOutputUtil = mock(InputOutputUtil.class);
        SimulationEngine simulationEngine = new SimulationEngine(TestHelper.getPatients(), TestHelper.getDrugs(), inputOutputUtil);

        // When
        when(inputOutputUtil.getPatientDrugInput()).thenReturn(null);
        doCallRealMethod().when(inputOutputUtil).showExitMessage();

        // Then
        simulationEngine.start();

        assertTrue(outputStream.toString().contains(APP_CONSTANTS.MSG_EXIT));
    }

    @Test
    void start_Invalid_Patient() {
        // Given
        InputOutputUtil inputOutputUtil = mock(InputOutputUtil.class);
        SimulationEngine simulationEngine = new SimulationEngine(TestHelper.getPatients(), TestHelper.getDrugs(), inputOutputUtil);

        // When
        when(inputOutputUtil.getPatientDrugInput()).thenReturn(TestHelper.getPatientDrugArray_Invalid_Patient()).thenReturn(null);
        doCallRealMethod().when(inputOutputUtil).showInvalidPatientMessage(any());

        // Then
        simulationEngine.start();

        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.MSG_INVALID_PATIENT, TestHelper.getPatientsStr())));
    }

    @Test
    void start_Invalid_Drugs() {
        // Given
        InputOutputUtil inputOutputUtil = mock(InputOutputUtil.class);
        SimulationEngine simulationEngine = new SimulationEngine(TestHelper.getPatients(), TestHelper.getDrugs(), inputOutputUtil);

        // When
        when(inputOutputUtil.getPatientDrugInput()).thenReturn(TestHelper.getPatientDrugArray_Invalid_Drug()).thenReturn(null);
        doCallRealMethod().when(inputOutputUtil).showInvalidDrugMessage(any());

        // Then
        simulationEngine.start();

        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.MSG_INVALID_DRUG, TestHelper.getDrugsStr())));
    }
}