package gui;

import entity.player.Player;
import gameengine.GameEngine;
import item.curse.Curse;
import item.powerup.PowerUp;
import util.ResourceCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTopPanelGUI {
    private final GameEngine model;
    private final JPanel topPanel;
    private final JPanel timer;
    private final JPanel plPanel;
    private final JFrame frame;
    private Color background;
    private final List<Player> players;
    private final int rounds;
    private final int map;

    public GameTopPanelGUI(JFrame frame, GameEngine gameEngine,  int map, int rounds) {
        this.frame = frame;
        this.topPanel = new JPanel();
        this.timer = new JPanel();
        this.plPanel = new JPanel();
        this.model = gameEngine;
        this.players = model.getPlayers();
        this.rounds = rounds;
        this.map = map;

        this.topPanel.setPreferredSize(new Dimension(990, 130));
        this.topPanel.setMaximumSize(new Dimension(990, 130));
        this.topPanel.setMinimumSize(new Dimension(990, 130));
        this.topPanel.setBounds(0, 0, 990, 130);
        this.topPanel.setLayout(new BorderLayout());

        this.timer.setPreferredSize(new Dimension(200, 130));
        this.timer.setMaximumSize(new Dimension(200, 130));
        this.timer.setMinimumSize(new Dimension(200, 130));

        this.plPanel.setPreferredSize(new Dimension(790, 130));
        this.plPanel.setMaximumSize(new Dimension(790, 130));
        this.plPanel.setMinimumSize(new Dimension(790, 130));

        this.timer.setBackground(background);
        this.plPanel.setBackground(background);

        addContent();
        addTimerAndPause();

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

        this.plPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 25;

        //Player 1
        constraints.gridx = 0;
        if(!players.get(0).isDead())
            this.plPanel.add(createPlayerPanel(players.getFirst().getImageIndex(), 0), constraints);
        else {
            this.plPanel.add(createPlayerPanel(4, 0), constraints);
        }

        //Player 2
        constraints.gridx = 1;
        if(!players.get(1).isDead())
            this.plPanel.add(createPlayerPanel((players.get(1).getImageIndex()), 1), constraints);
        else {
            this.plPanel.add(createPlayerPanel(4, 1), constraints);
        }

        //Player 3
        if (model.getPlayerCount() == 3) {
            constraints.gridx = 2;
            if(!players.getLast().isDead())
                this.plPanel.add(createPlayerPanel((players.get(2).getImageIndex()), 2), constraints);
            else {
                this.plPanel.add(createPlayerPanel(4, 2), constraints);
            }
        }

        this.topPanel.add(this.plPanel, BorderLayout.WEST);
    }

    private JPanel createPlayerPanel(int playerIndex, int playerNumber) {
        List<Player> players = model.getPlayers();
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

        JLabel bombCount;
        if (playerIndex != 4) bombCount = new JLabel("  " + players.get(playerNumber).bombCount);
        else bombCount = new JLabel("  0");
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

        JLabel victoryCount;
        victoryCount = new JLabel("  " + players.get(playerNumber).victoryCount);
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

        if (playerIndex != 4) {
            for (PowerUp powerUp : players.get(playerNumber).powerUpsItems) {
                ImageIcon icon = new ImageIcon(powerUp.getImage());

                if(powerUp.isPowerUpAboutToFinish()) {
                    float transparencyLevel = 0.5f;
                    icon = createTransparentIcon(icon.getImage(), transparencyLevel);
                }

                JLabel powerUpLabel = new JLabel(icon);
                pwUpsCurses.add(powerUpLabel);
            }
        }
        if (playerIndex != 4) {
            for (Curse curse : players.get(playerNumber).cursesItems) {
                ImageIcon icon = new ImageIcon(curse.getImage());

                if(curse.isCurseAboutToFinish()) {
                    float transparencyLevel = 0.5f;
                    icon = createTransparentIcon(icon.getImage(), transparencyLevel);
                }

                JLabel curseLabel = new JLabel(icon);
                pwUpsCurses.add(curseLabel);
            }
        }

        playerPanel.add(playerInfo);
        playerPanel.add(pwUpsCurses);

        playerPanel.repaint();
        playerPanel.revalidate();

        return playerPanel;
    }

    private void addTimerAndPause() {
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
                            player.bombCount = 1;
                            player.powerUpsItems.clear();
                            player.cursesItems.clear();
                            player.setDead(false);
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

        this.timer.add(timerPanel);
        this.topPanel.add(this.timer, BorderLayout.EAST);
    }

    public void updateTopPanel() {
        this.plPanel.removeAll();
        addContent();
        this.topPanel.revalidate();
        this.topPanel.repaint();
    }

    private ImageIcon createTransparentIcon(Image img, float alpha) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }
}
