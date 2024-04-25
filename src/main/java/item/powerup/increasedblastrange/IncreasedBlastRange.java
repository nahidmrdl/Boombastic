package item.powerup.increasedblastrange;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

import java.awt.*;

/**
 * Represents a power-up that increases the blast range of the player's bombs.
 */
public class IncreasedBlastRange extends PowerUp {

    /**
     * Constructor for IncreasedBlastRange
     */
    public IncreasedBlastRange() {
        super(ResourceCollection.Images.BLASTRANGE_EXTENSION.getImage());
    }

    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setBombBlastRange(p.getBombBlastRange() + 1);
    }

    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {
        p.setBombBlastRange(p.getBombBlastRange() - 1);
    }
}
