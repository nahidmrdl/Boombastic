package item.powerup.increasedblastrange;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

import java.awt.*;

public class IncreasedBlastRange extends PowerUp {

    public IncreasedBlastRange() {
        super(ResourceCollection.Images.BLASTRANGE_EXTENSION.getImage());
    }

    @Override
    public void apply(Player p) {
        p.setBombBlastRange(p.getBombBlastRange() + 1);
    }

    @Override
    public void reset(Player p) {
        p.setBombBlastRange(p.getBombBlastRange() - 1);
    }
}
