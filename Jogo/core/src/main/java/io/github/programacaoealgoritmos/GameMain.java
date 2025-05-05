package io.github.programacaoealgoritmos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture playerTexture;
    private float playerX, playerY;

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture("player.png"); // Coloque player.png em assets/
        playerX = 200;
        playerY = 200;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(playerTexture, playerX, playerY, playerTexture.getWidth() * 2, playerTexture.getHeight() * 2);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTexture.dispose();
    }
}
