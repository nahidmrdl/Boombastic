package cell.box;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

public class BoxCell extends Cell {
    private Image image;
    public BoxCell(int row, int col,String type, Player owner, Image image) {
        super(row, col, type, owner);
        this.image = image;
    }

}
