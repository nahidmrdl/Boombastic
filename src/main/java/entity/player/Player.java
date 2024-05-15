package entity.player;

import cell.Cell;
import entity.Entity;
import item.bomb.Bomb;
import item.curse.Curse;
import item.powerup.PowerUp;
import map.GameMap;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import cell.box.BoxCell;
import cell.wall.WallCell;

/**
 * Represents a player in the game.
 */
public class Player extends Entity {
    private final String name;
    private final int imageIndex;
    private GameMap gameMap;
    private Image Image;

    private Date lastMoveTime = new Date();

    private int bombBlastRange = 1;

    private boolean isDetonator = false;

    private boolean isGhost = false;

    private boolean isInvincible = false;

    private int placeObsticleCount = 0;

    private int speed = 75;
    private HashMap<String, String> Controls;

    public List<PowerUp> powerUpsItems = new ArrayList<>();
    public List<Curse> cursesItems = new ArrayList<>();

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public int bombCount = 1;

    private boolean hasStepFromBomb = false;
    private boolean canPlaceBomb = true;

    private boolean isDead = false;
    public int victoryCount = 0;

    /**
     * Constructor for Player
     * @param x x-coordinate of the player
     * @param y y-coordinate of the player
     * @param gameMap map that the player is in
     * @param name name of the player
     * @param imageIndex index of the image of the player
     * @param Controls controls of the player
     * @param image image of the player
     */
    public Player(int x, int y, GameMap gameMap, String name, int imageIndex, HashMap<String, String> Controls, Image image) {
        super(x, y, gameMap);
        this.name = name;
        this.Image = image;
        this.Controls = Controls;
        this.imageIndex = imageIndex;
    }

    /**
     * Gets the image index of the player
     * @return
     */
    public int getImageIndex() {return imageIndex;}


    /**
     * Sets the number of obstacles that the player can place
     * @param placeObsticle number of obstacles that the player can place
     */
    public void setPlaceObsticleCount(int placeObsticle) {
        placeObsticleCount = placeObsticle;
    }

    /**
     * Gets the number of obstacles that the player can place
     * @return number of obstacles that the player can place
     */
    public int getPlaceObsticleCount() {
        return placeObsticleCount;
    }

    /**
     * Sets the step from bomb status of the player
     * @param hasStepFromBomb
     */
    public void setHasStepFromBomb(boolean hasStepFromBomb) {
        this.hasStepFromBomb = hasStepFromBomb;
    }

    /**
     * Gets the step from bomb status of the player
     * @return step from bomb status of the player
     */
    public boolean getHasStepFromBomb() {
        return hasStepFromBomb;
    }

    /**
     * Sets the detonator status of the player
     * @param detonator detonator status of the player
     */
    public void setDetonator(boolean detonator) {
        isDetonator = detonator;
    }

    /**
     * Sets the invincible status of the player
     * @param invincible
     */
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    /**
     * Gets the invincible status of the player
     * @return invincible status of the player
     */
    public boolean isInvincible() {
        return isInvincible;
    }

    /**
     * Gets the dead status of the player
     * @return
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets the dead status of the player
     * @param dead dead status of the player
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Sets the ghost status of the player
     * @param ghost
     */
    public void setGhost(boolean ghost) {
        isGhost = ghost;
    }


    /**
     * Gets the ghost status of the player
     * @return ghost status of the player
     */
    public boolean isGhost() {
        return isGhost;
    }

    /**
     * Gets the name of the player
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Resets the speed of the player
     * @return
     */
    public void resetDefaultSpeed() {
        this.speed = 75;
    }

    /**
     * Sets the image of the player
     * @param image
     */
    public void setImage(Image image) {
        Image = image;
    }

    /**
     * Gets the image of the player
     * @return
     */
    public Image getImage() {
        return Image;
    }

    /**
     * Sets the game map of the player
     * @param gameMap
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Sets the x-coordinate of the player
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the player
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public void cleanItems(){
        for (PowerUp powerUp : powerUpsItems) {
            powerUp.reset(this);
        }
        for (Curse curse : cursesItems) {
            curse.reset(this);
        }

        powerUpsItems.clear();
        cursesItems.clear();
    }


    /**
     * Gets the x-coordinate of the player
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the bomb count of the player
     * @return
     */
    public int getBombCount() {
        return this.bombCount;
    }

    /**
     * Sets the speed of the player
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets the player's bomb placement status
     * @param canPlaceBomb
     */
    public void setCanPlaceBomb(boolean canPlaceBomb) {
        this.canPlaceBomb = canPlaceBomb;
    }

    /**
     * Gets the player's bomb placement status
     * @return
     */
    public boolean getCanPlaceBomb() {
        return this.canPlaceBomb;
    }

    /**
     * Adds a power-up to the player
     * @param powerUp
     */
    public void addPowerUp(PowerUp powerUp) {
        this.powerUpsItems.add(powerUp);
    }

    /**
     * Adds a curse to the player
     * @param curse
     */
    public void addCurse(Curse curse) {
        this.cursesItems.add(curse);
    }


    /**
     * Gets the y-coordinate of the player
     * @return
     */
    public int getY(){
        return this.y;
    }

    /**
     * Gets the controls of the player
     * @return
     */
    public HashMap<String, String> getControls() {
        return Controls;
    }

    /**
     * Handles the action of the player
     * @param keyCode key code of the action
     * @param level level that the player is in
     * @throws IOException
     */
    public void HandleAction(String keyCode, Cell[][] level ) throws IOException {

        if (this.isDead) {
            return;
        }
        int newX = this.x;
        int newY = this.y;

        HashMap<String, String> playerControls = this.getControls();
        String action = getKeyActionFromKeyCode(keyCode, playerControls);

        if (action != null) {
            switch (action) {
                case "UP": newY = this.y - 1; break;
                case "DOWN": newY = this.y + 1; break;
                case "LEFT": newX = this.x - 1; break;
                case "RIGHT": newX = this.x + 1; break;
                case "BOX" : placeBox(); break;
                case "BOMB": placeBomb(); break;
            }

            if(lastMoveTime.getTime() + speed < new Date().getTime()){
                this.move(newX, newY);
                lastMoveTime = new Date();
            }

        }
    }

    /**
     * Places a box in the game map
     */
    private void placeBox() {
        if(placeObsticleCount > 0) {
            BoxCell box = new BoxCell(this.x, this.y, this.gameMap);
            box.setOwner(this);
            this.gameMap.getMap()[this.y][this.x] = box;
            placeObsticleCount--;
        }

    }

    /**
     * Moves the player to a new position
     * @param newX new x-coordinate of the player
     * @param newY new y-coordinate of the player
     */
    protected void move(int newX, int newY){
        Cell[][] level = this.gameMap.getMap();
        if (
                        newX >= 0
                        && newY >= 0
                        && newX < level[0].length
                        && newY < level.length
        ) {
            if( (level[newY][newX] instanceof WallCell) || (level[newY][newX] instanceof BoxCell)) {
                // If the player is a ghost, they can move through walls and boxes
                if(isGhost) {
                    moveTo(newX, newY);
                }
            } else {
                moveTo(newX, newY);
            }
            setHasStepFromBomb(false);
        }
    }

    /**
     * Moves the player to a new position
     * @param newX new x-coordinate of the player
     * @param newY new y-coordinate of the player
     */
    private void moveTo(int newX, int newY) {
        this.gameMap.getMap()[this.y][this.x].getVisitors().remove(this);
        this.x = newX;
        this.y = newY;
        this.gameMap.getMap()[this.y][this.x].addVisitor(this);

        // Invoke the detonate animation if the player is on a bomb with a detonator
        this.gameMap.getMap()[this.y][this.x].getItems().forEach(item -> {
            if (item instanceof Bomb && ((Bomb) item).isDetonator() && !getHasStepFromBomb() ) {
                ((Bomb) item).setState(2);
                ((Bomb) item).invokeDetonateAnimation();
            }
        });
    }

    /**
     * Gets the bomb blast range of the player
     * @return
     */
   public int getBombBlastRange() {
        return bombBlastRange;
    }

    /**
     * Sets the bomb blast range of the player
     * @param bombBlastRange
     */
    public void setBombBlastRange(int bombBlastRange) {
        this.bombBlastRange = bombBlastRange;
    }

    /**
     * Places a bomb in the game map
     */
    public void placeBomb() {
        if (!canPlaceBomb) {
            return; // Do not proceed if bomb placement is prohibited
        }
        if (canPlaceBomb) {
            if (this.bombCount == 0) {
                if (this.isDetonator) {
                    this.gameMap.DetonatePlayerBombs(this);
                    this.setDetonator(false); // Detonator is a one-time use item
                }
                return;
            }

            setHasStepFromBomb(true);
            Bomb bomb = new Bomb(isDetonator);
            bomb.setBlastRadius(this.bombBlastRange);
            bomb.setCell(this.gameMap.getMap()[this.y][this.x]);
            bomb.setOwner(this);
            this.gameMap.getMap()[this.y][this.x].addItem(bomb);
            this.bombCount--;
        }
    }
    /**
     * Remove finished power-ups (check the finish time) and if the power-up is finished, remove it from the player and apply the reset method
     */
    public void removeFinishedPowerUps() {
        Iterator<PowerUp> iterator = this.powerUpsItems.iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();
            if(powerUp.getFinishTime() != 0 && powerUp.getFinishTime() < System.currentTimeMillis()) {
                iterator.remove();
                powerUp.reset(this);
            }
        }
    }

    /**
     * Remove finished curses (check the finish time) and if the curse is finished, remove it from the player and apply the reset method
     */
    public void removeFinishedCurses(){
        Iterator<Curse> iterator = this.cursesItems.iterator();
        while (iterator.hasNext()) {
            Curse curse = iterator.next();
            if(curse.getFinishTime() != 0 && curse.getFinishTime() < System.currentTimeMillis()) {
                iterator.remove();
                curse.reset(this);
            }
        }
    }

    /**
     * Gets the key action from the key code
     * @param keyCode key code of the action
     * @param playerControls controls of the player
     * @return
     */
    private String getKeyActionFromKeyCode(String keyCode, HashMap<String, String> playerControls) {
        for (Map.Entry<String, String> entry : playerControls.entrySet()) {
            if (String.valueOf(keyCode).equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * toString method for Player
     * @return
     */
    @Override
    public String toString() {
       // STR."Player name: \{name}, Image index: \{imageIndex}, Controls: \{Controls}, X: \{x}, Y: \{y}, \{hasStepFromBomb}";

        return "Player name: " + name + ", Image index: " + imageIndex + ", Controls: " + Controls + ", X: " + x + ", Y: " + y + ", " + hasStepFromBomb;
    }
}
