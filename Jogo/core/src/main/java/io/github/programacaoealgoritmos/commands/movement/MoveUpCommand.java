// MoveUpCommand.java
package io.github.programacaoealgoritmos.commands.movement;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

public class MoveUpCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        if (!player.isShielding()) { // Can't move while shielding
            player.y += player.getSpeed() * delta;
            player.updateMovementDirection(0, 1); // Track upward movement
        }
    }
}
