package cell.wall;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

public class WallCell extends Cell {
    private Image image;
    public WallCell(int row, int col, String type, Player owner, Image image) {
        super(row, col, type, owner);
        this.image = image;
    }
}
