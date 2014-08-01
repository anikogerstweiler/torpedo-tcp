package com.epam.training.message;

public class Hit implements FireAnswer {

    @Override
    public String toString() {
        return "HIT";
    }

    @Override
    public int demage() {
        return 1;
    }
}
