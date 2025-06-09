package io.github.programacaoealgoritmos.commands.useitem;

import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;

public class DrinkPotionCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        System.out.println("Player drinks a potion!");
    }
}
