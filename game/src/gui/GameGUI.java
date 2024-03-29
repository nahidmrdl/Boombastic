package gui;

import gameengine.GameEngine;

import javax.swing.*;

public class GameGUI {
    public JFrame frame;
    private GameEngine model;

    public GameGUI() {

        this.frame = new JFrame("Bombastic initial screen");
        this.frame.setSize(1000, 600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = new GameEngine();

        GameInitialScreenGUI initialScreen = new GameInitialScreenGUI();
        this.frame.add(initialScreen);

        this.frame.setLocationRelativeTo(null);

        this.frame.setVisible(true);
    }
}

