package gui;

import gameengine.GameEngine;
import entity.player.Player;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



public class GameInitialScreenGUI extends JPanel {
    final int[] roundCountValueHolder = {5};
    private final JFrame frame;

    public final List<Player> players = new ArrayList<>();
    private final List<JTextField> playerNameFields = new ArrayList<>();

    private final List<HashMap<String, String>> controls;
    private final List<ImagePanel> imagePanels = new ArrayList<>();

    private final GameGUI gameGui;
    private JRadioButton selectedMapRadioButton;
    private int mapIndex;

    public GameInitialScreenGUI(JFrame frame, GameGUI gameGui) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.frame = frame;
        this.gameGui = gameGui;
        JLabel greetingLabel = new JLabel("Welcome to BOOMberman Game!");
        greetingLabel.setFont(new Font(greetingLabel.getFont().getName(), Font.BOLD, 30));
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingLabel);

        this.controls = new ArrayList<>();
        setupControls();

        add(Box.createRigidArea(new Dimension(0, 10)));
        addMaps();
        addPlayers();
    }

    public void setupControls() {
        // Player 1 controls
        HashMap<String, String> player1Controls = new HashMap<>();
        player1Controls.put("UP", "87"); // Up
        player1Controls.put("LEFT", "65"); // Left
        player1Controls.put("DOWN", "83"); // Down
        player1Controls.put("RIGHT", "68"); // Right
        player1Controls.put("BOMB", "66"); // Place bomb / B
        player1Controls.put("BOX", "81"); // Detonate bomb / Q

        // Player 2 controls
        HashMap<String, String> player2Controls = new HashMap<>();
        player2Controls.put("UP", "38"); // Up arrow key
        player2Controls.put("LEFT", "37"); // Left arrow key
        player2Controls.put("DOWN", "40"); // Down arrow key
        player2Controls.put("RIGHT", "39"); // Right arrow key
        player2Controls.put("BOMB", "10"); // Place bomb // ENTER
        player2Controls.put("BOX", "93"); // Detonate bomb / ]

        // Player 3 controls
        HashMap<String, String> player3Controls = new HashMap<>();
        player3Controls.put("UP", "73"); // I
        player3Controls.put("LEFT", "74"); // J
        player3Controls.put("DOWN", "75"); // K
        player3Controls.put("RIGHT", "76"); // L
        player3Controls.put("BOMB", "32"); // Space
        player3Controls.put("BOX", "79"); // O

        // Add the control maps to the controls list
        controls.add(player1Controls);
        controls.add(player2Controls);
        controls.add(player3Controls);
    }

    private final ButtonGroup mapGroup = new ButtonGroup();

    private JLayeredPane createMapPanel(int imgIndex) {
        JLayeredPane mapPanel = new JLayeredPane() {
            @Override
            public void doLayout() {
                super.doLayout();

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

        mapRadioButton.addActionListener(e -> selectedMapRadioButton = mapRadioButton);

        return mapPanel;
    }

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

    private JButton addPlayerButton;

    private JPanel createPlayerPanel(String playerName, int imgIndex) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setPreferredSize(new Dimension(250, playerPanel.getPreferredSize().height));

        JPanel playerCharacter = new JPanel();
        playerCharacter.setPreferredSize(new Dimension(250, 200));
        playerCharacter.setLayout(new BoxLayout(playerCharacter, BoxLayout.X_AXIS));

        ImagePanel imagePanel = new ImagePanel(imgIndex);
        imagePanel.setMaximumSize(new Dimension(100, 100));
        imagePanels.add(imagePanel);

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
        playerNameFields.add(playerNameField);

        JButton playerControls = new JButton("W");
        playerControls.setActionCommand(playerName);
        playerControls.addActionListener(e -> {
            String actionCommand = e.getActionCommand();

            switch (actionCommand) {
                case "Player 1":
                    showPlayerControls("Player 1 controls: W, A, S, D; B to place bomb");
                    break;
                case "Player 2":
                    showPlayerControls("Player 2 controls: Arrow keys; Enter to place bomb");
                    break;
                case "Player 3":
                    showPlayerControls("Player 3 controls: I, J, K, L; Space to place bomb");
                    break;
            }
        });

        if (playerName.equals("Player 3")) {
            JButton removePlayerButton = new JButton("Remove");

            removePlayerButton.setForeground(Color.RED);


            removePlayerButton.addActionListener(e -> {
                if (playerNameFields.size() == 3) {
                    playerNameFields.remove(2);
                }
                playerPanel.setVisible(false);
                playerPanel.getParent().remove(playerPanel);
                addPlayerButton.setVisible(true);
                playerPanel.repaint();
            });


            playerNamePanel.add(playerNameField);
            playerNamePanel.add(playerControls);
            playerNamePanel.add(removePlayerButton);
            playerPanel.add(playerNamePanel);
        } else {
            playerNamePanel.add(playerNameField);
            playerNamePanel.add(playerControls);
            playerPanel.add(playerNamePanel);
        }

        return playerPanel;
    }

    private void showPlayerControls(String controls) {
        JDialog playerControlsDialog = new JDialog();
        playerControlsDialog.setPreferredSize(new Dimension(330, 100));
        JLabel playerControlsLabel = new JLabel(controls);
        playerControlsDialog.add(playerControlsLabel);
        playerControlsDialog.pack();
        playerControlsDialog.setLocationRelativeTo(null);
        playerControlsDialog.setVisible(true);
    }

    private JPanel createRoundCountPanel() {
        JPanel roundCount = new JPanel();
        roundCount.setPreferredSize(new Dimension(250, 150));
        roundCount.setLayout(new BoxLayout(roundCount, BoxLayout.X_AXIS));

        final int maxRoundCount = 9;
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
            if (roundCountValueHolder[0] < maxRoundCount) {
                roundCountValueHolder[0]++;
                roundCountArea.setText(String.valueOf(roundCountValueHolder[0]));
            }
        });

        roundCount.add(decreaseRoundCount);
        roundCount.add(roundCountArea);
        roundCount.add(increaseRoundCount);

        return roundCount;
    }

    public int getRoundCount() {
        return roundCountValueHolder[0];
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
        addPlayerButton = new JButton("+");
        addPlayerButton.setPreferredSize(new Dimension(50, 50));


        playersAndRounds.add(addPlayerButton);

        addPlayerButton.addActionListener(e -> {
            constraints.gridx = 2;
            addPlayerButton.setVisible(false);
            playersAndRounds.add(createPlayerPanel("Player 3", 2), constraints);
            playersAndRounds.repaint();
        });

        // Rounds and Start Panel
        JPanel roundsAndStart = new JPanel();
        roundsAndStart.setLayout(new BoxLayout(roundsAndStart, BoxLayout.Y_AXIS));
        roundsAndStart.setPreferredSize(new Dimension(250, 150));

        JLabel roundCountLabel = new JLabel("Round count");
        roundCountLabel.setFont(new Font(roundCountLabel.getFont().getName(), Font.BOLD, 15));
        roundCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(250, 150));
        startButton.setMaximumSize(new Dimension(200, 150));

        startButton.addActionListener(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        roundsAndStart.add(roundCountLabel);
        roundsAndStart.add(createRoundCountPanel());
        roundsAndStart.add(startButton);

        constraints.gridx = 3;
        playersAndRounds.add(roundsAndStart, constraints);
        add(playersAndRounds);
    }

    private void startGame() throws IOException {
        Image[] images = new Image[4];
        images[0] = ResourceCollection.Images.JAMIL.getImage();
        images[1] = ResourceCollection.Images.NAHID.getImage();
        images[2] = ResourceCollection.Images.MIKE.getImage();
        images[3] = ResourceCollection.Images.GOSHA.getImage();

        if (selectedMapRadioButton != null) {
            for (int i = 0; i < playerNameFields.size(); i++) {
                String playerName = playerNameFields.get(i).getText();
                int imageIndex = imagePanels.get(i).getImgIndex();
                players.add(new Player(0, 0, null, playerName, imageIndex, controls.get(i), images[imageIndex]));
            }

            JLayeredPane mapPanel = (JLayeredPane) selectedMapRadioButton.getParent();
            ImagePanel imagePanel = null;
            for (Component component : mapPanel.getComponents()) {
                if (component instanceof ImagePanel) {
                    imagePanel = (ImagePanel) component;
                    break;
                }
            }

            if (imagePanel != null) {
                mapIndex = imagePanel.getImgIndex();
                setMapIndex(mapIndex);
            }

            gameGui.setGameEngine(new GameEngine(players, getRoundCount(), getMapIndex()));
            gameGui.startGame();

        } else {
            JDialog noMapSelected = new JDialog();
            JLabel noMapLabel = new JLabel("Please select a map");
            noMapLabel.setFont(new Font(noMapLabel.getFont().getName(), Font.BOLD, 15));
            noMapSelected.add(noMapLabel);
            noMapSelected.setSize(170, 100);
            noMapSelected.setBackground(Color.RED);
            noMapSelected.setLocationRelativeTo(null);
            noMapSelected.setVisible(true);
        }
    }

    public void reset(List<Player> players, int round, int map) throws IOException {
        gameGui.setGameEngine(new GameEngine(players, round, map));
        gameGui.startGame();
    }

    public int getMapIndex() {
        return mapIndex;
    }

    public void setMapIndex(int mapIndex){
        this.mapIndex = mapIndex;
    }
}

class ImagePanel extends JPanel {
    private Image image;
    private int imgIndex;

    private final Image[] characters = {
            ResourceCollection.Images.JAMIL.getImage(),
            ResourceCollection.Images.NAHID.getImage(),
            ResourceCollection.Images.MIKE.getImage(),
            ResourceCollection.Images.GOSHA.getImage(),
            ResourceCollection.Images.DEAD_NEW.getImage(),
    };

    private final Image[] maps = {
            ResourceCollection.Images.MAP1.getImage(),
            ResourceCollection.Images.MAP2.getImage(),
            ResourceCollection.Images.MAP3.getImage()
    };

    public ImagePanel(int img) {
        this.imgIndex = img;
        Image originalImage;

        originalImage = switch (img) {
            case 0 -> characters[0];
            case 1 -> characters[1];
            case 2 -> characters[2];
            case 3 -> characters[3];
            case 4 -> characters[4];

            case 10 -> maps[0];
            case 11 -> maps[1];
            case 12 -> maps[2];

            default -> throw new IllegalArgumentException("Invalid image name: " + img);
        };
        image = originalImage;
    }

    public void updateImage(int imgIndex) {

        String[] charact = {
                "src/main/java/assets/jamil.png",
                "src/main/java/assets/nahid.png",
                "src/main/java/assets/mike.png",
                "src/main/java/assets/gosha.png",
                "src/main/java/assets/dead.jpeg"
        };


        this.imgIndex = imgIndex;
        try {
            image = ImageIO.read(new File((charact[imgIndex])) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImgIndex() {
        return imgIndex;
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

            int cornerRadius = 20;
            g2d.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

            Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

            g2d.drawImage(scaledImage, 0, 0, this);
            g2d.dispose();
        }
    }
}