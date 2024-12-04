package com.taitly.actions.initActions;

import com.taitly.entities.Entity;
import com.taitly.entities.Carrot;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

import java.util.List;

public class CarrotSpawnAction extends SpawnAction {
    private static final int CARROT_PERCENTAGE = 8;

    public CarrotSpawnAction(World world) {
        super(world, CARROT_PERCENTAGE);
    }

    @Override
    public void perform(World world) {
        if (canSpawn(world)) {
            super.perform(world);
        }
    }

    private boolean canSpawn(World world) {
        int currentCarrotCount = 0;
        List<Entity> entities = world.getAllEntities();
        for (Entity entity : entities) {
            if (entity instanceof Carrot) {
                currentCarrotCount++;
            }
        }
        return currentCarrotCount < getMaxEntityQuantity() / 3;
    }

    @Override
    protected Entity spawnEntity() {
        return new Carrot();
    }
}
