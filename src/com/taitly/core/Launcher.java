package com.taitly.core;

import com.taitly.world.World;

import java.util.Scanner;


public class Launcher {
    private final SystemMessage systemMessage = new SystemMessage();
    private final Scanner scanner = new Scanner(System.in);
    private static final String START = "s";
    private static final String PAUSE = "p";
    private static final String UNPAUSE = "u";
    private static final String NEXT_TURN = "n";
    private static final String EXIT = "e";
    private static final String WORLD_WIDTH = "Width: ";
    private static final String WORLD_HEIGHT = "Height: ";


    public void startLauncher() {
        systemMessage.printMessage(MessageType.WELCOME);

        Simulation simulation = new Simulation(setupWorldSize());
        simulation.showStartWindow();

        while (simulation.isSimulationRunning()) {
            switch (scanner.next().trim().toLowerCase()) {
                case START -> simulation.startSimulation();
                case NEXT_TURN -> simulation.nextTurn();
                case PAUSE -> simulation.pauseSimulation();
                case UNPAUSE -> simulation.unpauseSimulation();
                case EXIT -> exitGame(simulation);
                default -> systemMessage.printMessage(MessageType.ERROR_INPUT);
            }
        }
    }

    private World setupWorldSize() {
        systemMessage.printMessage(MessageType.MAP_SIZE_INPUT);
        int width = getPositiveInteger(WORLD_WIDTH);
        int height = getPositiveInteger(WORLD_HEIGHT);
        return new World(height,width);
    }

    private void exitGame(Simulation simulation) {
        simulation.stopSimulation();
        systemMessage.printMessage(MessageType.CLOSE);
        System.exit(0);
    }

    private int getPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            systemMessage.printMessage(MessageType.ERROR_NUMBER_INPUT);
        }
    }
}
