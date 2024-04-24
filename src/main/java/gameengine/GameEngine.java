package gameengine;

import cell.Cell;
import cell.normalCell.NormalCell;
import entity.monster.Monster;
import entity.monster.monstertypes.ConfusedMonster;
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
import java.util.Iterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents the game engine that runs the game.
 */
public class GameEngine {
    private int roundCount;
    private int mapIndex;
    private int playerCount;
    private List<Player> players;
    private LevelReader lr = new LevelReader();
    private GameMap gameMap;
    public List<Monster> monsters;

    /**
     * Constructor for GameEngine
     * @param players list of players in the game
     * @param roundCount number of rounds in the game
     * @param mapIndex index of the map
     */
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
        ConfusedMonster m4 = new ConfusedMonster(0, 0, this.gameMap, this.players);

        monsters.add(m1);
        monsters.add(m2);
        monsters.add(m3);
        monsters.add(m4);

    }

    /**
     * Remove dead monsters from the list of monsters
     * @param monsters list of monsters in the game
     */
    public void removeDeadMonsters(List<Monster> monsters) {
        synchronized(monsters) {
            Iterator<Monster> iterator = monsters.iterator();
            while (iterator.hasNext()) {
                Monster monster = iterator.next();
                if (monster.isDead()) {
                    iterator.remove(); // Safely remove the current monster from the list
                }
            }
        }
    }


        // TODO: FIX THIS
//    public void removeDeadPlayers(List<Player> players) {
//        synchronized(players) {
//            Iterator<Player> iterator = players.iterator();
//            while (iterator.hasNext()) {
//                Player player = iterator.next();
//                if (player.isDead()) {
//                    iterator.remove(); // Safely remove the current player from the list
//                }
//            }
//        }
//    }



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

    /**
     * Get the list of monsters in the game
     * @return list of monsters in the game
     */
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

    /**
     * Get the list of players in the game
     * @return list of players in the game
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get the game map
     * @return game map
     */
    public GameMap getMap() {
        return this.gameMap;
    }

    /**
     * Get the round count
     * @return round count
     */
    public int getRoundCount() {
        return this.roundCount;
    }

    /**
     * Get the map index
     * @return map index
     */
    public int getMapIndex() {
        return this.mapIndex;
    }

    /**
     * Get the player count
     * @return player count
     */
    public int getPlayerCount() {
        return this.playerCount;
    }
}
