package item.curse.prohibitbombs;

import item.curse.Curse;

import entity.player.Player;

import util.ResourceCollection;

/**
 * Represents a curse that prohibits the player from placing bombs for some time.
 */
public class ProhibitBombs extends Curse {

    /**
     * Constructor for ProhibitBombs
     */
    public ProhibitBombs() {
        super(ResourceCollection.Images.CANNOTPLACE_BOMB.getImage());
    }

    /**
     * Applies the curse to the player
     * @param p player to apply the curse to
     */
    @Override
    public void apply(Player p) {
        p.setCanPlaceBomb(false);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    /**
     * Resets the curse for the player
     * @param p player to reset the curse for
     */
    @Override
    public void reset(Player p) {
        p.setCanPlaceBomb(true);
        setFinishTime(System.currentTimeMillis());
    }
}
