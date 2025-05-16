package io.github.programacaoealgoritmos;

public class Boss extends Enemy {
    public Boss(float x, float y) {
        super(x, y, "boss.png"); // Sets initial boss position and sprite on assets folder
        this.speed = 0.5f; // sets boss speed
    }
}
