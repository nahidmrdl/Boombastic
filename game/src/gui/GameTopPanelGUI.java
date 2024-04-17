package gui;

import entity.player.Player;
import gameengine.GameEngine;
import util.ResourceCollection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTopPanelGUI {
    private final GameEngine model;
    private final JPanel topPanel;
    private final JFrame frame;
    private Color background;
    List<Player> players;
    private final int rounds;
    private final int map;

    public GameTopPanelGUI(JFrame frame, GameEngine gameEngine,  int map, int rounds) {
        this.frame = frame;
        this.topPanel = new JPanel();
        this.model = gameEngine;
        this.players = model.getPlayers();
        //System.out.println(players);
        this.rounds = rounds;
        this.map = map;

        this.topPanel.setPreferredSize(new Dimension(990, 130));
        this.topPanel.setMaximumSize(new Dimension(990, 130));
        this.topPanel.setMinimumSize(new Dimension(990, 130));
        this.topPanel.setBounds(0, 0, 990, 130);

        addContent();

        this.frame.revalidate();
        this.frame.repaint();
    }

    public JPanel getTopPanel() { return this.topPanel; }

    public void setColor(Color color) {
        background = color;
        this.topPanel.setOpaque(true);
        this.topPanel.setBackground(background);
    }

    private void addContent() {

        this.topPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.25;

        //Player 1
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.topPanel.add(createPlayerPanel(players.getFirst().getImageIndex()), constraints);

        //Player 2
        constraints.gridx = 1;
        this.topPanel.add(createPlayerPanel((players.get(1).getImageIndex())), constraints);

        //Player 3
        if (model.getPlayerCount() == 3) {
            constraints.gridx = 2;
            this.topPanel.add(createPlayerPanel((players.getLast().getImageIndex())), constraints);
        }

        //Timer
        constraints.gridx = 3;
        this.topPanel.add(addTimerAndPause(), constraints);
    }

    private JPanel createPlayerPanel(int playerIndex) {
        List<Player> players = model.getPlayers();
        System.out.println("pl index: " + playerIndex);
        JPanel playerPanel = new JPanel();

        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(background);

        playerPanel.setPreferredSize(new Dimension(200, 130));
        playerPanel.setMaximumSize(new Dimension(200, 130));
        playerPanel.setMinimumSize(new Dimension(200, 130));

        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.X_AXIS));

        playerInfo.setBackground(background);
        playerInfo.setMaximumSize(new Dimension(200, 100));
        playerInfo.setMinimumSize(new Dimension(200, 100));

        ImagePanel imagePanel = new ImagePanel(playerIndex);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
        imagePanel.setBackground(background);
        imagePanel.setMaximumSize(new Dimension(100, 100));
        imagePanel.setMinimumSize(new Dimension(100, 100));

        JPanel bombsAndVictories = new JPanel();
        bombsAndVictories.setLayout(new BoxLayout(bombsAndVictories, BoxLayout.Y_AXIS));
        bombsAndVictories.setBackground(background);

        bombsAndVictories.setMaximumSize(new Dimension(100, 100));
        bombsAndVictories.setMinimumSize(new Dimension(100, 100));

        JPanel bombs = new JPanel();
        bombs.setBackground(background);
        bombs.setLayout(new BoxLayout(bombs, BoxLayout.X_AXIS));
        bombs.setMaximumSize(new Dimension(100, 50));
        bombs.setMinimumSize(new Dimension(100, 50));

        Image bombImage = ResourceCollection.Images.POWER_BOMBSTAGE1.getImage();
        JLabel bombLabel = new JLabel(new ImageIcon(bombImage));

        JLabel gap = new JLabel("   ");
        JLabel gap2 = new JLabel("   ");

        JLabel bombCount = new JLabel("  " + players.get(playerIndex).bombCount);
        bombCount.setFont(new Font("Arial", Font.BOLD, 30));
        bombs.add(gap2);
        bombs.add(bombLabel);
        bombs.add(bombCount);

        JPanel victories = new JPanel();
        victories.setBackground(background);
        victories.setLayout(new BoxLayout(victories, BoxLayout.X_AXIS));
        victories.setMaximumSize(new Dimension(100, 50));
        victories.setMinimumSize(new Dimension(100, 50));

        Image victoryImage = ResourceCollection.Images.TROPHY.getImage();
        JLabel victoryLabel = new JLabel(new ImageIcon(victoryImage));

        JLabel victoryCount = new JLabel("  " + players.get(playerIndex).victoryCount);
        victoryCount.setFont(new Font("Arial", Font.BOLD, 30));
        victories.add(gap);
        victories.add(victoryLabel);
        victories.add(victoryCount);

        bombs.setPreferredSize(new Dimension(100, 50));
        victories.setPreferredSize(new Dimension(100, 50));

        bombsAndVictories.add(bombs);
        bombsAndVictories.add(victories);

        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        bombsAndVictories.setAlignmentY(Component.TOP_ALIGNMENT);

        playerInfo.add(imagePanel);
        playerInfo.add(bombsAndVictories);

        JPanel pwUpsCurses = new JPanel();
        pwUpsCurses.setLayout(new BoxLayout(pwUpsCurses, BoxLayout.X_AXIS));
        pwUpsCurses.setBackground(background);
        pwUpsCurses.setMaximumSize(new Dimension(200, 30));
        pwUpsCurses.setMinimumSize(new Dimension(200, 30));

        // Will be removed, added for testing purposes
        // Assume that player can have maximum 3 powerUps and 3 curses

        for(int i = 0; i < 3; i++){
            players.get(playerIndex).powerUps.add(ResourceCollection.Images.POWERUP_ICON.getImage());
            players.get(playerIndex).curses.add(ResourceCollection.Images.CURSE_ICON.getImage());
        }

        for (Image powerUp : players.get(playerIndex).powerUps) {
            JLabel powerUpLabel = new JLabel(new ImageIcon(powerUp));
            pwUpsCurses.add(powerUpLabel);
        }

        for (Image curse : players.get(playerIndex).curses) {
            JLabel curseLabel = new JLabel(new ImageIcon(curse));
            pwUpsCurses.add(curseLabel);
        }

        playerPanel.add(playerInfo);
        playerPanel.add(pwUpsCurses);

        playerPanel.repaint();
        playerPanel.revalidate();

        return playerPanel;
    }


    private JPanel addTimerAndPause() {
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
        timerPanel.setBackground(background);
        timerPanel.setPreferredSize(new Dimension(200, 130));
        timerPanel.setMaximumSize(new Dimension(200, 130));
        timerPanel.setMinimumSize(new Dimension(200, 130));

        JButton pauseButton = new JButton("Pause");
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.setPreferredSize(new Dimension(80, 40));
        pauseButton.setMaximumSize(new Dimension(80, 40));
        pauseButton.setMinimumSize(new Dimension(80, 40));

        JLabel timerLabel = new JLabel("0:00");
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timerLabel.setForeground(Color.BLACK);

        timerPanel.add(Box.createVerticalStrut(20));
        timerPanel.add(timerLabel);
        timerPanel.add(pauseButton);

        // Timer setup
        AtomicInteger elapsedSeconds = new AtomicInteger(0);
        Timer timer = new Timer(1000, e -> {
            int seconds = elapsedSeconds.incrementAndGet();
            int minutes = seconds / 60;
            seconds %= 60;
            timerLabel.setText(String.format("%1$d:%2$02d", minutes, seconds));
        });
        timer.start();

        pauseButton.addActionListener(event -> {
            if (timer.isRunning()) {
                timer.stop();
                JDialog dialog = new JDialog(frame, "Game Paused", true);
                dialog.setLayout(new GridLayout(3, 1));
                dialog.setSize(200, 300);
                dialog.setLocationRelativeTo(frame);

                JButton resumeButton = new JButton("Resume");
                resumeButton.addActionListener(e -> {
                    dialog.dispose();
                    timer.start();
                });

                JButton restartButton = new JButton("Restart");
                restartButton.addActionListener(e -> {
                    dialog.dispose();
                    frame.dispose();
                    GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, new GameGUI());
                    try {
                        for (Player player : model.getPlayers()) {
                            player.bombCount = 0;
                            player.powerUps.clear();
                            player.curses.clear();
                        }
                        initialScreen.reset(this.players, this.rounds, this.map);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                JButton exitButton = new JButton("Back to Menu");
                exitButton.addActionListener(e -> {
                    dialog.dispose();
                    frame.getContentPane().removeAll();
                    frame.dispose();
                    GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, new GameGUI());
                    frame.add(initialScreen);
                });

                dialog.add(resumeButton);
                dialog.add(restartButton);
                dialog.add(exitButton);
                dialog.setVisible(true);
            } else {
                timer.start();
                pauseButton.setText("Pause");
            }
        });

        return timerPanel;
    }

}
