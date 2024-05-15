package item.powerup.increasednumberffbombs;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that increases the number of bombs the player can place.
 */
public class IncreasedNumberOfBombs extends PowerUp {

    /**
     * Constructor for IncreasedNumberOfBombs
     */
    public IncreasedNumberOfBombs() {
        super(ResourceCollection.Images.INCREACEDBOMBS.getImage());
    }

    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setBombCount(p.getBombCount() + 1);
    }



    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {
        setFinishTime(System.currentTimeMillis());
    }
}
