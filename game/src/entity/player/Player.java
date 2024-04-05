package entity.player;

import cell.Cell;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setControls(HashMap<String, String> controls) {
        Controls = controls;
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

    public int getImageIndex() {
        return imageIndex;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public HashMap<String, String> getControls() {return Controls;}

    public void HandleAction(String keyCode, Cell[][] level ) throws IOException {
        int newX = this.x; // Assuming 'x' is horizontal (columns)
        int newY = this.y; // Assuming 'y' is vertical (rows)

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
                // Add more cases as needed for other actions
            }

            // Check if the new position is within bounds and not blocked
            if (newX >= 0 && newX < level[0].length && newY >= 0 && newY < level.length && !level[newY][newX].getType().equals("#") && !level[newY][newX].getType().equals("X")) {
                this.x = newX;
                this.y = newY;
            }
        }
    }

    public void placeBomb() throws IOException {
        this.gameMap.getMap()[this.y][this.x].addItem(new Bomb());
    }

    private String getKeyActionFromKeyCode(String keyCode, HashMap<String, String> playerControls) {
        for (Map.Entry<String, String> entry : playerControls.entrySet()) {
            if (String.valueOf(keyCode).equals(entry.getValue())) {
                return entry.getKey(); // This is the action to perform
            }
        }
        return null; // No action found for this keyCode
    }

    @Override
    public String toString() {
        return "Player name: " + name + ", Image index: " + imageIndex + ", Controls: " + Controls + ", X: " + x + ", Y: " + y;
    }
}
