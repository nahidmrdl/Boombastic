package gui;

import javax.swing.*;
import java.awt.*;

// Simple example to demonstrate the GameMapGUI structure.
public class GameMapGUI extends JPanel {
    private int mapIndex;

    public GameMapGUI(int mapIndex) {
        this.mapIndex = mapIndex;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        JLabel mapLabel = new JLabel("Map " + mapIndex + " Displayed Here", SwingConstants.CENTER);
        this.add(mapLabel, BorderLayout.CENTER);

        GameTopPanelGUI topPanel = new GameTopPanelGUI();
        this.add(topPanel, BorderLayout.NORTH); // Correctly adding the panel
    }
}

