package item.curse.lowspeed;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

public class LowSpeed extends Curse {

    public LowSpeed() {
        super(ResourceCollection.Images.LOWSPEED_CURSE.getImage());
    }


    @Override
    public void apply(Player p) {
        p.setSpeed(135);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    @Override
    public void reset(Player p) {
        p.resetDefaultSpeed();
    }
}
