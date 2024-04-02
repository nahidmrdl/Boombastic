package gameengine;

import cell.Cell;
import entity.player.Player;
import gui.GameMapGUI;
import levels.LevelReader;
import map.GameMap;

import javax.swing.JFrame;
import java.io.IOException;
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
            Cell mapCell[][] = LevelReader.readLevelFromFile("src/levels/" + this.mapIndex + ".txt");
            this.gameMap = new GameMap(mapCell, null, String.valueOf(this.mapIndex));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
