package item.powerup.invincibility;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that makes the player invincible for some time.
 */
public class Invincibility extends PowerUp {

    /**
     * Constructor for Invincibility
     */
    public Invincibility() {
        super(ResourceCollection.Images.INVINCIBLE_POWERUP.getImage());
    }

    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setInvincible(true);
        setFinishTime(System.currentTimeMillis() + 10000);
    }

    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {
        p.setInvincible(false);
        setFinishTime(System.currentTimeMillis());
    }
}
