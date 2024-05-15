package entity.monster;

import java.awt.*;
import java.util.Random;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import map.GameMap;
import entity.player.Player;
import java.util.List;
// TODO: NOT READY!!! NEED SAFELY DELETE OF MONSTER

/**
 * Represents a monster in the game map.
 */
public class Monster extends Entity {
    private Image baseImage;
    private int direction;
    private int speed;

    protected boolean isDead;

    public void setDead(boolean state){
        this.isDead = state;
    }

    public boolean isDead(){
        return this.isDead;
    }
    protected List<Player> players;

    /**
     * Constructor for Monster
     * @param x x-coordinate of the monster
     * @param y y-coordinate of the monster
     * @param gameMap map that the monster is in
     * @param players list of players in the game
     */
    public Monster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap);
        this.players = players;
        this.isDead = false;

    }


    private void findValidStartingPosition() {

    }

    public void moveRandomly() {

    }

    /**
     * Gets the x-coordinate of the monster
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the y-coordinate of the monster
     * @return
     */
    public int getY(){
        return this.y;
    }

    /**
     * Method to check if the monster is next to the player
     * @param px
     * @param py
     * @return true if the monster is next to the player, false otherwise
     */
    public boolean isNextToPlayer(int px, int py) {
        return this.x == px && this.y == py;
    }
}
