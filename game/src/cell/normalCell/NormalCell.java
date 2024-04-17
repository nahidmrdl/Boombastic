package cell.normalCell;

import cell.Cell;
import entity.Entity;
import entity.player.Player;
import item.GameItem;
import item.bomb.Bomb;
import item.curse.Curse;
import item.curse.bombblastreduction.BombBlastReduction;
import item.curse.lowspeed.LowSpeed;
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


public class NormalCell extends Cell {

    boolean isStartingPoint = false;

    public NormalCell(int row, int col, GameMap map) {
        super(row, col, map);
        this.image = ResourceCollection.Images.GROUNDMAP1.getImage();
    }

    public void removeFinishedItems() {
        this.items.removeIf(item -> item.getFinishTime() != 0 && item.getFinishTime()  < System.currentTimeMillis());
    }

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

    private void setRandomCurse() {
        Curse[] curses = new Curse[]{
                new BombBlastReduction(),
                new LowSpeed()
        };
        Curse curse = curses[(int) (Math.random() * curses.length)];
        curse.setCell(this);
        this.items.add(curse);
    }


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


    public void setStartingPoint(boolean isStartingPoint) {
        this.isStartingPoint = isStartingPoint;
    }

    public boolean isStartingPoint() {
        return isStartingPoint;
    }
}



