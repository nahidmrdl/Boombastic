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

/**
 * Represents a box cell in the game map.
 */
public class BoxCell extends Cell {
    private Image powerUpImage;
    private Player owner;

    /**
     * Constructor for BoxCell
     * @param row row of the cell
     * @param col column of the cell
     * @param map map that the cell is in
     */
    public BoxCell(int row, int col, GameMap map) {
        super(row, col, map);
        this.image = ResourceCollection.Images.PLACED_BOX.getImage();
        this.owner = null;
    }

    /**
     * Sets an owner to the box
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner of the box
     */
    public Player getOwner() {
        return this.owner;
    }

}
