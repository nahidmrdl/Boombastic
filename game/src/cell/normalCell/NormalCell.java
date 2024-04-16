package cell.normalCell;

import cell.Cell;
import entity.Entity;
import entity.player.Player;
import item.GameItem;
import item.powerup.PowerUp;
import item.powerup.detonator.Detonator;
import item.powerup.increasedblastrange.IncreasedBlastRange;
import item.powerup.increasednumberffbombs.IncreasedNumberOfBombs;
import item.powerup.invincibility.Invincibility;
import item.powerup.rollerskate.RollerSkate;
import map.GameMap;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class NormalCell extends Cell {
    private Image powerUpImage; // New attribute for the power-up image
    private boolean hasPowerUp; // Flag to indicate presence of a power-up

    boolean isStartingPoint = false;

    public NormalCell(int row, int col, GameMap map) {
        super(row, col, map);
        this.image = ResourceCollection.Images.GROUNDMAP1.getImage();
    }


    public void removeFinishedItems() {
        this.items.removeIf(item -> item.getFinishTime() != 0 && item.getFinishTime()  < System.currentTimeMillis());
    }

    public void setRandomPowerUp() {
        // change from 1 to 0.5 to change the chance of power-up spawn
        if (Math.random() < 1) {
            // power-up options list
            //PowerUp[] powerUps = new PowerUp[]{new IncreasedNumberOfBombs(), new RollerSkate(), new IncreasedBlastRange()};
            PowerUp[] powerUps = new PowerUp[]{ new Invincibility()};
            PowerUp powerUp = powerUps[(int) (Math.random() * powerUps.length)];
            powerUp.setCell(this);
            this.items.add(powerUp);
        }
    }

    public void CollectPowerUp() {
        if(!this.getVisitors().isEmpty() && !this.getItems().isEmpty()) {
            for (Entity visitor : this.getVisitors()) {
                if (visitor instanceof Player) {
                    Player player = (Player) visitor;
                    for (GameItem item : this.getItems()) {
                        if (item instanceof PowerUp) {
                            PowerUp powerUp = (PowerUp) item;
                            player.addPowerUp(powerUp);
                            powerUp.apply(player);
                            this.items.remove(item);
                            break;
                        }
                    }
                }
            }
        }
    }


    // Getter and setter for the power-up image
    public Image getPowerUpImage() {
        return powerUpImage;
    }

    public void setPowerUpImage(Image powerUpImage) {
        this.powerUpImage = powerUpImage;
    }

    // Getter and setter for the hasPowerUp flag
    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    public void setHasPowerUp(boolean hasPowerUp) {
        this.hasPowerUp = hasPowerUp;
    }

    public void setStartingPoint(boolean isStartingPoint) {
        this.isStartingPoint = isStartingPoint;
    }

    public boolean isStartingPoint() {
        return isStartingPoint;
    }
}



