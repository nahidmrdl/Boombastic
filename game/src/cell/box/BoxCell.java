package cell.box;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

public class BoxCell extends Cell {
    private Image image;
    private Player owner;
    public BoxCell(int row, int col,String type, Player owner, Image image) {
        super(row, col, type);
        this.image = image;
        this.owner = owner;

    }

}
