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

        this.frame.revalidate();
        this.frame.repaint();
    }

    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public void setColor(Color color) {
        this.topPanel.setBackground(color);
    }

    private void addContent() {
        System.out.println("ADD CONTENT CALLED");

        this.topPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.25;

        //Player 1
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.topPanel.add(createPlayerPanel(0), constraints);
        System.out.println("player pannel 0");

        //Player 2
        constraints.gridx = 1;
        this.topPanel.add(createPlayerPanel(1), constraints);

        //Player 3
        if (model.getPlayerCount() == 3) {
            constraints.gridx = 2;
            this.topPanel.add(createPlayerPanel(2), constraints);
        }

        //Timer
        constraints.gridx = 3;
        this.topPanel.add(addTimerAndPause(), constraints);
    }

    private JPanel createPlayerPanel(int playerIndex) {
        List<Player> players = model.getPlayers();
        System.out.println(players.size());
        System.out.println("CREATE player panel called");

        // Setting up the playerPanel with a fixed width of 200 and height of 130 as per your request
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(Color.GREEN);
        playerPanel.setPreferredSize(new Dimension(200, 130)); // Set the preferred size as well
        playerPanel.setMaximumSize(new Dimension(200, 130));
        playerPanel.setMinimumSize(new Dimension(200, 130));

        // The playerInfo panel will take up the full width and approximately half the height of playerPanel
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.X_AXIS));
        playerInfo.setBackground(Color.RED);
        playerInfo.setMaximumSize(new Dimension(200, 100));
        playerInfo.setMinimumSize(new Dimension(200, 100));

        // The imagePanel within playerInfo will be a square as indicated in the schema
        ImagePanel imagePanel = new ImagePanel(playerIndex);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
        imagePanel.setBackground(Color.BLUE);
        imagePanel.setMaximumSize(new Dimension(100, 100));
        imagePanel.setMinimumSize(new Dimension(100, 100));

        // The bombsAndVictories panel will occupy the space next to imagePanel within playerInfo
        JPanel bombsAndVictories = new JPanel();
        bombsAndVictories.setLayout(new BoxLayout(bombsAndVictories, BoxLayout.Y_AXIS));
        bombsAndVictories.setBackground(Color.YELLOW);

        bombsAndVictories.setMaximumSize(new Dimension(100, 100)); // Assuming equal division of space
        bombsAndVictories.setMinimumSize(new Dimension(100, 100));

        // bombs and victories panels will be vertically stacked in bombsAndVictories
        JPanel bombs = new JPanel();
        bombs.setBackground(Color.ORANGE);
        bombs.setLayout(new BoxLayout(bombs, BoxLayout.X_AXIS));
        bombs.setMaximumSize(new Dimension(100, 50));
        bombs.setMinimumSize(new Dimension(100, 50));

        JPanel victories = new JPanel();
        victories.setBackground(Color.MAGENTA);
        victories.setLayout(new BoxLayout(victories, BoxLayout.X_AXIS));
        victories.setMaximumSize(new Dimension(100, 50));
        victories.setMinimumSize(new Dimension(100, 50));

        bombs.setPreferredSize(new Dimension(100, 50));
        victories.setPreferredSize(new Dimension(100, 50));
        // Add the bombs and victories to the bombsAndVictories panel
        bombsAndVictories.add(bombs);
        bombsAndVictories.add(victories);

        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        bombsAndVictories.setAlignmentY(Component.TOP_ALIGNMENT);

        // Add the imagePanel and bombsAndVictories to the playerInfo panel
        playerInfo.add(imagePanel);
        playerInfo.add(bombsAndVictories);

        // pwUpsCurses will take up the remaining space within playerPanel below playerInfo
        JPanel pwUpsCurses = new JPanel();
        pwUpsCurses.setLayout(new BoxLayout(pwUpsCurses, BoxLayout.X_AXIS));
        pwUpsCurses.setBackground(Color.CYAN);
        pwUpsCurses.setMaximumSize(new Dimension(200, 30));
        pwUpsCurses.setMinimumSize(new Dimension(200, 30));

        // Finally, add the playerInfo and pwUpsCurses to the playerPanel
        playerPanel.add(playerInfo);
        playerPanel.add(pwUpsCurses);

        // Ensure that the imagePanel is repainted and revalidated
        imagePanel.repaint();
        imagePanel.revalidate();

        // Return the complete player panel
        return playerPanel;
    }


    private JPanel addTimerAndPause() {
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));
        timerPanel.setBackground(Color.BLUE);
        timerPanel.setPreferredSize(new Dimension(200, 130));
        timerPanel.setMaximumSize(new Dimension(200, 130));
        timerPanel.setMinimumSize(new Dimension(200, 130));

        return timerPanel;
    }
}
