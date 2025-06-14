package io.github.programacaoealgoritmos.commands.combat;

import io.github.programacaoealgoritmos.Enemy;
import io.github.programacaoealgoritmos.Player;
import io.github.programacaoealgoritmos.commands.Command;
import java.util.List;

public class UseShieldCommand implements Command {
    @Override
    public void execute(Player player, float delta) {
        // Toggle shield on/off or keep it active while key is held
        player.setShielding(true);

        // Check if enemies list exists before using it
        List<Enemy> enemies = player.getEnemies();
        if (enemies == null) {
            return;
        }

        // Shield knockback - push away nearby enemies
        for (Enemy enemy : enemies) {
            float dx = enemy.getX() - player.x;
            float dy = enemy.getY() - player.y;

            float distanceSquared = dx * dx + dy * dy;
            float shieldRange = 40 * 40; // Shield has slightly larger range than sword

            if (distanceSquared < shieldRange && distanceSquared > 0) {
                float knockbackAmount = 15f; // Slightly less knockback than sword
                float length = (float) Math.sqrt(distanceSquared);

                if (length != 0) {
                    float newX = enemy.getX() + (dx / length) * knockbackAmount;
                    float newY = enemy.getY() + (dy / length) * knockbackAmount;
                    enemy.setPosition(newX, newY);
                }
            }
        }
    }
}
