package io.github.programacaoealgoritmos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private Map map;

    @Override
    public void create() {
        batch = new SpriteBatch();

        map = new Map();

        player = new Player(map);

//        if (map instanceof Map) {
//            player.x = 0;
//            player.y = Gdx.graphics.getHeight() / 2;
//        } else {
        player.x = 0;
        player.y = 242;
//        }
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
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        map.dispose();
    }
}
