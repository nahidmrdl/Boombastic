package gameengine;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.player.Player;
import item.GameItem;
import item.powerup.PowerUp;
import levels.LevelReader;
import map.GameMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        this.gameMap = new GameMap(null, String.valueOf(this.mapIndex), this.mapIndex);
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
            // remove finished items
            for (Cell cell : row) {
                if (cell instanceof NormalCell) {
                    ((NormalCell) cell).CollectPowerUp();
                    ((NormalCell) cell).removeFinishedItems();
                }
            }
        }

        // remove finished power-ups form players
        for (Player player : this.players) {
            player.removeFinishedPowerUps();
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
