package item;

import cell.Cell;
import entity.player.Player;

import java.awt.*;


public abstract class GameItem {
    protected Image baseImage;
    protected Image image;
    protected long finishTime;
    protected Cell cell;

    private Player owner;


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

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public Image getImage(){
        return this.image;
    }
}
