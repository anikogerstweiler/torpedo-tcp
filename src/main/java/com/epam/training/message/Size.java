package com.epam.training.message;

public class Size implements Message {

    private int width;

    private int height;

    public Size(String input) {
        String[] splitted = input.split(" ");
        width = Integer.parseInt(splitted[0].trim());
        height = Integer.parseInt(splitted[1].trim());
    }

    public Size(int boardWidth, int boardHeight) {
        width = boardWidth;
        height = boardHeight;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "SIZE " + width + " " + height;
    }
}
