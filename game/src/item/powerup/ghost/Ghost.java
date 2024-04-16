package item.powerup.ghost;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

public class Ghost extends PowerUp {

    public Ghost() {
        super(ResourceCollection.Images.GHOST_POWERUP.getImage());
        setFinishTime(System.currentTimeMillis() + 10000);
    }

    @Override
    public void apply(Player p) {
        p.setGhost(true);
    }

    @Override
    public void reset(Player p) {
        p.setGhost(false);
    }
}
