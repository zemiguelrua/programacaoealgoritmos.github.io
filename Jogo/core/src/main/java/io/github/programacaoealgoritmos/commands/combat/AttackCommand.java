package io.github.programacaoealgoritmos.commands.combat;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

public class AttackCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        System.out.println("Player attacks!");
    }
}
