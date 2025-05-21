package io.github.programacaoealgoritmos.commands;

import io.github.programacaoealgoritmos.Player;

// Command design pattern interface to implement player movement
public interface Command {
    void execute(Player player, float delta);
}
