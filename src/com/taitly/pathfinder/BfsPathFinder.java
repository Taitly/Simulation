package com.taitly.pathfinder;

import com.taitly.entities.Entity;
import com.taitly.world.Coordinate;
import com.taitly.world.World;

import java.util.*;

public class BfsPathFinder implements PathFinder {

    @Override
    public List<Coordinate> findPath(World world, Coordinate startCoordinate, Class<? extends Entity> target) {
        Queue<Coordinate> coordinatesToCheck = new LinkedList<>();
        List<Coordinate> visitedCoordinates = new ArrayList<>();

        Map<Coordinate, Coordinate> route = new HashMap<>();

        coordinatesToCheck.add(startCoordinate);

        while (!coordinatesToCheck.isEmpty()) {
            Coordinate current = coordinatesToCheck.poll();
            visitedCoordinates.add(current);

            List<Coordinate> neighbours = checkNeighbourCoordinatesToMove(current, world);

            for (Coordinate neighbourCoordinate : neighbours) {
                if (isCoordinateAvailableForMoving(visitedCoordinates, coordinatesToCheck, world, neighbourCoordinate, target)) {
                    coordinatesToCheck.add(neighbourCoordinate);
                    route.put(neighbourCoordinate, current);
                }
            }

            if (!world.isCoordinateEmpty(current) && target.isInstance(world.getEntity(current))) {
                return backTracePath(route, current, startCoordinate);
            }
        }

        return stepToRandomNeighbourCoordinate(startCoordinate, world);
    }

    private List<Coordinate> backTracePath(Map<Coordinate, Coordinate> route, Coordinate target, Coordinate start) {
        List<Coordinate> path = new ArrayList<>();
        path.add(target);
        Coordinate previousCoordinate = target;

        while (path.get(path.size() - 1) != start) {
            previousCoordinate = route.get(previousCoordinate);
            path.add(previousCoordinate);

        }

        Collections.reverse(path);
        path.remove(0);

        return path;
    }

    private List<Coordinate> checkNeighbourCoordinatesToMove(Coordinate coordinate, World world) {
        List<Coordinate> availableCoordinatesToMove = new ArrayList<>();
        int[][] neighboursCoordinates = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        int mapRowBorder = world.getRows();
        int mapColumnsBorder = world.getColumns();

        for (int[] shiftCoordinate : neighboursCoordinates) {
            int neighbourRow = coordinate.getRow() + shiftCoordinate[0];
            int neighbourColumn = coordinate.getColumn() + shiftCoordinate[1];

            if ((neighbourRow >= 0 && neighbourRow < mapRowBorder) &&
                    (neighbourColumn >= 0 && neighbourColumn < mapColumnsBorder)) {
                availableCoordinatesToMove.add(new Coordinate(neighbourRow, neighbourColumn));
            }
        }

        return availableCoordinatesToMove;
    }

    
    private boolean isCoordinateAvailableForMoving(List<Coordinate> visitedCoordinates, Queue<Coordinate> coordinatesToCheck, World world, Coordinate next, Class<? extends Entity> target) {
        return !visitedCoordinates.contains(next) &&
                !coordinatesToCheck.contains(next) &&
                (world.isCoordinateEmpty(next) || isTargetNext(world, target, next));
    }

    private boolean isTargetNext(World world, Class<? extends Entity> target, Coordinate next) {
        if(!world.isCoordinateEmpty(next)) {
            return target.isInstance(world.getEntity(next));
        }
        return false;
    }

    private List<Coordinate> stepToRandomNeighbourCoordinate(Coordinate currentCoordinate, World world) {
        Random random = new Random();
        List<Coordinate> availableSteps = new ArrayList<>();
        List<Coordinate> neighbourCells = checkNeighbourCoordinatesToMove(currentCoordinate, world);

        for (Coordinate coordinate : neighbourCells) {
            if (world.isCoordinateEmpty(coordinate)) {
                availableSteps.add(coordinate);
            }
        }
        if(availableSteps.isEmpty()) {
            return Collections.singletonList(currentCoordinate);
        }

        int randomIndex = random.nextInt(availableSteps.size());
        Coordinate randomStep = availableSteps.get(randomIndex);

        return Collections.singletonList(randomStep);
    }
}
