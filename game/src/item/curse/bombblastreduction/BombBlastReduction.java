package item.curse.bombblastreduction;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

import java.awt.*;

public class BombBlastReduction extends Curse {

    public BombBlastReduction() {
        super(ResourceCollection.Images.LOWBLASTRANGE_CURSE.getImage());
    }

    @Override
    public void apply(Player p) {
        p.setBombBlastRange(0);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    @Override
    public void reset(Player p) {
        p.setBombBlastRange(1);
    }
}
