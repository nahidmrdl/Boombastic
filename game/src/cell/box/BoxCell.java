package cell.box;

import cell.Cell;
import entity.player.Player;
import item.powerup.PowerUp;
import map.GameMap;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BoxCell extends Cell {
    private Image powerUpImage;
    private Player owner;

    private boolean hasPowerUp;
    public BoxCell(int row, int col, GameMap map) {
        super(row, col, map);
        this.image = ResourceCollection.Images.BOXMAP1.getImage();
        this.owner = null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public Player getOwner() {
        return this.owner;
    }

    public void setPowerUp(Image powerUpImage) {
        this.powerUpImage = powerUpImage;
        this.hasPowerUp = true;
    }


    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    public Image getPowerUpImage() {
        return powerUpImage;
    }

}
