package cell.box;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BoxCell extends Cell {
    private Player owner;
    public BoxCell(int row, int col, String type) throws IOException {
        super(row, col, type);
        this.image = ImageIO.read(new File("src/assets/mapAssets/map1/map1box.png"));;
        this.owner = null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

}
