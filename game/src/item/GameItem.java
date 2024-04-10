package item;

import cell.Cell;

import java.awt.*;


public abstract class GameItem {
    private Image baseImage;
    private Image image;
    private long finishTime;
    private Cell cell;

    public GameItem(Image image) {
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public void setCell(Cell c){
        this.cell = c;
    }

    public Cell getCell(){
        return this.cell;
    }

    public long getFinishTime(){
        return this.finishTime;
    }

    public Image getBaseImage(){
        return this.baseImage;
    }

    public Image getImage(){
        return this.image;
    }
}
