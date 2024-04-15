package entity.monster;

import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import map.GameMap;

import java.awt.*;
import java.util.Random;

public class Monster extends Entity {

    private Image baseImage;
    private int direction;
    private int speed;

    private int row;
    private int col;

// x is column, y is row...
    public Monster(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);

        int maxX = this.gameMap.getMap().length;
        System.out.println(maxX + "row length");
        int maxY = this.gameMap.getMap()[0].length;
        System.out.println(maxY + "col length");

        Random rand = new Random();
        do{
            this.x = rand.nextInt(maxY);
            this.y = rand.nextInt(maxX);

        }while((this.gameMap.getMap()[y][x] instanceof WallCell));
        System.out.println(this.y + " row");
        System.out.println(this.x + " col");

        direction = rand.nextInt(4);
    }

    public void moveRandomly() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int newX = this.x + dx[direction];
        int newY = this.y + dy[direction];
        Random rand = new Random();

        if (newX < 0 || newX >= this.gameMap.getMap()[0].length || newY < 0 || newY >= this.gameMap.getMap().length || this.gameMap.getMap()[newY][newX] instanceof NormalCell) {
            direction = rand.nextInt(4);
        } else {
            this.x = newX;
            this.y = newY;
        }

    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    /**
     * this method checks if the dragon is next to player, it takes into account the player's position
     * @param px, py
     */
    public boolean isNextToPlayer(int px, int py) {
        int distX = Math.abs(this.x - px);
        int distY = Math.abs(this.y - py);
        if (distX <= 1 && distY == 0 || (distX == 0 && distY <= 1)) {
            return true;
        }
        return false;
    }


}
