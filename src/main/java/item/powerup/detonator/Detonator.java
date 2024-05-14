package item.powerup.detonator;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that allows the player to detonate bombs.
 */
public class Detonator extends PowerUp {

    /**
     * Constructor for Detonator
     */
    public Detonator() {
        super(ResourceCollection.Images.DETONATOR_POWERUP.getImage());
    }

    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setDetonator(true);
    }

    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {
        p.setDetonator(false);
        setFinishTime(System.currentTimeMillis());
    }
}
