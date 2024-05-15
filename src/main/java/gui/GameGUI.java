package gui;

import gameengine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.Color;

/**
 * The GameGUI class represents the graphical user interface for the Boomberman game.
 */
public class GameGUI {
    /** The main JFrame for the game. */
    public JFrame frame;

    /** The JPanel for displaying the game map. */
    private JPanel mapPanel;

    /** The model containing game data and logic. */
    private GameEngine model;

    /** Constant for the dark green color used in certain UI elements. */
    public static final Color darkGreen = new Color(0,102,0);

    /** Constant for the custom orange color used in certain UI elements. */
    public static final Color customOrange = new Color(255,153,0);

    /**
     * Constructs a new GameGUI instance.
     * Initializes the main JFrame and displays the initial screen.
     */
    public GameGUI() {

        this.frame = new JFrame("Boomberman");
        this.frame.setSize(1007, 582);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the initial screen
        GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, this);
        this.model = null;

        this.frame.add(initialScreen);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /**
     * Starts the game by removing the initial screen and displaying the game interface.
     * @throws IOException if an IO error occurs
     */
    public void startGame() throws IOException {
        // Remove initial screen and set new frame size
        this.frame.getContentPane().removeAll();
        this.frame.setSize(990, 700);
        this.frame.setLocationRelativeTo(null);

        // Create and add the top panel with appropriate color based on map index
        final GameTopPanelGUI gameTopPanelGUI = new GameTopPanelGUI(frame, model, model.getMapIndex(), model.getRoundCount());
        if(model.getMapIndex() == 10 || model.getMapIndex() == 11) {
            gameTopPanelGUI.setColor(darkGreen);
        }
        else {
            gameTopPanelGUI.setColor(customOrange);
        }
        this.frame.add(gameTopPanelGUI.getTopPanel(), BorderLayout.NORTH);

        // Create and add the game map panel
        this.mapPanel = new GameMapGUI(this.model, this.frame, gameTopPanelGUI);
        this.frame.add(this.mapPanel, BorderLayout.CENTER);

        // Refresh the frame
        this.frame.revalidate();
        this.frame.repaint();
    }

    /**
     * Sets the game engine model.
     * @param model the game engine model
     */
    public void setGameEngine(GameEngine model) {
        this.model = model;
    }
}
