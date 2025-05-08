package io.github.programacaoealgoritmos;

public class Spider extends Enemy {
    private float patrolRange = 100;
    private float originX;
    private boolean movingRight = true;

    public Spider(float x, float y) {
        super(x, y, "spider.png");
        this.speed = 1f;
        this.originX = x;
    }

    @Override
    public void update(float playerX, float playerY) {
        if (movingRight) {
            x += speed;
            if (x > originX + patrolRange) movingRight = false;
        } else {
            x -= speed;
            if (x < originX - patrolRange) movingRight = true;
        }
    }
}

