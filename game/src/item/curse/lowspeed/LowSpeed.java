package item.curse.lowspeed;

import entity.player.Player;
import item.curse.Curse;
import util.ResourceCollection;

public class LowSpeed extends Curse {

    public LowSpeed() {
        super(ResourceCollection.Images.LOWSPEED_CURSE.getImage());
    //    super(ResourceCollection.Images.CURSE_ICON.getImage()); // Specific image for this curse

    }


    @Override
    public void apply(Player p) {
        p.setSpeed(135);
        System.out.println("Player speed reduced");
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    @Override
    public void reset(Player p) {
        p.resetDefaultSpeed();
    }
}
