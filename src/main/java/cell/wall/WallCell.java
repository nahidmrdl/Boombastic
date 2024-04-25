package cell.wall;

import cell.Cell;
import entity.player.Player;
import map.GameMap;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents a wall cell in the game map.
 */
public class WallCell extends Cell {

    /**
     * Constructor for WallCell
     * @param row row of the cell
     * @param col column of the cell
     * @param map map that the cell is in
     * @throws IOException if the image file is not found
     */
    public WallCell(int row, int col, GameMap map) throws IOException {
        super(row, col, map);
        this.image = ResourceCollection.Images.WALLMAP1.getImage();
    }

}
