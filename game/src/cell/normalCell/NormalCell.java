package cell.normalCell;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class NormalCell extends Cell {
    private Image image;
    private Image powerUpImage; // New attribute for the power-up image
    private boolean hasPowerUp; // Flag to indicate presence of a power-up

    boolean isStartingPoint = false;

    public NormalCell(int row, int col, String type) throws IOException {
        super(row, col, type);
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



