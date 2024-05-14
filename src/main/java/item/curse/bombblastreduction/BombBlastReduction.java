package item.curse.bombblastreduction;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

/**
 * Represents a curse that reduces the bomb blast range of the player for some time.
 */
public class BombBlastReduction extends Curse {

    /**
     * Constructor for BombBlastReduction
     */
    public BombBlastReduction() {
        super(ResourceCollection.Images.LOWBLASTRANGE_CURSE.getImage());
    }

    /**
     * Applies the curse to the player
     * @param p player to apply the curse to
     */
    @Override
    public void apply(Player p) {
        p.setBombBlastRange(0);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    /**
     * Resets the curse for the player
     * @param p player to reset the curse for
     */
    @Override
    public void reset(Player p) {
        p.setBombBlastRange(1);
        setFinishTime(System.currentTimeMillis());
    }
}
