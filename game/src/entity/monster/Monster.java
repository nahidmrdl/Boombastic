package entity.monster;

import entity.Entity;
import map.GameMap;

import java.awt.*;

public class Monster extends Entity {

    private Image baseImage;
    private String direction;
    private int speed;

    public Monster(int x, int y, GameMap gameMap, int speed) {
        super(x, y, gameMap);
        this.speed = speed;

    }
}
