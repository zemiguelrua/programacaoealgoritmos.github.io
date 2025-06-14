package io.github.programacaoealgoritmos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.programacaoealgoritmos.commands.InputHandler;

import java.util.List;

public class Player {
    public float x, y;
    private float speed = 100;
    private Texture texture;
    private Texture swordTexture;
    private Texture shieldTexture;
    private Texture potionTexture;
    private final float width, height;
    private Map map;
    private boolean shielding = false;

    // Attack system variables
    private boolean isAttacking = false;
    private float attackTimer = 0f;
    private final float attackDuration = 0.3f; // How long sword is visible
    private float attackCooldownTimer = 0f;
    private final float attackCooldown = 0.5f; // Time before next attack

    // Last movement direction for sword positioning
    private float lastMoveX = 0f;
    private float lastMoveY = 0f;

    // Sets max health for 3 hearts
    private int maxHealth = 3;
    private int health = maxHealth;
    private int potions = 2; // Player starts with 2 potions

    // Potion visual effect
    private boolean showingPotionEffect = false;
    private float potionEffectTimer = 0f;
    private final float potionEffectDuration = 0.5f;

    // Uses InputHandler to map key inputs to commands
    private final InputHandler inputHandler = new InputHandler();

    // Enemies the player can interact with
    private List<Enemy> enemies;

    public Player(Map map) {
        texture = new Texture("player.png"); // Sets a sprite in folder assets for the player
        swordTexture = new Texture("sword.png"); // Load sword texture
        shieldTexture = new Texture("shield.png"); // Load shield texture
        potionTexture = new Texture("potion.png"); // Load potion texture

        // Sets width and height based on the loaded texture's dimensions.
        width = texture.getWidth();
        height = texture.getHeight();
        // References the player to the map
        this.map = map;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isShielding() {
        return shielding;
    }

    public void setShielding(boolean shielding) {
        this.shielding = shielding;
    }

    // Attack system methods
    public boolean canAttack() {
        return attackCooldownTimer <= 0f;
    }

    public void startAttack() {
        if (canAttack()) {
            isAttacking = true;
            attackTimer = attackDuration;
            attackCooldownTimer = attackCooldown;
            System.out.println("Player attacks!");
        }
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    // Potion system methods
    public int getPotions() {
        return potions;
    }

    public boolean canDrinkPotion() {
        // For testing: allow drinking even with full HP
        return potions > 0;

        // For production: uncomment this line instead
        // return potions > 0 && health < maxHealth;
    }

    public void drinkPotion() {
        if (canDrinkPotion()) {
            potions--;
            int oldHealth = health;
            health = maxHealth; // Restore to full health

            // Show visual effect
            showingPotionEffect = true;
            potionEffectTimer = potionEffectDuration;

            System.out.println("Used potion! Health: " + oldHealth + " -> " + health + " | Potions left: " + potions);
        } else if (potions <= 0) {
            System.out.println("No potions left!");
        } else {
            System.out.println("Health is already full!");
        }
    }

    // Update last movement direction
    public void updateMovementDirection(float dx, float dy) {
        if (dx != 0 || dy != 0) {
            lastMoveX = dx;
            lastMoveY = dy;
        }
    }

    public void damage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println("You took damage! Current hearts: " + health);
    }

    public int getHealth() {
        return health;
    }

    public void pushBack(float dx, float dy) {
        // Push player in the opposite direction after damage
        x -= dx;
        y -= dy;
        System.out.println("Player pushed back!");
    }

    // Updates the player state
    public void update(float delta) {
        // Saves the current player position coordinates for collision handling
        float oldX = x;
        float oldY = y;

        // Update attack timers
        if (attackTimer > 0f) {
            attackTimer -= delta;
            if (attackTimer <= 0f) {
                isAttacking = false;
            }
        }

        if (attackCooldownTimer > 0f) {
            attackCooldownTimer -= delta;
        }

        // Update potion effect timer
        if (potionEffectTimer > 0f) {
            potionEffectTimer -= delta;
            if (potionEffectTimer <= 0f) {
                showingPotionEffect = false;
            }
        }

        // Handles inputs
        inputHandler.handleInput(this, delta);

        if (checkCollision()) {
            x = oldX;
            y = oldY;
        }
    }

    private boolean checkCollision() {
        int tileSize = 16; // assume a constant 16 to the tiles size (All sprites in the assets folder are 16 pixel)

        // x is players left side and y is its bottom, x + width is players right side and y + height is its top
        int left = (int) x / tileSize; // the number of the tile to the left of the player
        int right = (int) (x + width) / tileSize; // the number of the tile to the right of the player
        int bottom = (int) y / tileSize;// the number of the tile on the bottom of the player
        int top = (int) (y + height) / tileSize; // the number of the tile on the top of the player

        // Check for a wall at each of the player's corners
        // If any of these positions has a wall, return true
        return map.hasWallAt(left, bottom)  // bottom-left corner
            || map.hasWallAt(right, bottom) // bottom-right corner
            || map.hasWallAt(left, top)     // top-left corner
            || map.hasWallAt(right, top);   // top-right corner
    }

    public void render(SpriteBatch batch) {
        // Draw the player sprite
        batch.draw(texture, x, y);

        // Draw sword if attacking
        if (isAttacking) {
            renderSword(batch);
        }

        // Draw shield if shielding
        if (shielding) {
            renderShield(batch);
        }

        // Draw potion effect if showing
        if (showingPotionEffect) {
            renderPotionEffect(batch);
        }
    }

    private void renderSword(SpriteBatch batch) {
        float swordX = x;
        float swordY = y;

        // Position sword based on last movement direction
        if (lastMoveX > 0) { // Moving right
            swordX = x + width;
            swordY = y;
        } else if (lastMoveX < 0) { // Moving left
            swordX = x - swordTexture.getWidth();
            swordY = y;
        } else if (lastMoveY > 0) { // Moving up
            swordX = x;
            swordY = y + height;
        } else if (lastMoveY < 0) { // Moving down
            swordX = x;
            swordY = y - swordTexture.getHeight();
        } else { // Default position (right)
            swordX = x + width;
            swordY = y;
        }

        batch.draw(swordTexture, swordX, swordY);
    }

    private void renderShield(SpriteBatch batch) {
        float shieldX = x;
        float shieldY = y;

        // Position shield based on last movement direction
        if (lastMoveX > 0) { // Was moving right
            shieldX = x + width;
            shieldY = y;
        } else if (lastMoveX < 0) { // Was moving left
            shieldX = x - shieldTexture.getWidth();
            shieldY = y;
        } else if (lastMoveY > 0) { // Was moving up
            shieldX = x;
            shieldY = y + height;
        } else if (lastMoveY < 0) { // Was moving down
            shieldX = x;
            shieldY = y - shieldTexture.getHeight();
        } else { // Default position (right)
            shieldX = x + width;
            shieldY = y;
        }

        batch.draw(shieldTexture, shieldX, shieldY);
    }

    private void renderPotionEffect(SpriteBatch batch) {
        // Show potion effect above the player
        float potionX = x;
        float potionY = y + height + 5; // 5 pixels above player

        batch.draw(potionTexture, potionX, potionY);
    }

    public float getSpeed() {
        return speed;
    }

    // Disposes players texture to avoid memory leaks
    public void dispose() {
        texture.dispose();
        swordTexture.dispose();
        shieldTexture.dispose();
        potionTexture.dispose();
    }
}
