package com.taitly.world;

import com.taitly.entities.Entity;

public class WorldConsoleRenderer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String GROUND = "\uD83D\uDFEB";
    private static final String ROCK = "\uD83D\uDDFB";
    private static final String TREE = "\uD83C\uDF32";
    private static final String CARROT = "\uD83E\uDD55";
    private static final String HERBIVORE = "\uD83D\uDC07";
    private static final String PREDATOR = "\uD83E\uDD8A";

    public void render(World world) {
        for (int row = 0; row < world.getRows(); row++) {
            String line = "";
            for (int col = 0; col < world.getColumns(); col++) {
                Coordinate coordinate = new Coordinate(row, col);
                if(world.isCoordinateEmpty(coordinate)) {
                    line += GROUND + " ";
                } else {
                    line += selectSpriteForEntities(world.getEntity(coordinate)) + " ";
                }
                line += ANSI_RESET;
            }
            System.out.println(line);
        }
    }

    private String selectSpriteForEntities(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Carrot" -> CARROT;
            case "Rock" -> ROCK;
            case "Tree" -> TREE;
            case "Herbivore" -> HERBIVORE;
            case "Predator" -> PREDATOR;
            default -> throw new UnsupportedOperationException("Unsupported entity type: " + entity.getClass().getSimpleName());
        };
    }
}
