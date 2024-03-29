package entity.player;

import cell.Cell;
import entity.Entity;
import map.Map;

import java.util.HashMap;

public class Player extends Entity {
    private String name;
    private int imageIndex;

    public Player(int x, int y, Map map, String name, int imageIndex) {
        super(x, y, map);
        this.imageIndex = imageIndex;

    }
    //private HashMap<String, String> Controls;



    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void Move(String keyNumber, Cell[][] level) {
        int newX = this.x; // Assuming x is horizontal (columns)
        int newY = this.y; // Assuming y is vertical (rows)

        switch (keyNumber) {
            case "87": // Move up
                newY = this.y - 1;
                break;
            case "83": // Move down
                newY = this.y + 1;
                break;
            case "65": // Move left
                newX = this.x - 1;
                break;
            case "68": // Move right
                newX = this.x + 1;
                break;
        }

        if (newX >= 0 && newX < level[0].length && newY >= 0 && newY < level.length && !level[newY][newX].getType().equals("#") && !level[newY][newX].getType().equals("X")) {
            this.x = newX;
            this.y = newY;
        }
    }




    @Override
    public String toString() {
        return "Player name: " + name + ", Image index: " + imageIndex;
    }
}
