package io.github.programacaoealgoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
    private int[][] layout; // array for the map layout (0 = floor, 1 = wall)
    public final int tileSize = 16; // Size of each tile in pixels
    private Texture floorTexture;
    private Texture wallTexture;

    public Map() {
        // Loads textures from assets
        floorTexture = new Texture("floor.png");
        wallTexture = new Texture("wall.png");
        // Calculates the number of tiles in X and Y directions based on screen size
        int tilesX = Gdx.graphics.getWidth() / tileSize;
        int tilesY = Gdx.graphics.getHeight() / tileSize;

        layout = new int[tilesY][tilesX];
        // Fills the map with walls
        fillWithWalls();
        // Generates the level layout
        generateLayout(tilesX, tilesY);
    }
    // Fills the map with wall tiles (value 1)
    private void fillWithWalls() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                layout[y][x] = 1;
            }
        }
    }
    // Clears a horizontal path at the Y position
    private void clearHorizontalPath(int y, int tilesX) {
        for (int x = 0; x < tilesX; x++) {
            clearTileAndSides(y, x);
        }
    }

    // Clears a horizontal segment between startX and endX at Y position
    private void clearHorizontalPathSegment(int startX, int endX, int y) {
        for (int x = startX; x <= endX; x++) {
            if (x >= 0 && x < layout[0].length) {
                clearTileAndSides(y, x);
            }
        }
    }

    // Clears a vertical path between startY and endY at column X
    private void clearVerticalPath(int startY, int endY, int x) {
        for (int y = startY; y < endY; y++) {
            clearTileAndSides(y, x);
        }
    }
    // Clears a tile and its neighbors
    private void clearTileAndSides(int y, int x) {
        if (isInside(y, x)) layout[y][x] = 0;           // center
        if (isInside(y, x + 1)) layout[y][x + 1] = 0;   // right
        if (isInside(y, x - 1)) layout[y][x - 1] = 0;   // left
        if (isInside(y + 1, x)) layout[y + 1][x] = 0;     // up
        if (isInside(y - 1, x)) layout[y - 1][x] = 0;     // down
    }
    // Creates paths in the map
    private void generateLayout(int tilesX, int tilesY) {
        int middleY = tilesY / 2;
        // Makes a horizontal path through the middle
        clearHorizontalPath(middleY, tilesX);

        // Defines X positions of 3 vertical branches
        int branch1X = tilesX / 4;
        int branch2X = tilesX / 2;
        int branch3X = (tilesX) * 3 / 4;

        // Creates vertical paths from branches
        clearVerticalPath(middleY, tilesY - 1, branch1X); // From the middle to the top.
        clearVerticalPath(1, tilesY - 1, branch2X); // From tile 1 to the top.
        clearVerticalPath(1, middleY, branch3X); // From tile 1 to the middle.

        // Defines Y positions for the branches connectors
        int upperPath = (tilesY * 3) / 4;
        int lowerPath = tilesY / 4;
        // Creates the connectors between the branches
        clearHorizontalPathSegment(branch1X, branch2X, upperPath);
        clearHorizontalPathSegment(branch2X, branch3X, lowerPath);
    }
    // Checks if (y, x) is in the map
    private boolean isInside(int y, int x) {
        return y >= 0 && y < layout.length && x >= 0 && x < layout[0].length;
    }

    // Renders the map with the tile textures
    public void render(SpriteBatch batch) {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                Texture tex = layout[y][x] == 1 ? wallTexture : floorTexture;
                batch.draw(tex, x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }

    // Returns true if there's a wall at the tile coordinates (x, y)
    public boolean hasWallAt(int x, int y) {
        if (!isInside(y, x)) {
            return true;
        }

        return layout[y][x] == 1;
    }

    // Dispose to avoid memory leaks
    public void dispose() {
        floorTexture.dispose();
        wallTexture.dispose();
    }
}
