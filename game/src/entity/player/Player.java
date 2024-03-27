package entity.player;

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

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    @Override
    public String toString() {
        return "Player name: " + name + ", Image index: " + imageIndex;
    }
}
