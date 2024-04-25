package entity;

import cell.Cell;
import map.GameMap;

import java.awt.*;

/**
 * Represents an entity in the game map.
 */
public abstract class Entity {
    protected GameMap gameMap;
    private Image baseImage;
    private Image image;
    protected int x;
    protected int y;

    /**
     * Constructor for Entity
     * @param x x-coordinate of the entity
     * @param y y-coordinate of the entity
     * @param gameMap map that the entity is in
     */
    public Entity(int x, int y, GameMap gameMap){
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    // protected abstract void move(int x, int y);

    /**
     * Gets the cell that the entity is positioned on
     * @return the cell that the entity is positioned on
     */
    public Cell getCellPositioned(){
        return this.gameMap.getCell(x,y);
    }

//    public boolean move(){}

    /**
     * Gets the image of the entity
     * @return
     */
    public Image getImage(){
        return this.image;
    }

    /**
     * Gets the base image of the entity
     * @return
     */
    public Image getBaseImage(){
        return this.baseImage;
    }
}
