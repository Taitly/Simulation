package com.taitly.actions.initActions;

import com.taitly.entities.Entity;
import com.taitly.entities.obstacles.Tree;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

public class TreeSpawnAction extends SpawnAction {
    private static final int TREE_PERCENTAGE = 10;

    public TreeSpawnAction(World world) {
        super(world, TREE_PERCENTAGE);
    }

    @Override
    protected Entity spawnEntity() {
        return new Tree();
    }
}
