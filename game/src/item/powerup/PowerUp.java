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

    public abstract void apply(Player p);

    public abstract void reset(Player p);
}
