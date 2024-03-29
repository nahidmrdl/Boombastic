package entity;

import cell.Cell;
import map.Map;

import java.awt.*;

public abstract class Entity {
    private Map map;
    private Image baseImage;
    private Image image;
    protected int x;
    protected int y;

    public Entity(int x, int y, Map map){
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public Cell getCellPositioned(){
        return this.map.getCell(x,y);
    }

//    public boolean move(){}

    public Image getImage(){
        return this.image;
    }
    public Image getBaseImage(){
        return this.baseImage;
    }
}
