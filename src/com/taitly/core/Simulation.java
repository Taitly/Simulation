package com.taitly.core;

import com.taitly.actions.Action;
import com.taitly.actions.initActions.*;
import com.taitly.actions.turnActions.MoveCreaturesAction;
import com.taitly.world.WorldConsoleRenderer;
import com.taitly.world.World;

import java.util.ArrayList;
import java.util.List;


public class Simulation extends Thread {
    private int turnCount;
    private final World world;
    private final WorldConsoleRenderer renderer;
    private final SystemMessage systemMessage = new SystemMessage();
    private final Object lock = new Object();

    private boolean simulationPaused = false;
    private boolean simulationRunning = true;
    private boolean infiniteSimulationLoop = false;

    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(World world) {
        this.world = world;
        this.renderer = new WorldConsoleRenderer();
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }


    @Override
    public void run() {
        while (simulationRunning) {
            synchronized (lock) {
                while (simulationPaused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            infiniteSimulationLoop = true;
            nextTurn();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showStartWindow() {
        initActions();
        turnActions();

        for (Action action : initActions) {
            action.perform(world);
        }
        systemMessage.printMessage(MessageType.MAP_CREATION);
        renderer.render(world);
        systemMessage.printMessage(MessageType.MENU);
    }

    public void startSimulation() {
        try {
            this.start();
        } catch (Exception e) {
            stopSimulation();
            systemMessage.printMessage(MessageType.ERROR_CLOSE);
            throw new IllegalThreadStateException("Thread has already been started. A thread can be started at most once.");
        }
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(world);
        }
        turnCount++;
        systemMessage.printTurnCount(turnCount);
        renderer.render(world);

        if (!infiniteSimulationLoop && !simulationPaused) {
            systemMessage.printMessage(MessageType.NEXT_TURN);
        }
        if (simulationPaused) {
            systemMessage.printMessage(MessageType.PAUSE_SIMULATION);
        }
        if (infiniteSimulationLoop) {
            systemMessage.printMessage(MessageType.RUNNING_SIMULATION);
        }
    }

    public void pauseSimulation() {
        simulationPaused = true;
        infiniteSimulationLoop = false;
        nextTurn();
    }

    public void unpauseSimulation() {
        simulationPaused = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    public void stopSimulation() {
        simulationRunning = false;
    }

    public void initActions() {
        initActions.add(new CarrotSpawnAction(world));
        initActions.add(new RockSpawnAction(world));
        initActions.add(new TreeSpawnAction(world));
        initActions.add(new HerbivoreSpawnAction(world));
        initActions.add(new PredatorSpawnAction(world));
    }

    public void turnActions() {
        turnActions.add(new MoveCreaturesAction());
        turnActions.add(new CarrotSpawnAction(world));
        turnActions.add(new HerbivoreSpawnAction(world));
    }
}
