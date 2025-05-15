package io.github.programacaoealgoritmos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private Map map;
    private List<Enemy> enemies;

    @Override
    public void create() {
        batch = new SpriteBatch();

        map = new Map();
        player = new Player(map);

        enemies = new ArrayList<>();
        enemies.add(new Rat(100, 220));
        enemies.add(new Bat(300, 150));
        enemies.add(new Ghost(500, 200));
        enemies.add(new Spider(400, 100));
        enemies.add(new Boss(600, 230));

        player.x = 0;
        player.y = 242;
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        player.update(deltaTime);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        map.render(batch);
        player.render(batch);

        for (Enemy enemy : enemies) {
            enemy.update(player.x, player.y);
            enemy.render(batch);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        map.dispose();
        for (Enemy enemy : enemies) enemy.dispose();
    }
}
