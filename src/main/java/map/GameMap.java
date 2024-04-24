package map;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import item.GameItem;
import item.bomb.Bomb;
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
            //(STR."src/levels/\{mapIndex}.txt");
            Cell[][] mapCell = LevelReader.readLevelFromFile("src/main/java/levels/" + mapIndex + ".txt");
            this.map = mapCell;
        } catch (IOException e) {
            // log the path that caused the error
            System.out.println("src/main/java/levels/" + mapIndex + ".txt");
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

    public Cell[][] getMap(){
        return this.map;
    }

    public Cell getCell(int x, int y){
        return this.map[x][y];
    }

    public void DetonatePlayerBombs(Player player) {
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if (cell instanceof NormalCell) {
                    cell.getItems().forEach(item -> {
                        if (item instanceof Bomb) {
                            Bomb bomb = (Bomb) item;
                            if (bomb.getOwner() == player) {
                                bomb.invokeDetonateAnimation();
                            }
                        }
                    });
                }
            }
        }
    }

    public Image getImage(){
        return this.image;
    }

    public String getName(){
        return this.name;
    }



}
