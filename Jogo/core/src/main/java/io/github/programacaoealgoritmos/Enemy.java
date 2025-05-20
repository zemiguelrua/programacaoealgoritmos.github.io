package io.github.programacaoealgoritmos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    protected float x, y; // Enemy's position on the screen
    protected Texture texture;
    protected float speed = 1f; // Sets default movement speed of the enemy

    // Constructor to set the enemy's position and texture
    public Enemy(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(texturePath);
    }

    // Moves the enemy toward the player if within agro range
    public void update(float playerX, float playerY) {
        // Calculates the distance between enemy and player
        float dx = playerX - x;
        float dy = playerY - y;

        // Calculates the-line distance to the player
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        // Defines default distance for enemy agro
        float agroRadius = 50f;

        // If the player is within agro range, moves the enemy toward the player
        if (length <= agroRadius) {
            x += (dx / length) * speed;
            y += (dy / length) * speed;
        }
    }

    // Draws the enemy on the screen at its current position
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    //    Disposes players texture to avoid memory leaks
    public void dispose() {
        texture.dispose();
    }
}
