package entity;

import cell.Cell;
import map.GameMap;

import java.awt.*;

public abstract class Entity {
    private GameMap gameMap;
    private Image baseImage;
    private Image image;
    protected int x;
    protected int y;

    public Entity(int x, int y, GameMap gameMap){
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

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
