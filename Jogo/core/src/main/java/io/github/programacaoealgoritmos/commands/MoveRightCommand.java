package io.github.programacaoealgoritmos.commands;

import io.github.programacaoealgoritmos.Player;

// Moves the player right
public class MoveRightCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.x += player.getSpeed() * delta;
    }
}
