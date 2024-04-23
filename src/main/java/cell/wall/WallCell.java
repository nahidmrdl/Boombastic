package cell.wall;

import cell.Cell;
import entity.player.Player;
import map.GameMap;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WallCell extends Cell {

    public WallCell(int row, int col, GameMap map) throws IOException {
        super(row, col, map);
        this.image = ResourceCollection.Images.WALLMAP1.getImage();
    }

}
