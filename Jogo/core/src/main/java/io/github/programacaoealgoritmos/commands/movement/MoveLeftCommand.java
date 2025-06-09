package io.github.programacaoealgoritmos.commands.movement;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

// Moves the player left
public class MoveLeftCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.x -= player.getSpeed() * delta;
    }
}
