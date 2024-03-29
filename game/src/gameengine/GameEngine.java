package gameengine;

import cell.Cell;
import gui.GameInitialScreenGUI;
import gui.GameMapGUI;
import levels.LevelReader;
import map.Map;

import javax.swing.JFrame;
import java.io.IOException;

public class GameEngine {
    private int roundCount;
    private int mapIndex;
    private int playerCount;
    private JFrame frame; // Store the frame

    private LevelReader lr = new LevelReader();
    private Map map;

    public GameEngine(GameInitialScreenGUI initGUI, JFrame frame) {
        this.roundCount = initGUI.getRoundCount();
        this.mapIndex = initGUI.getMapIndex();
        this.playerCount = 1; // Assuming you set this up based on the selected players
        this.frame = frame; // Store the frame for later

        try {
            Cell mapCell[][] = LevelReader.readLevelFromFile(STR."src/levels/\{this.mapIndex}.txt");
            this.map = new Map(mapCell, null, STR."\{this.mapIndex}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGame() {
        System.out.println("Game started");
        System.out.println("Round Count: " + this.roundCount);
        System.out.println("Map index: " + this.mapIndex);

        // Remove the initial GUI
        this.frame.getContentPane().removeAll();

        // Create and add the game map GUI to the frame
        GameMapGUI gameMapGUI = new GameMapGUI(this.roundCount, this.map, this.playerCount);
        this.frame.add(gameMapGUI);
        this.frame.validate();
        this.frame.repaint();
    }
}
