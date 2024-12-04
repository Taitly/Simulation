package com.taitly.entities.creatures;

import com.taitly.entities.Carrot;
import com.taitly.entities.Entity;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

public class Herbivore extends Creature {
    private static final int SPEED = 1;
    private static final int HEALTH = 20;
    private static final Class<? extends Entity> TARGET = Carrot.class;

    public Herbivore() {
        super(SPEED, HEALTH, TARGET);
    }


    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Carrot;
    }

    @Override
    public void eatTarget(World world, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        world.removeEntity(currentCoordinate);
        world.setEntity(targetCoordinate, this);
    }
}
