package gui;

import cell.Cell;
import levels.LevelReader;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Simple example to demonstrate the GameMapGUI structure.
public class GameMapGUI extends JPanel {

    private Map map;
    private int playerCount;
    private int roundCount;

    private LevelReader lr = new LevelReader();
    public GameMapGUI(int roundCount, Map map, int playerCount) {
        this.map = map;
        this.roundCount = roundCount;
        this.playerCount = playerCount;
        updateGUI();


    }

    private void updateGUI() {
        this.setLayout(new BorderLayout());
        JLabel mapLabel = new JLabel("Map " + map.getName() + " Displayed Here", SwingConstants.CENTER);
        this.add(mapLabel, BorderLayout.CENTER);


//        GameTopPanelGUI topPanel = new GameTopPanelGUI();
//        this.add(topPanel, BorderLayout.NORTH); // Correctly adding the panel
    }
}

