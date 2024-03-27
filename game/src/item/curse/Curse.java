package item.curse;

import item.GameItem;

public abstract class Curse extends GameItem {
    public Curse(Image baseImage) {
        super(baseImage);
    }

    public abstract void apply(Player p);

    public abstract void reset(Player p);
}
