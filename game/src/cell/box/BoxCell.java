package cell.box;

import cell.Cell;
import entity.player.Player;
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
    public BoxCell(int row, int col) throws IOException {
        super(row, col);
        this.image = ImageIO.read(new File("src/assets/mapAssets/map1/map1box.png"));;
        this.owner = null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public Player getOwner() {
        return this.owner;
    }



    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    public Image getPowerUpImage() {
        return powerUpImage;
    }

}
