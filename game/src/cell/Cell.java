package cell;

import entity.Entity;
import entity.player.Player;
import item.GameItem;
import map.GameMap;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cell {
    private final int row;
    private final int col;

    private String type;
    private List<GameItem> items;





    private GameMap map;

    private List<Entity> visitors;
    private Image image;

    private Image foregroundImage;

    private boolean hasPowerUp;
    private String powerUpType;

    public Cell(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.visitors = new ArrayList<>();
        this.type = type;
        this.items = new ArrayList<>();

    }



    public void addItem(GameItem item){
        this.items.add(item);
    }

    public void setMap(GameMap map){
        this.map = map;
    }

    public GameMap getMap(){
        return this.map;
    }

    public void setForegroundImage(Image image) {
        this.foregroundImage = image;
    }

    public Image getForegroundImage() {
        return this.foregroundImage;
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

    public void setVisitor( List<Entity> visitors){
        this.visitors = visitors;
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
