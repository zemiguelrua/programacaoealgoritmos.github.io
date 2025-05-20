package io.github.programacaoealgoritmos;

public class Rat extends Enemy {
    public Rat(float x, float y) {
        super(x, y, "rat.png"); // Sets initial position and sprite on assets folder
        this.speed = 0.8f; // sets movement speed
    }
}
