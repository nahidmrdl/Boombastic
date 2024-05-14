package gui;

import gameengine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.Color;

public class GameGUI {
    public JFrame frame;

    private JPanel mapPanel;
    private GameEngine model;

    public static final Color darkGreen = new Color(0,102,0);
    public static final Color customOrange = new Color(255,153,0);

    public GameGUI() {

        this.frame = new JFrame("Boomberman");
        this.frame.setSize(1007, 582);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, this);
        this.model = null;

        this.frame.add(initialScreen);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void startGame() throws IOException {
        this.frame.getContentPane().removeAll();
        this.frame.setSize(990, 700);
        this.frame.setLocationRelativeTo(null);

        final GameTopPanelGUI gameTopPanelGUI = new GameTopPanelGUI(frame, model, model.getMapIndex(), model.getRoundCount());

        if(model.getMapIndex() == 10 || model.getMapIndex() == 11) {
            gameTopPanelGUI.setColor(darkGreen);
        }
        else {
            gameTopPanelGUI.setColor(customOrange);
        }

        this.frame.add(gameTopPanelGUI.getTopPanel(), BorderLayout.NORTH);

        this.mapPanel = new GameMapGUI(this.model, this.frame, gameTopPanelGUI);
        this.frame.add(this.mapPanel, BorderLayout.CENTER);

        this.frame.revalidate();
        this.frame.repaint();
    }

    public void setGameEngine(GameEngine model) {
        this.model = model;
    }
}