package gameengine;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.monster.Monster;
import entity.monster.monstertypes.GhostlyMonster;
import entity.monster.monstertypes.SimpleMonster;
import entity.monster.monstertypes.SpeedyMonster;
import entity.player.Player;
import item.GameItem;
import item.bomb.Bomb;
import item.powerup.PowerUp;
import gui.GameTopPanelGUI;

import levels.LevelReader;
import map.GameMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    private List<Monster> monsters;
    public GameEngine(List<Player> players, int roundCount, int mapIndex) {
        this.roundCount = roundCount;
        this.mapIndex = mapIndex;
        this.playerCount = players.size();
        this.players = players;
        this.monsters = new ArrayList<Monster>();
        this.defineMap();
        this.positionPlayersOnStartingPoint();

        for (Player player : players) {
            player.setGameMap(this.gameMap);
        }

        SimpleMonster m1 = new SimpleMonster(0, 0, this.gameMap, this.players);
        GhostlyMonster m2 = new GhostlyMonster(0, 0, this.gameMap, this.players);
        SpeedyMonster m3 = new SpeedyMonster(0, 0, this.gameMap, this.players);
        monsters.add(m1);
        monsters.add(m2);
        monsters.add(m3);

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
                            cell.addVisitor(players.get(playerCount));
                            playerCount++;
                        }
                    }
                }
            }
        }
    }

    public List<Monster> getMonsters(){
        return this.monsters;
    }


    /**
     * Run calculations for the game to get new state
     */

    public void runGameUnit() throws IOException {
        for (Cell[] row : this.gameMap.getMap()) {
            // remove finished items
            for (Cell cell : row) {
                if (cell instanceof NormalCell) {
                    ((NormalCell) cell).collectItems();
                    ((NormalCell) cell).removeFinishedItems();


                }
            }
        }

        // remove finished power-ups form players
        for (Player player : this.players) {
            player.removeFinishedPowerUps();
            player.removeFinishedCurses();
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
