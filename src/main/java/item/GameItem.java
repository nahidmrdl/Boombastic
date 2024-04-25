package item;

import cell.Cell;
import entity.player.Player;

import java.awt.*;

/**
 * Represents an item in the game map.
 */
public abstract class GameItem {
    protected Image baseImage;
    protected Image image;
    protected long finishTime;
    protected Cell cell;

    protected boolean isBlown;
    private Player owner;

    /**
     * Checks if the item is blown
     * @return true if the item is blown, false otherwise
     */
    public boolean isBlown(){
        return this.isBlown;
    }

    /**
     * Sets the item to be blown
     * @param state
     */
    public void setBlown(boolean state){
        this.isBlown = true;
    }



    public GameItem(Image image) {
        this.image = image;
    }
    /**
     * Sets the image of the item
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Sets the finish time of the item
     * @param finishTime
     */
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * Sets the cell that the item is positioned on
     * @param c
     */
    public void setCell(Cell c){
        this.cell = c;
    }

    /**
     * Gets the cell that the item is positioned on
     * @return the cell that the item is positioned on
     */
    public Cell getCell(){
        return this.cell;
    }

    /**
     * Gets the finish time of the item
     * @return
     */
    public long getFinishTime(){
        return this.finishTime;
    }

    /**
     * Gets the base image of the item
     * @return
     */
    public Image getBaseImage(){
        return this.baseImage;
    }

    /**
     * Sets the owner of the item
     * @param owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner of the item
     * @return
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Gets the image of the item
     * @return
     */
    public Image getImage(){
        return this.image;
    }
}
