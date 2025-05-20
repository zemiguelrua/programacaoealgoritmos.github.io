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
        texture = new Texture("player.png"); // Sets a sprite in folder assets for the player
//      Sets width and height based on the loaded texture's dimensions.
        width = texture.getWidth();
        height = texture.getHeight();
//        reference the player to the map
        this.map = map;
    }

//    Method to update the player state
    public void update(float delta) {
//      Saves the current player position coordinates for collision handling
        float oldX = x;
        float oldY = y;

        float move = speed * delta;
//      Manages what each key pressing does
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += move;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= move;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= move;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += move;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // Insert logic for attack
        }
//      if a collision happens, revert the player position to the last one
        if (checkCollision()) {
            x = oldX;
            y = oldY;
        }
    }

    private boolean checkCollision() {
        int tileSize = 16; // assume a constant 16 to the tiles size (All sprites in the assets folder are 16 pixel)

//      x is players left side and y is its bottom, x + width is players right side and y + height is its top
        int left = (int) x / tileSize; // the number of the tile to the left of the player
        int right = (int) (x + width) / tileSize; // the number of the tile to the right of the player
        int bottom = (int) y / tileSize;// the number of the tile on the bottom of the player
        int top = (int) (y + height) / tileSize; // the number of the tile on the top of the player


        // Check for a wall at each of the player's corners
        // If any of these positions has a wall, return true (collision detected)
        return map.hasWallAt(left, bottom)  // bottom-left corner
            || map.hasWallAt(right, bottom) // bottom-right corner
            || map.hasWallAt(left, top)     // top-left corner
            || map.hasWallAt(right, top);   // top-right corner
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y); // draws the players sprite in the position x and y
    }

//    Disposes players texture to avoid memory leaks
    public void dispose() {
        texture.dispose();
    }
}
