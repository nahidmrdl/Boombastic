package cell;

import entity.Entity;
import entity.player.Player;
import item.GameItem;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int row;
    private final int col;
    protected List<GameItem> items;

    private List<Entity> visitors;
    protected Image image;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.visitors = new ArrayList<>();
        this.items = new ArrayList<>();

    }

    public void addItem(GameItem item){
        this.items.add(item);
    }

    public int getX(){
        return this.row;
    }
    public int getY(){
        return this.col;
    }

    public List<Entity> getVisitors(){
        return this.visitors;
    }

    public List<GameItem> getItems(){
        return this.items;
    }

    protected void setVisitor( List<Entity> visitors){
        this.visitors = visitors;
    }
    public Image getImage(){
        return this.image;
    }

//    public boolean isPlayerAndMonsterOnCell() {
//        boolean playerFound = false;
//        boolean monsterFound = false;
//
//        for (Entity visitor : visitors) {
//            if(visitor.type.equals("Player")){
//                playerFound = true;
//            }
//            if(visitor.type.equals("Monster")){
//                monsterFound = true;
//            }
//        }
//
//        return playerFound && monsterFound;
//    }

}
