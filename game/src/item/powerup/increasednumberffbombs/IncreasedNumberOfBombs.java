package item.powerup.increasednumberffbombs;

import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

import java.awt.*;

public class IncreasedNumberOfBombs extends PowerUp {

    public IncreasedNumberOfBombs() {
        super(ResourceCollection.Images.POWER_BOMBSTAGE1.getImage());
    }

    @Override
    public void apply(Player p) {

    }

    @Override
    public void reset(Player p) {

    }
}
