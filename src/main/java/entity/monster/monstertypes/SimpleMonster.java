package entity.monster.monstertypes;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.monster.Monster;
import entity.player.Player;
import map.GameMap;

/**
 * Represents a simple monster in the game map.
 */
public class SimpleMonster extends Monster {
    private Image baseImage;
    private int direction;
    private Date lastMoveTime = new Date();

    private int speed;

    /**
     * Constructor for SimpleMonster
     * @param x x-coordinate of the monster
     * @param y y-coordinate of the monster
     * @param gameMap map that the monster is in
     * @param players list of players in the game
     */
    public SimpleMonster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap, players);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
        this.speed = 450;
    }



    /**
     * Finds a valid starting position for the monster
     */
    private void findValidStartingPosition() {
        int maxX = gameMap.getMap().length;
        int maxY = gameMap.getMap()[0].length;
        Random rand = new Random();
        do {
            this.x = rand.nextInt(maxY);
            this.y = rand.nextInt(maxX);
        } while (!(gameMap.getMap()[this.y][this.x] instanceof NormalCell) || !isFarFromPlayers(5));


    }

    /**
     * Determines if the monster is far from the players
     * @param distance
     * @return true if the monster is far from the players, false otherwise
     */
    private boolean isFarFromPlayers(int distance) {
        for (Player player : players) {
            int playerX = player.getX();
            int playerY = player.getY();
            double dist = Math.sqrt(Math.pow(playerX - this.x, 2) + Math.pow(playerY - this.y, 2));
            if (dist < distance) {
                return false;
            }
        }
        return true;
    }

    /**
     * Moves the monster randomly
     */
    public void moveRandomly() {
        if(lastMoveTime.getTime() + speed < new Date().getTime()) {
            lastMoveTime = new Date();
            Random rand = new Random();
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            int newX = this.x + dx[direction];
            int newY = this.y + dy[direction];

            if (newX >= 0 && newX < gameMap.getMap()[0].length && newY >= 0 && newY < gameMap.getMap().length && gameMap.getMap()[newY][newX] instanceof NormalCell) {
                this.gameMap.getMap()[this.y][this.x].getVisitors().remove(this);
                this.x = newX;
                this.y = newY;
                this.gameMap.getMap()[this.y][this.x].addVisitor(this);

                if (rand.nextInt(10) == 3){
                    direction = rand.nextInt(4);
                }

            } else {
                direction = rand.nextInt(4);
            }
        }
    }


    /**
     * Gets the x-coordinate of the monster
     * @return x-coordinate of the monster
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the y-coordinate of the monster
     * @return y-coordinate of the monster
     */
    public int getY(){
        return this.y;
    }


    /**
     * Method to check if the monster is next to the player
     * @param px x-coordinate of the player
     * @param py y-coordinate of the player
     * @return true if the monster is next to the player, false otherwise
     */
    public boolean isNextToPlayer(int px, int py) {
        return this.x == px && this.y == py;
    }



}

