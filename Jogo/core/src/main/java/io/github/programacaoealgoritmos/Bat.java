package io.github.programacaoealgoritmos;

public class Bat extends Enemy {
    public Bat(float x, float y) {
        super(x, y, "bat.png"); // Sets initial position and sprite on assets folder
        this.speed = 1.5f; // sets movement speed
    }
}
