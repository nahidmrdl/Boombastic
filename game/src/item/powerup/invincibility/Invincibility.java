package item.powerup.invincibility;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

public class Invincibility extends PowerUp {
    public Invincibility() {
        super(ResourceCollection.Images.INVINCIBLE_POWERUP.getImage());
        setFinishTime(System.currentTimeMillis() + 10000);
    }

    @Override
    public void apply(Player p) {
        p.setInvincible(true);
    }

    @Override
    public void reset(Player p) {
        p.setInvincible(false);
    }
}
