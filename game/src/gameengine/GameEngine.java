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
        this.playerCount = players.size(); // Assuming you set this up based on the selected players
        this.players = players;
        //this.frame = frame; // Store the frame for later

        try {
            Cell[][] mapCell = LevelReader.readLevelFromFile("src/levels/" + this.mapIndex + ".txt");
            this.gameMap = new GameMap(mapCell, null, String.valueOf(this.mapIndex));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int playerCount = 0;

        System.out.println(players.size());

        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if(playerCount < players.size()) {
                    System.out.println(cell.getVisitors());
                    if(cell instanceof NormalCell) {
                        if(((NormalCell) cell).isStartingPoint()) {
                            System.out.println("Player " + playerCount + " is at starting point");
                            // Inverse for some reason
                            players.get(playerCount).setX(cell.getY());
                            players.get(playerCount).setY(cell.getX());
                            playerCount++;
                        }
                    }
                }

            }
            System.out.println();
        }

        for (Player player : players) {
            player.setGameMap(this.gameMap);
        }
    }

    public void runGameUnit() throws IOException {
        for (Cell[] row : this.gameMap.getMap()) {
            for (Cell cell : row) {
                if (cell instanceof NormalCell) {
                    // Temporary list to hold items that need to be removed
                    List<GameItem> itemsToRemove = new ArrayList<>();

                    // First, determine which items need to be removed
                    for (GameItem item : cell.getItems()) {
                        System.out.println("Item finish time: " + item.getFinishTime());
                        System.out.println("Current time: " + System.currentTimeMillis());
                        if (item.getFinishTime() < System.currentTimeMillis()) {
                            itemsToRemove.add(item);
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

    private void setPlayersPositions() {

    }

    public int getRoundCount() {
        return this.roundCount;
    }

    public int getMapIndex() {
        return this.mapIndex;
    }

    public GameMap getMap() {
        return this.gameMap;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    public List<Player> getPlayers() {
        return this.players;
    }


    public void startGame() throws IOException {
        System.out.println("Game started");
        System.out.println("Round Count: " + this.roundCount);
        System.out.println("Map index: " + this.mapIndex);

        // Remove the initial GUI
        this.frame.getContentPane().removeAll();

        // Create and add the game map GUI to the frame
        GameMapGUI gameMapGUI = new GameMapGUI(this, frame);
        this.frame.add(gameMapGUI);
        this.frame.validate();
        this.frame.repaint();
    }
}
