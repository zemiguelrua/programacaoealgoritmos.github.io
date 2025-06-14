package io.github.programacaoealgoritmos.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.combat.AttackCommand;
import io.github.programacaoealgoritmos.commands.combat.UseShieldCommand;
import io.github.programacaoealgoritmos.commands.movement.MoveDownCommand;
import io.github.programacaoealgoritmos.commands.movement.MoveLeftCommand;
import io.github.programacaoealgoritmos.commands.movement.MoveRightCommand;
import io.github.programacaoealgoritmos.commands.movement.MoveUpCommand;
import io.github.programacaoealgoritmos.commands.useitem.DrinkPotionCommand;

public class InputHandler {

    private final Command moveUp = new MoveUpCommand();
    private final Command moveDown = new MoveDownCommand();
    private final Command moveLeft = new MoveLeftCommand();
    private final Command moveRight = new MoveRightCommand();
    private final Command attack = new AttackCommand();
    private final Command useShield = new UseShieldCommand();
    private final DrinkPotionCommand drinkPotion = new DrinkPotionCommand();

    private boolean potionKeyPressed = false;

    // Called by Player to process inputs and move using commands
    public void handleInput(Player player, float delta) {
        // Handle shield first - it affects movement
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            useShield.execute(player, delta);
        } else {
            // Turn off shield when key is released
            player.setShielding(false);
        }

        // Movement commands (these check if shielding internally)
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

        // Attack command
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            attack.execute(player, delta);
        }

        // Potion command (only once per key press)
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (!potionKeyPressed) {
                drinkPotion.execute(player, delta);
                potionKeyPressed = true;
            }
        } else {
            if (potionKeyPressed) {
                drinkPotion.reset();
                potionKeyPressed = false;
            }
        }
    }
}
