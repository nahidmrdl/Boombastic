package cell.normalCell;

import cell.Cell;
import entity.Entity;
import entity.player.Player;
import item.GameItem;
import item.bomb.Bomb;
import item.curse.Curse;
import item.curse.bombblastreduction.BombBlastReduction;
import item.curse.instantbombplacement.InstantBombPlacement;
import item.curse.lowspeed.LowSpeed;
import item.curse.prohibitbombs.ProhibitBombs;
import item.powerup.PowerUp;
import item.powerup.detonator.Detonator;
import item.powerup.ghost.Ghost;
import item.powerup.increasedblastrange.IncreasedBlastRange;
import item.powerup.increasednumberffbombs.IncreasedNumberOfBombs;
import item.powerup.invincibility.Invincibility;
import item.powerup.placeobstacle.PlaceObstacle;
import item.powerup.rollerskate.RollerSkate;
import map.GameMap;
import util.ResourceCollection;

import java.util.Iterator;


/**
 * Represents a normal cell in the game map.
 */
public class NormalCell extends Cell {

    boolean isStartingPoint = false;

    /**
     * Constructor for NormalCell
     * @param row row of the cell
     * @param col column of the cell
     * @param map map that the cell is in
     */
    public NormalCell(int row, int col, GameMap map) {
        super(row, col, map);
        this.image = ResourceCollection.Images.GROUNDMAP1.getImage();
    }

    /**
     * Removes the items that have finished their time
     */
    public void removeFinishedItems() {
        this.items.removeIf(item -> item.getFinishTime() != 0 && item.getFinishTime()  < System.currentTimeMillis());
    }

    /**
     * Sets a random item to the cell
     */
    public void setRandomItem() {
        double random = Math.random();
        if (random < 0.33) {
            // 33% chance to do nothing
            return;
        } else if (random < 0.66) {
            // 33% chance to place a powerup
            setRandomPowerUp();
        } else {
            // 33% chance to place a curse
            setRandomCurse();
        }
    }

    /**
     * Sets a random power-up to the cell
     */
    private void setRandomPowerUp() {
        PowerUp[] powerUps = new PowerUp[]{
                new IncreasedNumberOfBombs(),
                new RollerSkate(),
                new IncreasedBlastRange(),
                new Invincibility(),
                new Detonator(),
                new Ghost(),
                new PlaceObstacle()
        };
        PowerUp powerUp = powerUps[(int) (Math.random() * powerUps.length)];
        powerUp.setCell(this);
        this.items.add(powerUp);
    }

    /**
     * Sets a random curse to the cell
     */
    private void setRandomCurse() {
        Curse[] curses = new Curse[]{
                new BombBlastReduction(),
                new LowSpeed(),
                new ProhibitBombs(),
                new InstantBombPlacement()
        };
        Curse curse = curses[(int) (Math.random() * curses.length)];
        curse.setCell(this);
        this.items.add(curse);
    }


    /**
     * Collects the items in the cell
     */
   public void collectItems() {
        if(!this.getVisitors().isEmpty() && !this.getItems().isEmpty()) {
            for (Entity visitor : this.getVisitors()) {
                if (visitor instanceof Player) {
                    Player player = (Player) visitor;
                    Iterator<GameItem> itemIterator = this.getItems().iterator();
                    while (itemIterator.hasNext()) {
                        GameItem item = itemIterator.next();
                        if (item instanceof PowerUp) {
                            PowerUp powerUp = (PowerUp) item;
                            System.out.println("Player collected a power-up" + item.getClass().getSimpleName());
                            player.addPowerUp(powerUp);
                            powerUp.apply(player);
                            itemIterator.remove();
                        } else if (item instanceof Curse) {
                            Curse curse = (Curse) item;
                            player.addCurse(curse);
                            curse.apply(player);
                            itemIterator.remove();
                        }
                    }
                }
            }
        }
    }


    /**
     * Sets the cell as a starting point
     * @param isStartingPoint true if the cell is a starting point
     */
    public void setStartingPoint(boolean isStartingPoint) {
        this.isStartingPoint = isStartingPoint;
    }

    /**
     * Checks if the cell is a starting point
     * @return true if the cell is a starting point
     */
    public boolean isStartingPoint() {
        return isStartingPoint;
    }
}



