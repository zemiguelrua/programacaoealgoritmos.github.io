package io.github.programacaoealgoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
    private int[][] layout;
    public final int tileSize = 16;
    private Texture floorTexture;
    private Texture wallTexture;

    public Map() {
        floorTexture = new Texture("floor.png");
        wallTexture = new Texture("wall.png");

        int tilesX = Gdx.graphics.getWidth() / tileSize;
        int tilesY = Gdx.graphics.getHeight() / tileSize;

        layout = new int[tilesY][tilesX];

        for (int y = 0; y < tilesY; y++) {
            for (int x = 0; x < tilesX; x++) {
                layout[y][x] = 1;
            }
        }

        int middleY = tilesY / 2;
        for (int x = 0; x < tilesX; x++) {
            layout[middleY][x] = 0;
            if (middleY + 1 < tilesY) layout[middleY + 1][x] = 0;
            if (middleY - 1 >= 0) layout[middleY - 1][x] = 0;
        }

        int branch1X = tilesX / 4;
        int branch2X = tilesX / 2;
        int branch3X = (tilesX * 3) / 4;

        for (int y = middleY; y < tilesY - 2; y++) {
            layout[y][branch1X] = 0;
            if (branch1X + 1 < tilesX) layout[y][branch1X + 1] = 0;
            if (branch1X - 1 >= 0) layout[y][branch1X - 1] = 0;
        }

        for (int y = 2; y < tilesY - 2; y++) {
            layout[y][branch2X] = 0;
            if (branch2X + 1 < tilesX) layout[y][branch2X + 1] = 0;
            if (branch2X - 1 >= 0) layout[y][branch2X - 1] = 0;
        }

        for (int y = 2; y < middleY; y++) {
            layout[y][branch3X] = 0;
            if (branch3X + 1 < tilesX) layout[y][branch3X + 1] = 0;
            if (branch3X - 1 >= 0) layout[y][branch3X - 1] = 0;
        }

        int upperPath = (tilesY * 3) / 4;
        int lowerPath = tilesY / 4;

        for (int x = branch1X - 1; x <= branch2X + 1; x++) {
            if (x >= 0 && x < tilesX) {
                layout[upperPath][x] = 0;
                if (upperPath + 1 < tilesY) layout[upperPath + 1][x] = 0;
                if (upperPath - 1 >= 0) layout[upperPath - 1][x] = 0;
            }
        }

        for (int x = branch2X - 1; x <= branch3X + 1; x++) {
            if (x >= 0 && x < tilesX) {
                layout[lowerPath][x] = 0;
                if (lowerPath + 1 < tilesY) layout[lowerPath + 1][x] = 0;
                if (lowerPath - 1 >= 0) layout[lowerPath - 1][x] = 0;
            }
        }

        System.out.println("Map created with success!");
    }

    public void render(SpriteBatch batch) {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                Texture tex = layout[y][x] == 1 ? wallTexture : floorTexture;
                batch.draw(tex, x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }

    public boolean hasWallAt(int x, int y) {
        if (x < 0 || y < 0 || y >= layout.length || x >= layout[0].length) {
            return true;
        }

        if (layout[y][x] == 1) {
            System.out.println("wall detected: " + x + "," + y);
        }

        return layout[y][x] == 1;
    }

    public void dispose() {
        floorTexture.dispose();
        wallTexture.dispose();
    }
}
