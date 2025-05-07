package io.github.programacaoealgoritmos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    protected float x, y;
    protected Texture texture;
    protected float speed = 1f;

    public Enemy(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(texturePath);
    }

    public void update(float playerX, float playerY) {
        float dx = playerX - x;
        float dy = playerY - y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        float agroRadius = 50f;

        if (length <= agroRadius) {
            x += (dx / length) * speed;
            y += (dy / length) * speed;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
