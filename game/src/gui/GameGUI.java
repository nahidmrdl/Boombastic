package gui;

import entity.player.Player;
import gameengine.GameEngine;
import levels.LevelReader;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class GameGUI {
    public JFrame frame;

    private JPanel mapPanel;
    private GameEngine model;



    public GameGUI() {

            this.frame = new JFrame("Bombastic initial screen");
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
        this.mapPanel = new GameMapGUI(this.model, this.frame);
        this.frame.add(this.mapPanel);
        this.frame.revalidate();
        this.frame.repaint();


    }

    public void setGameEngine(GameEngine model) {
        this.model = model;
    }

    public JFrame getMainFrame() {
        return this.frame;
    }
}
