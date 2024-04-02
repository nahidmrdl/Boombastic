package map;

import cell.Cell;
import item.GameItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private Cell[][] map;
    private Image image;
    private String name;




    public GameMap(Cell[][] map, Image image, String name){
        this.map = map;
        this.image = image;
        this.name = name;

    }

//    public void resetMap(){}

//    public void shuffleBonuses(){}
    public Cell[][] getMap(){
        return this.map;
    }

    public Cell getCell(int x, int y){
        return this.map[x][y];
    }



    public Image getImage(){
        return this.image;
    }

    public String getName(){
        return this.name;
    }



}
