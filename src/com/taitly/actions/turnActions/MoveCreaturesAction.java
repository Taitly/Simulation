package com.taitly.actions.turnActions;

import com.taitly.actions.Action;
import com.taitly.entities.Entity;
import com.taitly.entities.creatures.Creature;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoveCreaturesAction extends Action {

    @Override
    public void perform(World world) {
        List<Coordinate> coordinatesOfEntities = world.getAllEntitiesCoordinates();
        Set<Entity> movedEntities = new HashSet<>();
        for (Coordinate coordinate : coordinatesOfEntities) {
            Entity entity = world.getEntity(coordinate);
            if (entity instanceof Creature creature) {
                if (!movedEntities.contains(entity)) {
                    creature.makeMove(world, coordinate, creature.getTarget());
                }
                movedEntities.add(entity);
            }
        }
    }
}
