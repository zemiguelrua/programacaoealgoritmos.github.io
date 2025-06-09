package io.github.programacaoealgoritmos.commands.movement;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

// Moves the player down
public class MoveDownCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.y -= player.getSpeed() * delta;
    }
}
