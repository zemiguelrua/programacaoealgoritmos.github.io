package io.github.programacaoealgoritmos;

public class Ghost extends Enemy {
    public Ghost(float x, float y) {
        super(x, y, "ghost.png");
        this.speed = 0.5f;
    }
}
