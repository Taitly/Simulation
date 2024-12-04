package com.taitly.actions.initActions;

import com.taitly.entities.Entity;
import com.taitly.entities.creatures.Herbivore;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

import java.util.List;

public class HerbivoreSpawnAction extends SpawnAction{
    private static final int HERBIVORE_PERCENTAGE = 2;

    public HerbivoreSpawnAction(World world) {
        super(world, HERBIVORE_PERCENTAGE);
    }

    @Override
    public void perform(World world) {
        if (canSpawn(world)) {
            super.perform(world);
        }
    }

    private boolean canSpawn(World world) {
        int currentHerbivoreCount = 0;
        List<Entity> entities = world.getAllEntities();
        for (Entity entity : entities) {
            if (entity instanceof Herbivore) {
                currentHerbivoreCount++;
            }
        }
        return currentHerbivoreCount == 0;
    }

    @Override
    protected Entity spawnEntity() {
        return new Herbivore();
    }
}
