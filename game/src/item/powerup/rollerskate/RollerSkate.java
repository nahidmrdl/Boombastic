package item.powerup.rollerskate;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

public class RollerSkate extends PowerUp {

    public RollerSkate() {
            super(ResourceCollection.Images.ROLLERSKATESPEED_POWERUP.getImage());
        }

    @Override
    public void apply(Player p) {
        p.setSpeed(35);
        setFinishTime(System.currentTimeMillis() + 5000);
    }

    @Override
    public void reset(Player p) {
        p.resetDefaultSpeed();
    }
}
