package cell.box;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BoxCell extends Cell {
    private Image image;
    private Image powerUpImage;
    private Player owner;
    private boolean hasPowerUp;
    public BoxCell(int row, int col,String type) throws IOException {
        super(row, col, type);
        // this.image = ImageIO.read(new File("src\\assets\\mapAssets\\map1\\map1box.png"));;
        this.owner = owner;
        this.powerUpImage = ImageIO.read(new File("src/assets/icons/powerupicon.png"));
        this.hasPowerUp = new Random().nextDouble() < 0.5;
    }

    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    public Image getPowerUpImage() {
        return powerUpImage;
    }

}
