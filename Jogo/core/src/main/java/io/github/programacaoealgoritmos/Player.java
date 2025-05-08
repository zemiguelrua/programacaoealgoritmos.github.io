package io.github.programacaoealgoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    public float x, y;
    private float speed = 100;
    private Texture texture;
    private final float width, height;
    private Map map;

    public Player(Map map) {
        x = 100;
        y = 100;
        texture = new Texture("player.png");
        width = texture.getWidth();
        height = texture.getHeight();
        this.map = map;
    }

    public void update(float delta) {
        float oldX = x;
        float oldY = y;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // Insert logic for attack
        }

        if (checkCollision()) {
            x = oldX;
            y = oldY;
        }
    }

    private boolean checkCollision() {
        int tileSize = 16;


        int topLeftX = (int) x / tileSize;
        int topLeftY = (int) (y + height) / tileSize;

        int topRightX = (int) (x + width) / tileSize;
        int topRightY = (int) (y + height) / tileSize;

        int bottomLeftX = (int) x / tileSize;
        int bottomLeftY = (int) y / tileSize;

        int bottomRightX = (int) (x + width) / tileSize;
        int bottomRightY = (int) y / tileSize;

        return map.hasWallAt(topLeftX, topLeftY) ||
            map.hasWallAt(topRightX, topRightY) ||
            map.hasWallAt(bottomLeftX, bottomLeftY) ||
            map.hasWallAt(bottomRightX, bottomRightY);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
