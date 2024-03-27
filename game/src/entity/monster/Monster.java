package entity.monster;

import entity.Entity;
import map.Map;

import java.awt.*;

public class Monster extends Entity {

    private Image baseImage;
    private String direction;
    private int speed;

    public Monster(int x, int y, Map map, int speed) {
        super(x, y, map);
        this.speed = speed;

    }
}
