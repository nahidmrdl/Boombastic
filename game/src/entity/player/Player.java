package entity.player;

import cell.Cell;
import cell.box.BoxCell;
import cell.wall.WallCell;
import entity.Entity;
import item.bomb.Bomb;
import map.GameMap;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    private String name;
    private int imageIndex;

    private GameMap gameMap;

    private Image Image;

    private HashMap<String, String> Controls;

    public Player(int x, int y, GameMap gameMap, String name, int imageIndex, HashMap<String, String> Controls, Image image) {
        super(x, y, gameMap);
        this.name = name;
        this.Image = image;
        this.Controls = Controls;
        this.imageIndex = imageIndex;
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public void setImage(Image image) {
        Image = image;
    }

    public Image getImage() {
        return Image;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public HashMap<String, String> getControls() {return Controls;}

    /**
     * This method is used to handle the player action
     * @param keyCode
     * @param level
     * @throws IOException
     */
    public void HandleAction(String keyCode, Cell[][] level ) throws IOException {
        int newX = this.x;
        int newY = this.y;

        HashMap<String, String> playerControls = this.getControls();

        System.out.println(playerControls);
        System.out.println(keyCode);

        String action = getKeyActionFromKeyCode(keyCode, playerControls);

        if (action != null) {
            switch (action) {
                case "UP":
                    // Move up
                    newY = this.y - 1;
                    break;
                case "DOWN":
                    // Move down
                    newY = this.y + 1;
                    break;
                case "LEFT":
                    // Move left
                    newX = this.x - 1;
                    break;
                case "RIGHT":
                    // Move right
                    newX = this.x + 1;
                    break;
                case "BOMB":
                    // Place bomb action
                    placeBomb();
                    break;
            }

            // Check if the new position is valid

            this.move(newX, newY);
        }
    }

    protected void move(int newX, int newY){

        Cell[][] level = this.gameMap.getMap();

        if (
                        newX >= 0
                        && newY >= 0
                        && newX < level[0].length
                        && newY < level.length
                        && !(level[newY][newX] instanceof WallCell)
                        && !(level[newY][newX] instanceof BoxCell)
        ) {
            this.x = newX;
            this.y = newY;
        }
    }

    public void placeBomb() throws IOException {
        this.gameMap.getMap()[this.y][this.x].addItem(new Bomb());
    }

    /**
     * This method is used to get corresponding action from the keyCode
     * @param keyCode
     * @param playerControls
     * @return
     */
    private String getKeyActionFromKeyCode(String keyCode, HashMap<String, String> playerControls) {
        for (Map.Entry<String, String> entry : playerControls.entrySet()) {
            if (String.valueOf(keyCode).equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * This method is used to get the player object as a string (mostly for debugging)
     * @return
     */
    @Override
    public String toString() {
        return "Player name: " + name + ", Image index: " + imageIndex + ", Controls: " + Controls + ", X: " + x + ", Y: " + y;
    }
}
