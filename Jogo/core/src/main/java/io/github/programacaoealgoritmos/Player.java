package io.github.programacaoealgoritmos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.programacaoealgoritmos.commands.InputHandler;


public class Player {
    public float x, y;
    private float speed = 100;
    private Texture texture;
    private final float width, height;
    private Map map;

    // Uses InputHandler to map key inputs to commands
    private final InputHandler inputHandler = new InputHandler();

    public Player(Map map) {
        texture = new Texture("player.png"); // Sets a sprite in folder assets for the player
//      Sets width and height based on the loaded texture's dimensions.
        width = texture.getWidth();
        height = texture.getHeight();
//      references the player to the map
        this.map = map;
    }

//  Updates the player state
    public void update(float delta) {
//      Saves the current player position coordinates for collision handling
        float oldX = x;
        float oldY = y;

        // Handles inputs
        inputHandler.handleInput(this, delta);

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
        // If any of these positions has a wall, return true
        return map.hasWallAt(left, bottom)  // bottom-left corner
            || map.hasWallAt(right, bottom) // bottom-right corner
            || map.hasWallAt(left, top)     // top-left corner
            || map.hasWallAt(right, top);   // top-right corner
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y); // draws the players sprite in the position x and y
    }
    public float getSpeed() {
        return speed;
    }
//    Disposes players texture to avoid memory leaks
    public void dispose() {
        texture.dispose();
    }
}
