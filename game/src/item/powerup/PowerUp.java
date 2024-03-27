package item.powerup;

import item.GameItem;


public abstract class PowerUp extends GameItem {
    public PowerUp(Image baseImage) {
        super(baseImage);
    }

    public abstract void apply(Player p);

    public abstract void reset(Player p);
}
