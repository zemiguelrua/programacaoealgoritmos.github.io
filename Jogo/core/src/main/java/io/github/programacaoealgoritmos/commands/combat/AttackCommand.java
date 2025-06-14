package io.github.programacaoealgoritmos.commands.combat;

import io.github.programacaoealgoritmos.Enemy;
import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;
import java.util.List;

public class AttackCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        // Check if player can attack (cooldown)
        if (!player.canAttack()) {
            return; // Still on cooldown
        }

        // Start the attack animation
        player.startAttack();

        // Check if enemies list exists before using it
        List<Enemy> enemies = player.getEnemies();
        if (enemies == null) {
            return;
        }

        // Enemy knockback and damage
        for (Enemy enemy : enemies) {
            float dx = enemy.getX() - player.x;
            float dy = enemy.getY() - player.y;

            float distanceSquared = dx * dx + dy * dy;
            float range = 32 * 32; // only hit nearby enemies (within 32px)

            if (distanceSquared < range) {
                float knockbackAmount = 20f;
                float length = (float) Math.sqrt(distanceSquared);

                if (length != 0) {
                    float newX = enemy.getX() + (dx / length) * knockbackAmount;
                    float newY = enemy.getY() + (dy / length) * knockbackAmount;
                    enemy.setPosition(newX, newY);
                    System.out.println("Enemy hit and knocked back!");
                }
            }
        }
    }
}
