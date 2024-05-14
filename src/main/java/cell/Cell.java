package cell;

import entity.Entity;
import item.GameItem;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a cell in the game map.
 */
public class Cell {
    private final int row;
    private final int col;

    protected List<GameItem> items;

    private GameMap map;

    private List<Entity> visitors;
    protected Image image;

    private Image foregroundImage;

    private boolean hasPowerUp;
    private String powerUpType;

    /**
     * Constructor for Cell
     * @param row row of the cell
     * @param col column of the cell
     * @param map map that the cell is in
     */
    public Cell(int row, int col, GameMap map){
        this.row = row;
        this.col = col;
        this.map = map;
        this.visitors = new ArrayList<>();
        this.items = new ArrayList<>();
    }

/**
     * Adds an item to the cell
     * @param item item to be added
     */
    public void addItem(GameItem item){
        this.items.add(item);
    }

    /**
     * Sets the map of the cell
     * @param map map to be set
     */
    public void setMap(GameMap map){
        this.map = map;
    }

    /**
     * Gets the map of the cell
     * @return map of the cell
     */
    public GameMap getMap(){
        return this.map;
    }

    /**
     * Sets the foreground image of the cell
     * @param image image to be set
     */
    public void setForegroundImage(Image image) {
        this.foregroundImage = image;
    }

    /**
     * Gets the foreground image of the cell
     * @return foreground image of the cell
     */
    public Image getForegroundImage() {
        return this.foregroundImage;
    }

    /**
     * Gets the row of the cell
     * @return row of the cell
     */
    public int getX(){
        return this.row;
    }

    /**
     * Gets the column of the cell
     * @return column of the cell
     */
    public int getY(){
        return this.col;
    }

    /**
     * Gets the visitors of the cell
     * @return visitors of the cell
     */
    public List<Entity> getVisitors(){
        return this.visitors;
    }

    /**
     * Gets the items of the cell
     * @return items of the cell
     */
    public List<GameItem> getItems(){
        return this.items;
    }

    /**
     * Adds a visitor to the cell
     * @param visitor visitor to be added
     */
    public void addVisitor( Entity visitor){
        this.visitors.add(visitor);
    }

    /**
     * Gets the image of the cell
     * @return image of the cell
     */
    public Image getImage(){
        return this.image;
    }

}
