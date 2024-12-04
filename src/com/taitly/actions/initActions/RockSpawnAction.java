package com.taitly.actions.initActions;

import com.taitly.entities.Entity;
import com.taitly.entities.obstacles.Rock;
import com.taitly.world.Coordinate;
import com.taitly.world.World;


public class RockSpawnAction extends SpawnAction{
    private static final int ROCK_PERCENTAGE = 10;

    public RockSpawnAction(World world) {
        super(world, ROCK_PERCENTAGE);
    }

    @Override
    protected Entity spawnEntity() {
        return new Rock();
    }
}
