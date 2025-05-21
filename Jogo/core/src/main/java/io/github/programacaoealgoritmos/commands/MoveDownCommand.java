package io.github.programacaoealgoritmos.commands;

import io.github.programacaoealgoritmos.Player;

// Moves the player down
public class MoveDownCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.y -= player.getSpeed() * delta;
    }
}
