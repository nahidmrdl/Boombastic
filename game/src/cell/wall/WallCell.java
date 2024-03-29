package cell.wall;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

public class WallCell extends Cell {
    private Image image;
    public WallCell(int row, int col, String type, Image image) {
        super(row, col, type);
        this.image = image;

    }
}
