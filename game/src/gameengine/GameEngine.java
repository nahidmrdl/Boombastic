package gameengine;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import gui.GameMapGUI;
import item.GameItem;
import levels.LevelReader;
import map.GameMap;

import javax.swing.JFrame;
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
                            // inverse x and y because of the way the map is read
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
