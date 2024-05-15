package gui;

import entity.player.Player;
import gameengine.GameEngine;
import item.curse.Curse;
import item.powerup.PowerUp;
import util.ResourceCollection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The graphical user interface class for the top panel of the game.
 * This panel displays player information, such as bombs, victories, power-ups, curses, and the game timer.
 */
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
    public Timer timerObj;

    /**
     * Constructs a GameTopPanelGUI object.
     *
     * @param frame      The main JFrame of the game.
     * @param gameEngine The game engine instance.
     * @param map        The map index.
     * @param rounds     The number of rounds in the game.
     */
    public GameTopPanelGUI(JFrame frame, GameEngine gameEngine, int map, int rounds) {
        this.frame = frame;
        this.topPanel = new JPanel();
        this.timer = new JPanel();
        this.plPanel = new JPanel();
        this.model = gameEngine;
        this.players = model.getPlayers();
        this.rounds = rounds;
        this.map = map;

        // Setting up the top panel
        this.topPanel.setPreferredSize(new Dimension(990, 130));
        this.topPanel.setMaximumSize(new Dimension(990, 130));
        this.topPanel.setMinimumSize(new Dimension(990, 130));
        this.topPanel.setBounds(0, 0, 990, 130);
        this.topPanel.setLayout(new BorderLayout());

        // Setting up the timer panel
        this.timer.setPreferredSize(new Dimension(200, 130));
        this.timer.setMaximumSize(new Dimension(200, 130));
        this.timer.setMinimumSize(new Dimension(200, 130));

        // Setting up the player panel
        this.plPanel.setPreferredSize(new Dimension(790, 130));
        this.plPanel.setMaximumSize(new Dimension(790, 130));
        this.plPanel.setMinimumSize(new Dimension(790, 130));

        // Setting background color
        this.timer.setBackground(background);
        this.plPanel.setBackground(background);

        // Adding content to the top panel
        addContent();
        // Adding timer and pause button
        addTimerAndPause();

        // Refreshing frame
        this.frame.revalidate();
        this.frame.repaint();
    }

    /**
     * Retrieves the top panel of the game.
     *
     * @return The top panel JPanel.
     */
    public JPanel getTopPanel() {
        return this.topPanel;
    }

    /**
     * Sets the background color of the top panel.
     *
     * @param color The color to set as background.
     */
    public void setColor(Color color) {
        background = color;
        this.topPanel.setOpaque(true);
        this.topPanel.setBackground(background);
    }

    /**
     * Adds player information and power-ups/curses to the player panel.
     */
    private void addContent() {
        this.plPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 25;

        // Adding Player 1 panel
        constraints.gridx = 0;
        if (!players.get(0).isDead())
            this.plPanel.add(createPlayerPanel(players.get(0).getImageIndex(), 0), constraints);
        else {
            this.plPanel.add(createPlayerPanel(4, 0), constraints);
        }

        // Adding Player 2 panel
        constraints.gridx = 1;
        if (!players.get(1).isDead())
            this.plPanel.add(createPlayerPanel((players.get(1).getImageIndex()), 1), constraints);
        else {
            this.plPanel.add(createPlayerPanel(4, 1), constraints);
        }

        // Adding Player 3 panel if applicable
        if (model.getPlayerCount() == 3) {
            constraints.gridx = 2;
            if (!players.get(model.getPlayerCount() - 1).isDead())
                this.plPanel.add(createPlayerPanel((players.get(2).getImageIndex()), 2), constraints);
            else {
                this.plPanel.add(createPlayerPanel(4, 2), constraints);
            }
        }

        this.topPanel.add(this.plPanel, BorderLayout.WEST);
    }

    /**
     * Creates a player information panel.
     *
     * @param playerIndex  The index of the player's image.
     * @param playerNumber The player's number.
     * @return The JPanel representing the player information.
     */
    private JPanel createPlayerPanel(int playerIndex, int playerNumber) {
        List<Player> players = model.getPlayers();
        JPanel playerPanel = new JPanel();

        // Setting up player panel properties
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(background);
        playerPanel.setPreferredSize(new Dimension(200, 130));
        playerPanel.setMaximumSize(new Dimension(200, 130));
        playerPanel.setMinimumSize(new Dimension(200, 130));

        // Setting up player info panel
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.X_AXIS));
        playerInfo.setBackground(background);
        playerInfo.setMaximumSize(new Dimension(200, 100));
        playerInfo.setMinimumSize(new Dimension(200, 100));

        // Setting up image panel
        ImagePanel imagePanel = new ImagePanel(playerIndex);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
        imagePanel.setBackground(background);
        imagePanel.setMaximumSize(new Dimension(100, 100));
        imagePanel.setMinimumSize(new Dimension(100, 100));

        // Setting up bombs and victories panel
        JPanel bombsAndVictories = new JPanel();
        bombsAndVictories.setLayout(new BoxLayout(bombsAndVictories, BoxLayout.Y_AXIS));
        bombsAndVictories.setBackground(background);
        bombsAndVictories.setMaximumSize(new Dimension(100, 100));
        bombsAndVictories.setMinimumSize(new Dimension(100, 100));

        // Setting up bombs panel
        JPanel bombs = new JPanel();
        bombs.setBackground(background);
        bombs.setLayout(new BoxLayout(bombs, BoxLayout.X_AXIS));
        bombs.setMaximumSize(new Dimension(100, 50));
        bombs.setMinimumSize(new Dimension(100, 50));

        // Adding bomb image
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

        // Setting up victories panel
        JPanel victories = new JPanel();
        victories.setBackground(background);
        victories.setLayout(new BoxLayout(victories, BoxLayout.X_AXIS));
        victories.setMaximumSize(new Dimension(100, 50));
        victories.setMinimumSize(new Dimension(100, 50));

        // Adding victory image
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

        // Adding bombs and victories to bombsAndVictories panel
        bombsAndVictories.add(bombs);
        bombsAndVictories.add(victories);

        // Setting alignment
        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        bombsAndVictories.setAlignmentY(Component.TOP_ALIGNMENT);

        // Adding image panel and bombsAndVictories panel to player info panel
        playerInfo.add(imagePanel);
        playerInfo.add(bombsAndVictories);

        // Setting up power-ups and curses panel
        JPanel pwUpsCurses = new JPanel();
        pwUpsCurses.setLayout(new BoxLayout(pwUpsCurses, BoxLayout.X_AXIS));
        pwUpsCurses.setBackground(background);
        pwUpsCurses.setMaximumSize(new Dimension(200, 30));
        pwUpsCurses.setMinimumSize(new Dimension(200, 30));

        // Adding power-ups icons
        if (playerIndex != 4) {
            for (PowerUp powerUp : players.get(playerNumber).powerUpsItems) {
                ImageIcon icon = new ImageIcon(powerUp.getImage());

                if (powerUp.isPowerUpAboutToFinish()) {
                    float transparencyLevel = 0.5f;
                    icon = createTransparentIcon(icon.getImage(), transparencyLevel);
                }

                JLabel powerUpLabel = new JLabel(icon);
                pwUpsCurses.add(powerUpLabel);
            }
        }
        // Adding curses icons
        if (playerIndex != 4) {
            for (Curse curse : players.get(playerNumber).cursesItems) {
                ImageIcon icon = new ImageIcon(curse.getImage());

                if (curse.isCurseAboutToFinish()) {
                    float transparencyLevel = 0.5f;
                    icon = createTransparentIcon(icon.getImage(), transparencyLevel);
                }

                JLabel curseLabel = new JLabel(icon);
                pwUpsCurses.add(curseLabel);
            }
        }

        // Adding player info and power-ups/curses panels to player panel
        playerPanel.add(playerInfo);
        playerPanel.add(pwUpsCurses);

        playerPanel.repaint();
        playerPanel.revalidate();

        return playerPanel;
    }

    /**
     * Adds the timer and pause button to the top panel.
     */
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
        timerObj = new Timer(1000, e -> {
            int seconds = elapsedSeconds.incrementAndGet();
            int minutes = seconds / 60;
            seconds %= 60;
            timerLabel.setText(String.format("%1$d:%2$02d", minutes, seconds));
        });
        timerObj.start();

        // Action listener for pause button
        pauseButton.addActionListener(event -> {
            if (timerObj.isRunning()) {
                timerObj.stop();
                GameMapGUI.stopTimer();
                JDialog dialog = new JDialog(frame, "Game Paused", true);
                dialog.setLayout(new GridLayout(3, 1));
                dialog.setSize(200, 300);
                dialog.setLocationRelativeTo(frame);

                JButton resumeButton = new JButton("Resume");
                resumeButton.addActionListener(e -> {
                    dialog.dispose();
                    timerObj.start();
                    GameMapGUI.startTimer();
                    GameMapGUI gameMapGUI = GameMapGUI.getInstance();
                    gameMapGUI.requestFocusInWindow();
                });

                JButton restartButton = new JButton("Restart");
                restartButton.addActionListener(e -> {
                    restartDialog(dialog, false);
                    GameMapGUI.startTimer();
                });

                JButton exitButton = new JButton("Back to Menu");
                exitButton.addActionListener(e -> {
                    backToMenu(dialog);
                });

                dialog.add(resumeButton);
                dialog.add(restartButton);
                dialog.add(exitButton);
                dialog.setVisible(true);
            } else {
                timerObj.start();
                pauseButton.setText("Pause");
            }
        });

        this.timer.add(timerPanel);
        this.topPanel.add(this.timer, BorderLayout.EAST);
    }

    /**
     * Updates the top panel.
     */
    public void updateTopPanel() {
        this.plPanel.removeAll();
        addContent();
        this.topPanel.revalidate();
        this.topPanel.repaint();
    }

    /**
     * Creates a transparent icon.
     *
     * @param img   The image to make transparent.
     * @param alpha The transparency level.
     * @return The transparent ImageIcon.
     */
    private ImageIcon createTransparentIcon(Image img, float alpha) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }

    /**
     * Returns to the main menu.
     *
     * @param dialog The dialog window.
     */
    public void backToMenu(JDialog dialog) {
        dialog.dispose();
        frame.getContentPane().removeAll();
        frame.dispose();
        GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, new GameGUI());
        frame.add(initialScreen);
    }

    /**
     * Restarts the game.
     *
     * @param dialog     The dialog window.
     * @param gameResult The result of the game.
     */
    public void restartDialog(JDialog dialog, boolean gameResult) {
        dialog.dispose();
        frame.dispose();
        GameInitialScreenGUI initialScreen = new GameInitialScreenGUI(frame, new GameGUI());
        try {
            for (Player player : model.getPlayers()) {
                player.bombCount = 1;
                player.setDead(false);
                player.cleanItems();
                if (gameResult) {
                    player.victoryCount = 0;
                }
            }
            initialScreen.reset(this.players, this.rounds, this.map);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
