package io.github.programacaoealgoritmos.commands.useitem;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

public class DrinkPotionCommand implements Command {
    private boolean wasPressed = false; // Prevent spam clicking

    @Override
    public void execute(Player player, float delta) {
        // Only execute once per key press (not while holding)
        if (!wasPressed) {
            player.drinkPotion();
            wasPressed = true;
        }
    }

    // This method should be called when the key is released
    public void reset() {
        wasPressed = false;
    }
}
