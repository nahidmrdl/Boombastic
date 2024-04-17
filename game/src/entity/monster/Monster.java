package entity.monster;

import java.awt.*;
import java.util.Random;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import map.GameMap;
import entity.player.Player;
import java.util.List;

    public class Monster extends Entity {
    private Image baseImage;
    private int direction;
    private int speed;

    protected List<Player> players;

    // Constructor
    public Monster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap);
        this.players = players;

    }



       private void findValidStartingPosition() {

    }

    public void moveRandomly() {

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
