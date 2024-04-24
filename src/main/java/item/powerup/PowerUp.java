package item.powerup;

import entity.player.Player;
import item.GameItem;
import util.ResourceCollection;

import java.awt.*;


public abstract class PowerUp extends GameItem {

    public PowerUp(Image image) {
        super(image);
        this.baseImage = ResourceCollection.Images.POWERUP_ICON.getImage();
    }

    // can be used for displaying clock icon or something in powerUps array
    public boolean isPowerUpAboutToFinish() {
        return System.currentTimeMillis() > getFinishTime() - 3000 && getFinishTime() != 0;
    }

    public abstract void apply(Player p);

    public abstract void reset(Player p);
}
