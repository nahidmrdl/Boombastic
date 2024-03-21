package entity.player;

import entity.Entity;

import java.util.HashMap;

public class Player extends Entity {
    private String name;
    private int imageIndex;
    //private HashMap<String, String> Controls;

    public Player(String name, int imageIndex) {
        this.name = name;
        this.imageIndex = imageIndex;
    }

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
