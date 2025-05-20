package io.github.programacaoealgoritmos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch; // draws textures on the screen
    private Player player;
    private Map map;
    // List with all enemy objects
    private List<Enemy> enemies;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Creates map and player, passing the map to the player for collision handling
        map = new Map();
        player = new Player(map);

        enemies = new ArrayList<>();
        // adds enemies to the enemies list
        enemies.add(new Rat(100, 220));
        enemies.add(new Bat(300, 150));
        enemies.add(new Ghost(500, 200));
        enemies.add(new Spider(400, 100));
        enemies.add(new Boss(600, 230));

        // Sets player's starting position on the map
        player.x = 0;
        player.y = 242;
    }

    @Override
    public void render() {
        // Calculates the frame time and updates player's position
        float deltaTime = Gdx.graphics.getDeltaTime();
        player.update(deltaTime);

        // Clears the screen with the set background color
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begins the drawing on screen batch and renders map and player
        batch.begin();
        map.render(batch);
        player.render(batch);

        for (Enemy enemy : enemies) {
            enemy.update(player.x, player.y); // passes the player's position to the enemies
            enemy.render(batch); // renders each enemy
        }

        batch.end();
    }

    @Override
    public void dispose() {
        // Frees memory used by rendering and game assets when no longer needed
        batch.dispose();
        player.dispose();
        map.dispose();
        for (Enemy enemy : enemies) enemy.dispose();
    }
}
