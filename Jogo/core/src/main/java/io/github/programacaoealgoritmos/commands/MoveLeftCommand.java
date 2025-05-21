package io.github.programacaoealgoritmos.commands;

import io.github.programacaoealgoritmos.Player;

// Moves the player left
public class MoveLeftCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.x -= player.getSpeed() * delta;
    }
}
