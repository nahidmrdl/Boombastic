package gameengine;

import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import gui.GameMapGUI;
import item.GameItem;
import levels.LevelReader;
import map.GameMap;

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
    private JFrame frame; // Store the frame
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

    public void runGameUnit() throws IOException {
        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if(playerCount < players.size()) {
                    if(cell instanceof NormalCell) {
                        if(((NormalCell) cell).isStartingPoint()) {
                            // inverse x and y because of the way the map is read
                            players.get(playerCount).setX(cell.getY());
                            players.get(playerCount).setY(cell.getX());
                            playerCount++;
                        }
                    }



                    // Now, remove the items outside of the original loop to avoid ConcurrentModificationException
                    for (GameItem item : itemsToRemove) {
                        cell.getItems().remove(item);
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (i == 2 && j == 2) {
                                    continue;
                                }
                                if (i == 2 || j == 2) {
                                    if (this.gameMap.getMap()[cell.getX() + i - 2][cell.getY() + j - 2] != null) {
                                        this.gameMap.getMap()[cell.getX() + i - 2][cell.getY() + j - 2].setForegroundImage(null);
                                    }
                                }
                            }
                        }
                        System.out.println("Item removed");
                    }
                }
            }
        }
    }

    /**
     * Run calculations for the game to get new state
     */
    public void runGameUnit() {
        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if (cell instanceof NormalCell) {
                    ((NormalCell) cell).removeFinishedItems();
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


    // Functions needed for Top Bar
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
