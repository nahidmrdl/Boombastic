package cell;

import entity.Entity;
import entity.player.Player;
import item.GameItem;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Cell {
    private final int row;
    private final int col;
    private Player owner;

    private GameItem item;

    private ArrayList<Entity> visitors;
    private Image image;

    public Cell(int row, int col, Player owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
        this.visitors = new ArrayList<>();

    }

    public int getX(){
        return this.row;
    }
    public int getY(){
        return this.col;
    }

    public ArrayList<Entity> getVisitors(){
        return this.visitors;
    }

    public GameItem getItems(){
        return this.item;
    }

    public void setVisitor(ArrayList<Entity> visitors){
        this.visitors = visitors;
    }

    public void setItem(GameItem item){
        this.item = item;
    }

}
