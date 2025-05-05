package org.hospital;

import org.hospital.component.SimulationEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    private final SimulationEngine simulationEngine;

    public Main(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        simulationEngine.start();
    }
}