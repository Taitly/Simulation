package com.taitly.actions.initActions;

import com.taitly.entities.Entity;
import com.taitly.entities.creatures.Predator;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

public class PredatorSpawnAction extends SpawnAction {
    private static final int PREDATOR_PERCENTAGE = 1;

    public PredatorSpawnAction(World world) {
        super(world, PREDATOR_PERCENTAGE);
    }

    @Override
    protected Entity spawnEntity() {
        return new Predator();
    }
}
