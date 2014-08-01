package com.epam.training.message;

public class Miss implements FireAnswer {

    @Override
    public String toString() {
        return "MISS";
    }

    @Override
    public int demage() {
        return 0;
    }
}
