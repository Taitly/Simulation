package com.taitly.actions.initActions;

import com.taitly.actions.Action;
import com.taitly.entities.Entity;
import com.taitly.world.Coordinate;
import com.taitly.world.World;


public abstract class SpawnAction extends Action {
    private final int maxEntityQuantity;

    public SpawnAction(World world, int percent) {
        this.maxEntityQuantity = calculateMaxEntityQuantity(world, percent);
    }

    @Override
    public void perform(World world) {
        int currentEntityQuantity = 0;

        while (currentEntityQuantity < maxEntityQuantity) {
            Coordinate coordinate = world.getRandomEmptyCoordinate();
            world.setEntity(coordinate, spawnEntity());
            currentEntityQuantity++;
        }
    }

    protected int getMaxEntityQuantity() {
        return maxEntityQuantity;
    }

    private int calculateMaxEntityQuantity(World world, int percent) {
        return world.getSize() * percent / 100;
    }

    protected abstract Entity spawnEntity();
}