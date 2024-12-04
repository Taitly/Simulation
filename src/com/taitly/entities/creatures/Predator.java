package com.taitly.entities.creatures;

import com.taitly.entities.Entity;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

public class Predator extends Creature {
    private final static int SPEED = 2;
    private final static int HEALTH = 20;
    private static final int ATTACK_POWER = 10;
    private static final Class<? extends Entity> TARGET = Herbivore.class;

    public Predator() {
        super(SPEED, HEALTH, TARGET);
    }

    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }

    @Override
    public void eatTarget(World world, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        Creature victim = (Creature) world.getEntity(targetCoordinate);
        victim.setHp(victim.hp - ATTACK_POWER);

        if (victim.getHp() <= 0) {
            world.removeEntity(currentCoordinate);
            world.setEntity(targetCoordinate, this);
        }
    }
}
