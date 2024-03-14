package gui;

import gameengine.GameEngine;
import entity.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class GameInitialScreenGUI extends JPanel {
    private final GameEngine gameEngine = new GameEngine();
    private final List<Player> players = new ArrayList<>();
    public GameInitialScreenGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel greetingLabel = new JLabel("Welcome to BOOMberman Game!");
        greetingLabel.setFont(new Font(greetingLabel.getFont().getName(), Font.BOLD, 30));
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));
        addMaps();
        //add(Box.createRigidArea(new Dimension(0, 5))); // Add space
        addPlayers();
    }

    private final ButtonGroup mapGroup = new ButtonGroup();

    private void addMaps() {
        JPanel maps = new JPanel();
        maps.setPreferredSize(new Dimension(900, 400));
        maps.setLayout(new BoxLayout(maps, BoxLayout.X_AXIS));

        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel(10));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel(11));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel(12));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space

        add(maps);
    }

    private JLayeredPane createMapPanel(int imgIndex) {
        JLayeredPane mapPanel = new JLayeredPane() {
            @Override
            public void doLayout() {
                super.doLayout();

                // Calculate the position of the checkbox after the layout
                int radioButtonPosition = 70;
                int x = getWidth() - radioButtonPosition + 25;
                int y = getHeight() - radioButtonPosition + 15;

                for (Component component : getComponents()) {
                    if (component instanceof JRadioButton) {
                        component.setBounds(x, y, radioButtonPosition, radioButtonPosition);
                    } else {
                        component.setBounds(0, 0, getWidth(), getHeight());
                    }
                }
            }
        };

        ImagePanel imagePanel = new ImagePanel(imgIndex);
        mapPanel.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        JRadioButton mapRadioButton = new JRadioButton();
        mapRadioButton.setPreferredSize(new Dimension(300, 300));
        mapGroup.add(mapRadioButton);
        mapPanel.add(mapRadioButton, JLayeredPane.PALETTE_LAYER);

        return mapPanel;
    }

    private void addPlayers() {
        JPanel playersAndRounds = new JPanel();
        playersAndRounds.setLayout(new GridBagLayout());
        playersAndRounds.setPreferredSize(new Dimension(1000, 200));

        GridBagConstraints constraints = new GridBagConstraints();

        // Player 1 Panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        constraints.fill = GridBagConstraints.BOTH;
        playersAndRounds.add(createPlayerPanel("Player 1", 0), constraints);

        // Player 2 Panel
        constraints.gridx = 1;
        playersAndRounds.add(createPlayerPanel("Player 2", 1), constraints);

        // Empty Panel to take up the remaining space
        constraints.gridx = 2;
        playersAndRounds.add(createPlayerPanel("Player 3", 2), constraints);
        playersAndRounds.add(new JPanel(), constraints);

        // Rounds and Start Panel
        JPanel roundsAndStart = new JPanel();
        roundsAndStart.setLayout(new BoxLayout(roundsAndStart, BoxLayout.Y_AXIS));
        roundsAndStart.setPreferredSize(new Dimension(250, 150));

        JLabel roundCountLabel = new JLabel("Round count");
        roundCountLabel.setFont(new Font(roundCountLabel.getFont().getName(), Font.BOLD, 15));
        roundCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(250, 150)); // Set preferred size
        startButton.setMaximumSize(new Dimension(200, 150)); // Set maximum size

        startButton.addActionListener(e -> {
            // May be replaced with the actual game engine
            gameEngine.startGame();
            //System.out.println("\n" + playerNames);
        });

        roundsAndStart.add(roundCountLabel);
        roundsAndStart.add(createRoundCountPanel());
        roundsAndStart.add(startButton);

        constraints.gridx = 3;
        playersAndRounds.add(roundsAndStart, constraints);
        add(playersAndRounds);
    }

    //private List<String> playerNames = new ArrayList<>();
    private JPanel createPlayerPanel(String playerName, int imgIndex) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setPreferredSize(new Dimension(250, playerPanel.getPreferredSize().height));

        JPanel playerCharacter = new JPanel();
        playerCharacter.setPreferredSize(new Dimension(250, 200));
        playerCharacter.setLayout(new BoxLayout(playerCharacter, BoxLayout.X_AXIS));
        //int charImgIndex = 0;

        ImagePanel imagePanel = new ImagePanel(imgIndex);
        imagePanel.setMaximumSize(new Dimension(100, 100));

        JButton leftArrow = new JButton("<-");

        final int[] imgIndexHolder = {imgIndex};
        leftArrow.addActionListener(e -> {
            if (imgIndexHolder[0] == 0) {
                imgIndexHolder[0] = 3;      // 3 is the last index of the array
            } else {
                imgIndexHolder[0]--;
            }
            imagePanel.updateImage(imgIndexHolder[0]);
            imagePanel.repaint();
            System.out.println(imgIndexHolder[0]);
        });

        JButton rightArrow = new JButton("->");
        rightArrow.addActionListener(e -> {
            if (imgIndexHolder[0] == 3) {
                imgIndexHolder[0] = 0;
            } else {
                imgIndexHolder[0]++;
            }
            imagePanel.updateImage(imgIndexHolder[0]);
            imagePanel.repaint();
            System.out.println(imgIndexHolder[0]);
        });

        playerCharacter.add(leftArrow);
        playerCharacter.add(imagePanel);
        playerCharacter.add(rightArrow);

        playerPanel.add(playerCharacter);

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setPreferredSize(new Dimension(250, 100));
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));


        JTextField playerNameField = new JTextField(playerName);
        playerNameField.setMaximumSize(new Dimension
                (playerNameField.getPreferredSize().width + 40, playerNameField.getPreferredSize().height));

        JButton playerControls = new JButton("W");

        playerNamePanel.add(playerNameField);
        playerNamePanel.add(playerControls);
        playerPanel.add(playerNamePanel);

        return playerPanel;
    }

    private JPanel createRoundCountPanel() {
        JPanel roundCount = new JPanel();
        roundCount.setPreferredSize(new Dimension(250, 150));
        roundCount.setLayout(new BoxLayout(roundCount, BoxLayout.X_AXIS));

        final int[] roundCountValueHolder = {5};
        final int maxRoundCount = 10;
        final int minRoundCount = 1;

        JTextArea roundCountArea = new JTextArea(String.valueOf(roundCountValueHolder[0]));

        roundCountArea.setMaximumSize(new Dimension
                (roundCountArea.getPreferredSize().width, roundCountArea.getPreferredSize().height));
        roundCountArea.setEditable(false);

        JButton decreaseRoundCount = new JButton("-");
        JButton increaseRoundCount = new JButton("+");

        decreaseRoundCount.addActionListener(e -> {
            if (roundCountValueHolder[0] > minRoundCount) {
                roundCountValueHolder[0]--;
                roundCountArea.setText(String.valueOf(roundCountValueHolder[0]));
            }
        });

        increaseRoundCount.addActionListener(e -> {
            if(roundCountValueHolder[0] < maxRoundCount) {
                roundCountValueHolder[0]++;
                roundCountArea.setText(String.valueOf(roundCountValueHolder[0]));
            }
        });

        roundCount.add(decreaseRoundCount);
        roundCount.add(roundCountArea);
        roundCount.add(increaseRoundCount);

        return roundCount;
    }
}
class ImagePanel extends JPanel {
    private final String[] characters = {
            "/assets/nahid.jpg",
            "/assets/mike.jpg",
            "/assets/gosha.jpg",
            "/assets/jamil.jpg"
    };

    private Image image;
    public ImagePanel(int img) {
        try {
            BufferedImage originalImage;
            String[] maps = {
                    "/assets/dnipro.jpg",
                    "/assets/sumy.jpg",
                    "/assets/azer.jpg",
            };
            switch (img) {
                case 0:
                    originalImage = ImageIO.read(getClass().getResource(characters[0]));
                    break;
                case 1:
                    originalImage = ImageIO.read(getClass().getResource(characters[1]));
                    break;
                case 2:
                    originalImage = ImageIO.read(getClass().getResource(characters[2]));
                    break;
                case 3:
                    originalImage = ImageIO.read(getClass().getResource(characters[3]));
                    break;
                case 10:
                    originalImage = ImageIO.read(getClass().getResource(maps[0]));
                    break;
                case 11:
                    originalImage = ImageIO.read(getClass().getResource(maps[1]));
                    break;
                case 12:
                    originalImage = ImageIO.read(getClass().getResource(maps[2]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid image name: " + img);
            }
            image = originalImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage(int imgIndex) {
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource(characters[imgIndex]));
            image = originalImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return getParent().getSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int cornerRadius = 20; // Adjust this value to change how rounded the corners are
            g2d.setClip(new java.awt.geom.RoundRectangle2D.Float
                    (0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g2d.drawImage(scaledImage, 0, 0, this);
            g2d.dispose();
        }
    }
}