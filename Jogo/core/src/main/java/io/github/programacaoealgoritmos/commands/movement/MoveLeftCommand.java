package io.github.programacaoealgoritmos.commands.movement;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

public class MoveLeftCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        if (!player.isShielding()) { // Can't move while shielding
            player.x -= player.getSpeed() * delta;
            player.updateMovementDirection(-1, 0); // Track leftward movement
        }
    }
}
