package com.epam.training.message;

public class Fire implements Message {

    private int coordinateX;

    private int coordinateY;

    public Fire(String input) {
        String[] splitted = input.split(" ");
        coordinateX = Integer.parseInt(splitted[0].trim());
        coordinateY = Integer.parseInt(splitted[1].trim());
    }

    public Fire(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "FIRE " + coordinateX + " " + coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
}
