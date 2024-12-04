package com.taitly.core;

public class SystemMessage {
    private static final String WELCOME_MESSAGE =
                           """
                           ************************************************
                           *            Project "SIMULATION"              *
                           ************************************************
                           """;

    private static final String MENU =
                           """
                           \nControl buttons:
                           s - start simulation
                           n - take the next step
                           p - pause simulation
                           u - resume simulation
                           e - exit the program
                           """;
    private static final String RUNNING_SIMULATION_MENU =
            """
            \np - pause simulation
            e - exit the program
            """;

    private static final String PAUSE_SIMULATION_MENU =
            """
            \nu - resume simulation
            n - take the next step
            e - exit the program
            """;

    private static final String NEXT_TURN_SIMULATION_MENU =
            """
            \ns - start simulation
            n - take the next step
            e - exit the program
            """;

    private static final String MAP_CREATION_MESSAGE = "\nA new world has been created:";
    private static final String MAP_SIZE_INPUT_MESSAGE = "Enter the size of the world.";
    private static final String TURN_COUNT = "\nCurrent move: ";
    private static final String ERROR_INPUT_MESSAGE = "Incorrect input, try again!";
    private static final String ERROR_NUMBER_INPUT_MESSAGE = "Please enter a positive integer (greater than zero).";
    private static final String CLOSE_MESSAGE = "Thank you for using the program. Exiting now.";
    private static final String ERROR_CLOSE_MESSAGE = "An error has occurred. Exiting the program.";

    public void printMessage(MessageType type) {
        switch (type) {
            case WELCOME -> System.out.println(WELCOME_MESSAGE);
            case MENU -> System.out.println(MENU);
            case RUNNING_SIMULATION -> System.out.println(RUNNING_SIMULATION_MENU);
            case PAUSE_SIMULATION -> System.out.println(PAUSE_SIMULATION_MENU);
            case NEXT_TURN -> System.out.println(NEXT_TURN_SIMULATION_MENU);
            case MAP_CREATION -> System.out.println(MAP_CREATION_MESSAGE);
            case MAP_SIZE_INPUT -> System.out.println(MAP_SIZE_INPUT_MESSAGE);
            case ERROR_INPUT -> System.out.println(ERROR_INPUT_MESSAGE);
            case ERROR_NUMBER_INPUT -> System.out.println(ERROR_NUMBER_INPUT_MESSAGE);
            case CLOSE -> System.out.println(CLOSE_MESSAGE);
            case ERROR_CLOSE -> System.out.println(ERROR_CLOSE_MESSAGE);
        }
    }

    public void printTurnCount(int counter) {
        System.out.println(TURN_COUNT + counter);
    }
}
