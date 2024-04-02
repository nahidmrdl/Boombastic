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

    private String type;
    private GameItem item;

    private List<Entity> visitors;
    private Image image;

    public Cell(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.visitors = new ArrayList<>();
        this.type = type;

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

    public GameItem getItems(){
        return this.item;
    }

    public void setVisitor( List<Entity> visitors){
        this.visitors = visitors;
    }

    public void setItem(GameItem item){
        this.item = item;
    }

    public String getType(){
        return this.type;
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
