package item.curse;

import entity.player.Player;
import item.GameItem;
import util.ResourceCollection;

import java.awt.*;


public abstract class Curse extends GameItem {

    public Curse(Image image) {
        super(image);
        this.baseImage = ResourceCollection.Images.CURSE_ICON.getImage();
    }

    // can be used for displaying clock icon or something in Curse array
    public boolean isCurseAboutToFinish() {
        return System.currentTimeMillis() > getFinishTime() - 3000;
    }

    public abstract void apply(Player p);

    public abstract void reset(Player p);

    public Image getBaseImage() {
        return this.baseImage;
    }
}
