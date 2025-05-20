package io.github.programacaoealgoritmos;

public class Spider extends Enemy {
    private float patrolRange = 100; // Patrol distance
    private float originX; // Spawn position
    private boolean movingRight = true; // When true move right, when false move left

    public Spider(float x, float y) {
        super(x, y, "spider.png"); // Sets initial position and sprite from assets folder
        this.speed = 1.5f; // sets movement speed
        this.originX = x; // starting X position for patrol reference
    }

    @Override
    public void update(float playerX, float playerY) {
        // Makes the spider move right when true and left when false
        if (movingRight) {
            x += speed;
            if (x > originX + patrolRange) movingRight = false; // Inverts movement direction when patrolRange reaches the limit
        } else {
            x -= speed;
            if (x < originX - patrolRange) movingRight = true;
        }
    }
}
