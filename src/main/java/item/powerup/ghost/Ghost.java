package item.powerup.ghost;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import item.powerup.PowerUp;
import util.ResourceCollection;

/**
 * Represents a power-up that makes the player invincible for some time.
 */
public class Ghost extends PowerUp {

    /**
     * Constructor for Ghost
     */
    public Ghost() {
        super(ResourceCollection.Images.GHOST_POWERUP.getImage());
        setFinishTime(System.currentTimeMillis() + 10000);
    }

    /**
     * Applies the power-up to the player
     * @param p player to apply the power-up to
     */
    @Override
    public void apply(Player p) {
        p.setGhost(true);
    }

    /**
     * Resets the power-up for the player
     * @param p player to reset the power-up for
     */
    @Override
    public void reset(Player p) {

        p.setGhost(false);

        setFinishTime(System.currentTimeMillis());

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
