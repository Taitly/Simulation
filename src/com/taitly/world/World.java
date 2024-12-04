package com.taitly.world;

import com.taitly.entities.Entity;

import java.util.*;

public class World {
    private final int rows;
    private final int columns;
    Map<Coordinate, Entity> world = new HashMap<>();

    public World(int rows, int columns) {
        checkWorldSize(rows, columns);
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isCoordinateEmpty(Coordinate coordinate) {
        return !world.containsKey(coordinate);
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        if(entity == null) {
            throw new IllegalArgumentException("The entity does not exist");
        }
        if(!isCoordinatesWithinWorldBounds(coordinate)) {
            throw new IllegalArgumentException("You're trying to add an entity outside of the created world");
        }
        world.put(coordinate, entity);
    }

    public void removeEntity(Coordinate coordinate) {
        if (isCoordinateEmpty(coordinate)) {
            throw new NoSuchElementException("There is no entity at these coordinates");
        }
        world.remove(coordinate);
    }

    public Entity getEntity(Coordinate coordinate) {
        if(isCoordinateEmpty(coordinate)) {
            throw new NoSuchElementException("There is no entity at these coordinates");
        }
        return world.get(coordinate);
    }

    public Coordinate getRandomEmptyCoordinate() {
        Random random = new Random();
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(random.nextInt(rows), random.nextInt(columns));
        } while (!isCoordinateEmpty(coordinate));
        return coordinate;
    }

    public List<Entity> getAllEntities() {
        List<Entity> entities = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> entry : world.entrySet()) {
            entities.add(entry.getValue());
        }
        return entities;
    }

    public List<Coordinate> getAllEntitiesCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> entry : world.entrySet()) {
            coordinates.add(entry.getKey());
        }
        return coordinates;
    }

    public int getSize() {
        return rows * columns;
    }

    private void checkWorldSize(int rows, int columns) {
        if (rows > 0 && columns > 0) {
            return;
        }
        throw new IllegalArgumentException("Incorrect value. The size of the sides must be greater than zero!");
    }

    private boolean isCoordinatesWithinWorldBounds(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("The coordinate cannot be equal to null");
        }
        return (coordinate.getRow() >= 0 && coordinate.getRow() < getRows()) &&
                (coordinate.getColumn() >= 0 && coordinate.getColumn() < getColumns());
    }
}

