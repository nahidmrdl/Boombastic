package gui;

import entity.player.Player;
import gameengine.GameEngine;
import gui.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GameTopPanelGUI {
    private GameEngine model;
    private JPanel topPanel;
    private JFrame frame;

    public GameTopPanelGUI(JFrame frame, GameEngine gameEngine) {
        this.frame = frame;
        this.topPanel = new JPanel();
        this.model = gameEngine;

        this.topPanel.setPreferredSize(new Dimension(990, 130));
        this.topPanel.setMaximumSize(new Dimension(990, 130));
        this.topPanel.setMinimumSize(new Dimension(990, 130));
        this.topPanel.setBounds(0, 0, 990, 130);

        addContent();
    }

    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public void setColor(Color color) {
        this.topPanel.setBackground(color);
    }

    private void addContent() {
        this.topPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.25;

        //Player 1
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.topPanel.add(createPlayerPanel(0), constraints);

        //Player 2
        constraints.gridx = 1;
        this.topPanel.add(createPlayerPanel(1), constraints);

        //Player 3
        if(model.getPlayerCount() == 3) {
            constraints.gridx = 2;
            this.topPanel.add(createPlayerPanel(3), constraints);
        }

        //Timer
        constraints.gridx = 3;
        this.topPanel.add(addTimerAndPause(), constraints);

    }

    private JPanel createPlayerPanel(int playerIndex){
        List<Player> players = model.getPlayers();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.X_AXIS));

        ImagePanel imagePanel = new ImagePanel(playerIndex);
        imagePanel.setPreferredSize(new Dimension(100, 100));

        JPanel pwUpsCurses = new JPanel();
        pwUpsCurses.setLayout(new BoxLayout(pwUpsCurses, BoxLayout.X_AXIS));

        JPanel bombsAndVictories = new JPanel();
        bombsAndVictories.setLayout(new BoxLayout(bombsAndVictories, BoxLayout.Y_AXIS));

        JPanel bombs = new JPanel();
        bombs.setLayout(new BoxLayout(bombs, BoxLayout.X_AXIS));

        JPanel victories = new JPanel();
        victories.setLayout(new BoxLayout(victories, BoxLayout.X_AXIS));

        bombsAndVictories.add(bombs);
        bombsAndVictories.add(victories);

        playerInfo.add(imagePanel);
        playerInfo.add(bombsAndVictories);

        playerPanel.add(playerInfo);
        playerPanel.add(pwUpsCurses);
        return playerPanel;
    }

    private JPanel addTimerAndPause(){
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));


        return timerPanel;
    }


}
