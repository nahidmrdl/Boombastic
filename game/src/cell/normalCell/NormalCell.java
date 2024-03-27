package cell.normalCell;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

public class NormalCell extends Cell {
    private Image image;
    public NormalCell(int row, int col, String type, Player owner, Image image) {
        super(row, col, type, owner);
        this.image = image;

    }
}
