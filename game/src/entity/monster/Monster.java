package entity.monster;

import java.awt.*;
import java.util.Random;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import map.GameMap;

   abstract public class Monster extends Entity {
    private Image baseImage;
    private int direction;
    private int speed;

    // Constructor
    public Monster(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
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
        Random rand = new Random();
        int tries = 0;
        while (tries < 4) {
            int newX = this.x + (direction == 1 ? 1 : direction == 0 ? -1 : 0);
            int newY = this.y + (direction == 2 ? -1 : direction == 3 ? 1 : 0);

            if (newX >= 0 && newX < gameMap.getMap()[0].length &&
                    newY >= 0 && newY < gameMap.getMap().length &&
                    gameMap.getMap()[newY][newX] instanceof NormalCell) {
                this.x = newX;
                this.y = newY;
                break;
            } else {
                direction = rand.nextInt(4); // Choose a new direction if the current one is blocked
                tries++;
            }
        }
    }

    // Method to check if next to player
    public boolean isNextToPlayer(int px, int py) {
        return Math.abs(this.x - px) <= 1 && Math.abs(this.y - py) <= 1;
    }
}
