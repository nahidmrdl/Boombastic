package gui;

import gameengine.GameEngine;
import levels.LevelReader;

import javax.swing.*;

import static java.lang.StringTemplate.STR;

public class GameGUI {
    public JFrame frame;
    private GameEngine model;



    public GameGUI() {

            this.frame = new JFrame("Bombastic initial screen");
            this.frame.setSize(1007, 582);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame);
            this.model = new GameEngine(initialScreen, frame);

            this.frame.add(initialScreen);

            this.frame.setLocationRelativeTo(null);

            this.frame.setVisible(true);


    }

    public JFrame getMainFrame() {
        return this.frame;
    }
}
