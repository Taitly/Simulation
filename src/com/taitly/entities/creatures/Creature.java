package com.taitly.entities.creatures;

import com.taitly.entities.Entity;
import com.taitly.pathfinder.PathFinder;
import com.taitly.world.Coordinate;
import com.taitly.world.World;
import com.taitly.pathfinder.BfsPathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    protected int speed;
    protected int hp;
    protected Class<? extends Entity> target;

    public Creature(int speed, int hp, Class<? extends Entity> target) {
        this.speed = speed;
        this.hp = hp;
        this.target = target;
    }

    public int getHp() {
        return hp;
    }

    public Class<? extends Entity> getTarget() {
        return target;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public abstract boolean isTarget(Entity entity);
    public abstract void eatTarget(World world, Coordinate currentCoordinate, Coordinate targetCoordinate);

    public void makeMove(World world, Coordinate currentCoordinate, Class<? extends Entity> target) {
        PathFinder bfsPathFinder = new BfsPathFinder();

        List<Coordinate> pathToTarget = bfsPathFinder.findPath(world, currentCoordinate, target);
        int maxSteps = Math.min(speed, pathToTarget.size());

        if (pathToTarget.size() <=2) {
            maxSteps = 1;
        }

        Coordinate nextCoordinateToMove = pathToTarget.get(maxSteps - 1);
        Entity entityToMove = world.getEntity(currentCoordinate);

        if(!world.isCoordinateEmpty(nextCoordinateToMove) && isTarget(world.getEntity(nextCoordinateToMove))) {
            if(entityToMove instanceof Creature creature) {
                creature.eatTarget(world, currentCoordinate, nextCoordinateToMove);
            }
        } else {
            world.removeEntity(currentCoordinate);
            world.setEntity(nextCoordinateToMove, entityToMove);
        }
    }
}
