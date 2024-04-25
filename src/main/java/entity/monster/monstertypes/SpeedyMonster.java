package entity.monster.monstertypes;

import java.awt.*;
import java.util.*;

import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import entity.monster.Monster;
import entity.player.Player;
import map.GameMap;
import util.Edge;
import util.Node;

import javax.swing.*;
import java.util.List;

//TODO NOT READY!! SHORTEST PATH IMPLEMENTATION.

/**
 * Represents a speedy monster in the game map.
 */
public class SpeedyMonster extends Monster {
    private Image baseImage;
    private int direction;
    private Date lastMoveTime = new Date();

    private Node[][] nodes;
    private int speed;

    /**
     * Constructor for SpeedyMonster
     * @param x x-coordinate of the monster
     * @param y y-coordinate of the monster
     * @param gameMap map that the monster is in
     * @param players list of players in the game
     */
    public SpeedyMonster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap, players);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
        this.speed = 300;
    }

    /**
     * Determines the direction of the monster based on the nearest player
     * @return the direction of the monster
     */
    public String determineMonsterDirection() {
        Player p = findNearestPlayer();
        int upDistance = this.getY() > p.getY() ? this.getY() - p.getY() : Integer.MAX_VALUE;
        int downDistance = this.getY() < p.getY() ? p.getY() - this.getY() : Integer.MAX_VALUE;
        int leftDistance = this.getX() > p.getX() ? this.getX() - p.getX() : Integer.MAX_VALUE;
        int rightDistance = this.getX() < p.getX() ? p.getX() - this.getX() : Integer.MAX_VALUE;

        int minDistance = Math.min(Math.min(upDistance, downDistance), Math.min(leftDistance, rightDistance));

        if (minDistance == upDistance && upDistance != 0) {
            return "UP";
        } else if (minDistance == downDistance && downDistance != 0) {
            return "DOWN";
        } else if (minDistance == leftDistance && leftDistance != 0) {
            return "LEFT";
        } else if (minDistance == rightDistance && rightDistance != 0) {
            return "RIGHT";
        } else {
            return "sup";
        }
    }


    /**
     * Finds valid starting position for the monster
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
     * Checks if the monster is far from the players
     * @param distance the distance to check
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
            int directionNew = -1;
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

            }
            else {
                switch (determineMonsterDirection()){
                    case "UP":
                        directionNew = 3;
                        break;
                    case "DOWN":
                        directionNew = 2;
                        break;
                    case "LEFT":
                        directionNew = 1;
                        break;
                    case "RIGHT":
                        directionNew = 0;
                        break;
                }

                if(directionNew != direction){
                    direction = directionNew;
                }
                else{
                    direction = rand.nextInt(4);
                }
            }
        }
    }

    /**
     * Sets the x-coordinate of the monster
     * @param x what to set the x-coordinate to
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the monster
     * @param y what to set the y-coordinate to
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the monster
     * @return the x-coordinate of the monster
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the y-coordinate of the monster
     * @return the y-coordinate of the monster
     */
    public int getY(){
        return this.y;
    }

    /**
     * Checks if the monster is next to the player
     * @param px
     * @param py
     * @return true if the monster is next to the player, false otherwise
     */
    public boolean isNextToPlayer(int px, int py) {
        return this.x == px && this.y == py;
    }

    /**
     * Finds the nearest player to the monster
     * @return the nearest player to the monster
     */
    private Player findNearestPlayer() {
        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;

        for (Player player : this.players) {
            double distance = Math.sqrt(Math.pow(this.x - player.getX(), 2) + Math.pow(this.y - player.getY(), 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayer = player;
            }
        }

        return nearestPlayer;
    }
}

