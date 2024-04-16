package map;

import cell.Cell;
import item.GameItem;
import levels.LevelReader;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private Cell[][] map;
    private Image image;
    private String name;




    public GameMap(Image image, String name, Integer mapIndex){
        try {
            Cell[][] mapCell = LevelReader.readLevelFromFile(STR."src/levels/\{mapIndex}.txt");
            this.map = mapCell;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                cell.setMap(this);
            }
        }
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
