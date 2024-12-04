package com.taitly.pathfinder;

import com.taitly.entities.Entity;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

import java.util.List;

public interface PathFinder {
    public List<Coordinate> findPath(World world, Coordinate startCoordinate, Class<? extends Entity> target);
}
