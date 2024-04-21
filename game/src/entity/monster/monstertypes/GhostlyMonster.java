package entity.monster.monstertypes;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import entity.monster.Monster;
import entity.player.Player;
import map.GameMap;

public class GhostlyMonster extends Monster {
    private Image baseImage;
    private int direction;
    private Date lastMoveTime = new Date();

    private int speed;

    // Constructor
    public GhostlyMonster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap, players);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
        this.speed = 700;
    }



    private void findValidStartingPosition() {
        int maxX = gameMap.getMap().length;
        int maxY = gameMap.getMap()[0].length;
        Random rand = new Random();
        do {
            this.x = rand.nextInt(maxY);
            this.y = rand.nextInt(maxX);
        } while (!(gameMap.getMap()[this.y][this.x] instanceof NormalCell));


    }


    public void moveRandomly() {
        if(lastMoveTime.getTime() + speed < new Date().getTime()) {
            lastMoveTime = new Date();
            Random rand = new Random();
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            int newX = this.x + dx[direction];
            int newY = this.y + dy[direction];

            if (newX > 0 && newX < gameMap.getMap()[0].length - 1 &&
                    newY > 0 && newY < gameMap.getMap().length - 1) {
                this.gameMap.getMap()[this.y][this.x].getVisitors().remove(this);

                this.x = newX;
                this.y = newY;
                this.gameMap.getMap()[this.y][this.x].addVisitor(this);


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

