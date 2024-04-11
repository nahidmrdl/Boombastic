package cell.wall;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WallCell extends Cell {

    public WallCell(int row, int col) throws IOException {
        super(row, col);
        this.image = ImageIO.read(new File("src/assets/mapAssets/map1/map1wall.png"));
    }

}
