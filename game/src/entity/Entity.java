package entity;

import cell.Cell;
import map.GameMap;

import java.awt.*;

public abstract class Entity {
    protected GameMap gameMap;
    private Image baseImage;
    private Image image;
    protected int x;
    protected int y;

    public Entity(int x, int y, GameMap gameMap){
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

//    protected abstract void move(int x, int y);
    public Cell getCellPositioned(){
        return this.gameMap.getCell(x,y);
    }

//    public boolean move(){}

    public Image getImage(){
        return this.image;
    }
    public Image getBaseImage(){
        return this.baseImage;
    }
}
