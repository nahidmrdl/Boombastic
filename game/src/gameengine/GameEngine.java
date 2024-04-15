package gameengine;

import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import gui.GameGUI;
import gui.GameMapGUI;
import item.GameItem;
import levels.LevelReader;
import map.GameMap;
import item.bomb.Bomb;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private int roundCount;
    private int mapIndex;
    private int playerCount;
    private List<Player> players;
    private LevelReader lr = new LevelReader();
    private GameMap gameMap;

    public GameEngine(List<Player> players, int roundCount, int mapIndex) {
        this.roundCount = roundCount;
        this.mapIndex = mapIndex;
        this.playerCount = players.size();
        this.players = players;

        this.defineMap();
        this.positionPlayersOnStartingPoint();

        for (Player player : players) {
            player.setGameMap(this.gameMap);
        }
    }
    /**
     * Read the map from the file and create the game map
     */
    private void defineMap() {
        try {
            Cell[][] mapCell = LevelReader.readLevelFromFile("src/levels/" + this.mapIndex + ".txt");
            this.gameMap = new GameMap(mapCell, null, String.valueOf(this.mapIndex));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Position players on the starting point of the map
     */
    private void positionPlayersOnStartingPoint() {
    int playerCount = 0;
        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if(playerCount < players.size()) {
                       if(cell instanceof NormalCell) {
                        if(((NormalCell) cell).isStartingPoint()) {
                            // Inverse for some reason
                            players.get(playerCount).setX(cell.getY());
                            players.get(playerCount).setY(cell.getX());
                            playerCount++;
                        }
                    }
                }

            }

        }
    }

    /**
     * Run calculations for the game to get new state
     */

    // TODO, THIS IS RELATED TO THE BOMB BLAST CAUSING CONFLICTS
   //TODO
    public void runGameUnit() throws IOException {
        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if (cell instanceof NormalCell) {
                    // Temporary list to hold items that need to be removed
                    List<GameItem> itemsToRemove = new ArrayList<>();
                    // First, determine which items need to be removed
                    for (GameItem item : cell.getItems()) {
//                        System.out.println("Item finish time: " + item.getFinishTime());
//                        System.out.println("Current time: " + System.currentTimeMillis());
                        if (item.getFinishTime() < System.currentTimeMillis()) {
                            itemsToRemove.add(item);
                        }
                    }
                    // Loop over items to remove
                    for (GameItem item : itemsToRemove) {
                        cell = item.getCell();
                        this.gameMap.getMap()[cell.getX()][cell.getY()].getItems().remove(item); // Remove the item from its current cell

                        // Assuming getBlastRadius() is a method that returns the blast radius
                        int blastRadius =  ((Bomb) item).getBlastRadius();

                        // Define directions for the blast pattern: up, down, left, right
                        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

                        // Loop through each direction to clear the foregroundImage
                        for (int[] direction : directions) {
                            for (int i = 1; i <= blastRadius; i++) {
                                int targetX = cell.getX() + i * direction[0];
                                int targetY = cell.getY() + i * direction[1];

                                // Ensure target coordinates are within map bounds
                                if (targetX >= 0 && targetX < this.gameMap.getMap().length && targetY >= 0 && targetY < this.gameMap.getMap()[0].length) {
                                    // Check if the cell is not null and then clear the foregroundImage
                                    if (this.gameMap.getMap()[targetX][targetY] != null) {
                                        this.gameMap.getMap()[targetX][targetY].setForegroundImage(null);
                                    }
                                } else {
                                    // If target is out of bounds, break out of the loop for the current direction
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public GameMap getMap() {
        return this.gameMap;
    }

    public int getRoundCount() {
        return this.roundCount;
    }

    public int getMapIndex() {
        return this.mapIndex;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }
}
