package cell.normalCell;

import cell.Cell;
import entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NormalCell extends Cell {

    boolean isStartingPoint = false;

    public NormalCell(int row, int col) throws IOException {
        super(row, col);
        this.image = ImageIO.read(new File("src/assets/mapAssets/map1/map1walkable.png"));
    }

    public void setStartingPoint(boolean isStartingPoint) {
        this.isStartingPoint = isStartingPoint;
    }

    public boolean isStartingPoint() {
        return isStartingPoint;
    }

    public void removeFinishedItems() {
        this.items.removeIf(item -> item.getFinishTime() < System.currentTimeMillis());
    }
}
