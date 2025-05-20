package io.github.programacaoealgoritmos;

public class Ghost extends Enemy {
    public Ghost(float x, float y) {
        super(x, y, "ghost.png");// Sets initial position and sprite on assets folder
        this.speed = 0.5f; // sets movement speed
    }
}
