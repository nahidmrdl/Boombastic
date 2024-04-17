package entity.monster.monstertypes;

import java.awt.*;
import java.util.Date;
import java.util.Random;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import entity.monster.Monster;
import map.GameMap;

import javax.swing.*;
// TODO: NOT READY!!! CONFUSED MONSTER
public class ConfusedMonster extends Monster {
    private Image baseImage;
    private int direction;
    private Date lastMoveTime = new Date();

    private int speed;

    // Constructor
    public ConfusedMonster(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
        this.speed = 450;
    }



    private void findValidStartingPosition() {
        int maxX = gameMap.getMap().length;
        int maxY = gameMap.getMap()[0].length;
        Random rand = new Random();
        do {
            this.x = rand.nextInt(maxY);
            this.y = rand.nextInt(maxX);
        } while (!(gameMap.getMap()[this.y][this.x] instanceof NormalCell));
        System.out.println(this.x);
        System.out.println(this.y);

    }

    public void moveRandomly() {
        if(lastMoveTime.getTime() + speed < new Date().getTime()) {
            lastMoveTime = new Date();
            Random rand = new Random();
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            int newX = this.x + dx[direction];
            int newY = this.y + dy[direction];

            if (newX >= 0 && newX < gameMap.getMap()[0].length &&
                    newY >= 0 && newY < gameMap.getMap().length &&
                    gameMap.getMap()[newY][newX] instanceof NormalCell) {
                this.x = newX;
                this.y = newY;
            } else {
                direction = rand.nextInt(4);
            }
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    // Method to check if next to player
    public boolean isNextToPlayer(int px, int py) {
        return Math.abs(this.x - px) <= 1 && Math.abs(this.y - py) <= 1;
    }
}
