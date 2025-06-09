package io.github.programacaoealgoritmos.commands.movement;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

// Moves the player right
public class MoveRightCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        player.x += player.getSpeed() * delta;
    }
}
