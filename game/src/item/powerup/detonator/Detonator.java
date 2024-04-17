package item.powerup.detonator;

import cell.Cell;
import entity.player.Player;
import item.GameItem;
import item.bomb.Bomb;
import item.powerup.PowerUp;
import map.GameMap;
import util.ResourceCollection;
import gui.GameTopPanelGUI;

public class Detonator extends PowerUp {

    public Detonator() {
        super(ResourceCollection.Images.DETONATOR_POWERUP.getImage());
    }

    @Override
    public void apply(Player p) {
        p.setDetonator(true);
    }

    @Override
    public void reset(Player p) {
        p.setDetonator(false);
    }
}
