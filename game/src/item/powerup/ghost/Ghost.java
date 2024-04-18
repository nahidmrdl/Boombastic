package item.powerup.ghost;

import cell.Cell;
import cell.normalCell.NormalCell;
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

        if(p.getX() < 0 || p.getY() < 0 || p.getX() >= getCell().getMap().getMap()[0].length || p.getY() >= getCell().getMap().getMap().length) {
            p.setDead(true);
            return;
        }

        // reversed x and y
        Cell cell = getCell().getMap().getCell(p.getY(), p.getX());

        if(!(cell instanceof NormalCell)) {
            p.setDead(true);
        }
    }
}
