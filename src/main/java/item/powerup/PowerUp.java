package item.powerup;

import entity.player.Player;
import item.GameItem;
import util.ResourceCollection;

import java.awt.*;
/**
 * Represents a power-up in the game map.
 */
public abstract class PowerUp extends GameItem {

    /**
     * Constructor for PowerUp
     * @param image image of the power-up
     */
    public PowerUp(Image image) {
        super(image);
        this.baseImage = ResourceCollection.Images.POWERUP_ICON.getImage();
    }

    // can be used for displaying clock icon or something in powerUps array
    public boolean isPowerUpAboutToFinish() {
        return System.currentTimeMillis() > getFinishTime() - 3000 && getFinishTime() != 0;
    }

    /**
     * Applies the power-up
     * @param p player to apply the power-up to
     */
    public abstract void apply(Player p);

    /**
     * Resets the power-up
     * @param p player to reset the power-up for
     */
    public abstract void reset(Player p);
}

