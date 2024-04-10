package gui;

import gameengine.GameEngine;

import javax.swing.*;
import java.awt.*;


public class GameTopPanelGUI {
    //private GameEngine gameEngine = new GameEngine();
    private JPanel topPanel;
    private JFrame frame;

    public GameTopPanelGUI(JFrame frame) {
        this.frame = frame;
        this.topPanel = new JPanel();

        this.topPanel.setPreferredSize(new Dimension(990, 130));
        this.topPanel.setMaximumSize(new Dimension(990, 130));
        this.topPanel.setMinimumSize(new Dimension(990, 130));
        this.topPanel.setBounds(0, 0, 990, 130);

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
    }

    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public void setColor(Color color) {
        this.topPanel.setBackground(color);
    }

}
