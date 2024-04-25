package item.curse;

import entity.player.Player;
import item.GameItem;
import util.ResourceCollection;

import java.awt.*;
/**
 * Represents a curse in the game map.
 */
public abstract class Curse extends GameItem {

    /**
     * Constructor for Curse
     * @param image image of the curse
     */
    public Curse(Image image) {
        super(image);
        this.baseImage = ResourceCollection.Images.CURSE_ICON.getImage();
    }

    // can be used for displaying clock icon or something in Curse array
    public boolean isCurseAboutToFinish() {
        return System.currentTimeMillis() > getFinishTime() - 3000 && getFinishTime() != 0;
    }

    /**
     * Applies the curse
     * @param p player to apply the curse to
     */
    public abstract void apply(Player p);

    /**
     * Resets the curse
     * @param p player to reset the curse for
     */
    public abstract void reset(Player p);

    /**
     * Gets the base image of the curse
     * @return the base image of the curse
     */
    public Image getBaseImage() {
        return this.baseImage;
    }
}
