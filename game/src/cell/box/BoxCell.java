package cell.box;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BoxCell extends Cell {
    private Image image;
    private Player owner;
    public BoxCell(int row, int col,String type) throws IOException {
        super(row, col, type);
        this.image = ImageIO.read(new File("src\\assets\\mapAssets\\map1\\map1box.png"));;
        this.owner = owner;

    }

}
