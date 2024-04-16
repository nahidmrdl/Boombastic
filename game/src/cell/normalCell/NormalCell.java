package cell.normalCell;

import cell.Cell;
import entity.player.Player;
import item.GameItem;
import item.powerup.PowerUp;
import item.powerup.increasednumberffbombs.IncreasedNumberOfBombs;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class NormalCell extends Cell {
    private Image powerUpImage; // New attribute for the power-up image
    private boolean hasPowerUp; // Flag to indicate presence of a power-up

    boolean isStartingPoint = false;

    public NormalCell(int row, int col) {
        super(row, col);
        this.image = ResourceCollection.Images.GROUNDMAP1.getImage();
    }


    public void removeFinishedItems() {
        this.items.removeIf(item -> item.getFinishTime() < System.currentTimeMillis());
    }

    public void setRandomPowerUp() {
        if (Math.random() < 0.5) {
            IncreasedNumberOfBombs increasedNumberOfBombs = new IncreasedNumberOfBombs();
            increasedNumberOfBombs.setCell(this);
            this.items.add(increasedNumberOfBombs);
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



