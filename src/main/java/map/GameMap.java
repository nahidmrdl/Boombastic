package map;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import item.bomb.Bomb;
import levels.LevelReader;

import java.awt.*;
import java.io.IOException;

/**
 * Represents a game map.
 */
public class GameMap {
    private Cell[][] map;
    private Image image;
    private String name;

    /**
     * Constructor for GameMap
     * @param image image of the map
     * @param name name of the map
     * @param mapIndex index of the map
     */
    public GameMap(Image image, String name, Integer mapIndex){
        try {
            Cell[][] mapCell = LevelReader.readLevelFromFile("src/main/java/levels/" + mapIndex + ".txt");
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

    /**
     * Returns the map
     * @return the map
     */
    public Cell[][] getMap(){
        return this.map;
    }

    /**
     * Returns the cell at the given coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     * @return the cell at the given coordinates
     */
    public Cell getCell(int x, int y){
        return this.map[x][y];
    }

    /**
     * Detonates all bombs of the player
     * @param player
     */
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

    /**
     * Returns the image of the map
     * @return the image of the map
     */
    public Image getImage(){
        return this.image;
    }

    /**
     * Returns the name of the map
     * @return the name of the map
     */
    public String getName(){
        return this.name;
    }
}
