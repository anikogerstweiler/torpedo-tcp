package com.epam.training.domain;

public class BoardFactory {

    private String inputFile;

    public BoardFactory(String inputFile) {
        this.inputFile = inputFile;
    }

    public Board create(int width, int height) {
        handleWrongInput(width, height);

        Board board = new Board(width, height);
        try (ShipTypeReader shipTypeReader = new ShipTypeReader(inputFile)) {
            while (shipTypeReader.hasNext()) {
                ShipType shipType = shipTypeReader.readShipType();
                board.createShips(shipType);
            }

            return board;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleWrongInput(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width " + width + " and height " + height + " size must be positive");
        }
    }
}
