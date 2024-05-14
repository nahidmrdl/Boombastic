package item.curse.lowspeed;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

/**
 * Represents a curse that reduces the speed of the player for some time.
 */
public class LowSpeed extends Curse {
    /**
     * Constructor for LowSpeed
     */
    public LowSpeed() {
        super(ResourceCollection.Images.LOWSPEED_CURSE.getImage());
    }


    /**
     * Applies the curse to the player
     * @param p player to apply the curse to
     */
    @Override
    public void apply(Player p) {
        p.setSpeed(135);
        System.out.println("Player speed reduced");
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    /**
     * Resets the curse for the player
     * @param p player to reset the curse for
     */
    @Override
    public void reset(Player p) {
        p.resetDefaultSpeed();
        setFinishTime(System.currentTimeMillis());
    }
}
