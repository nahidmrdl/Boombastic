package item.powerup.rollerskate;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that increases the speed of the player for some time.
 */
public class RollerSkate extends PowerUp {

    /**
     * Constructor for RollerSkate
     */
    public RollerSkate() {
            super(ResourceCollection.Images.ROLLERSKATESPEED_POWERUP.getImage());
        }


    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setSpeed(35);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {
        p.resetDefaultSpeed();
        setFinishTime(System.currentTimeMillis());
    }
}
