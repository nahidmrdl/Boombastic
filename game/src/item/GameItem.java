package item;

import cell.Cell;

import java.awt.*;


public abstract class GameItem {
    protected Image baseImage;
    protected Image image;
    protected long finishTime;
    protected Cell cell;


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
