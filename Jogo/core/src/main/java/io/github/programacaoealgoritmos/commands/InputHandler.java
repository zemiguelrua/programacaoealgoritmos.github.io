package io.github.programacaoealgoritmos.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.programacaoealgoritmos.Player;

public class InputHandler {

    private final Command moveUp = new MoveUpCommand();
    private final Command moveDown = new MoveDownCommand();
    private final Command moveLeft = new MoveLeftCommand();
    private final Command moveRight = new MoveRightCommand();

    // Called by Player to process input and move using commands
    public void handleInput(Player player, float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveUp.execute(player, delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveDown.execute(player, delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft.execute(player, delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight.execute(player, delta);
        }
    }
}
