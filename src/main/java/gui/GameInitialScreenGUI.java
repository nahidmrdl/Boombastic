package gui;

import gameengine.GameEngine;
import entity.player.Player;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The GameInitialScreenGUI class represents the initial screen of the game where players configure game settings.
 */
public class GameInitialScreenGUI extends JPanel {
    // Holder for round count value
    final int[] roundCountValueHolder = {5};
    private final JFrame frame;

    // Lists to store players, text fields for player names, control mappings, and image panels
    public final List<Player> players = new ArrayList<>();
    private final List<JTextField> playerNameFields = new ArrayList<>();
    private final List<HashMap<String, String>> controls;
    private final List<ImagePanel> imagePanels = new ArrayList<>();

    private final GameGUI gameGui;
    private JRadioButton selectedMapRadioButton;
    private int mapIndex;

    /**
     * Constructs a new GameInitialScreenGUI instance.
     * Initializes the initial screen GUI components.
     * @param frame The main JFrame of the game.
     * @param gameGui The GameGUI instance controlling the game's GUI.
     */
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

    // Setup control mappings for players
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

    // Create a map panel with map image and selection radio button
    private final ButtonGroup mapGroup = new ButtonGroup();
    private JLayeredPane createMapPanel(int imgIndex) {
        // Customized JLayeredPane to hold map image and radio button
        JLayeredPane mapPanel = new JLayeredPane() {
            @Override
            public void doLayout() {
                super.doLayout();

                // Position the radio button at the bottom right corner of the map image
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

        // Add map image to the layered pane
        ImagePanel imagePanel = new ImagePanel(imgIndex);
        mapPanel.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        // Add radio button for map selection
        JRadioButton mapRadioButton = new JRadioButton();
        mapRadioButton.setPreferredSize(new Dimension(300, 300));
        mapGroup.add(mapRadioButton);
        mapPanel.add(mapRadioButton, JLayeredPane.PALETTE_LAYER);

        mapRadioButton.addActionListener(e -> selectedMapRadioButton = mapRadioButton);

        return mapPanel;
    }

    // Add map selection panels to the initial screen
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

    // Create panel for each player with character selection, name input, and controls display
    private JButton addPlayerButton;
    private JPanel createPlayerPanel(String playerName, int imgIndex) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setPreferredSize(new Dimension(250, playerPanel.getPreferredSize().height));

        // Panel for selecting character image
        JPanel playerCharacter = new JPanel();
        playerCharacter.setPreferredSize(new Dimension(250, 200));
        playerCharacter.setLayout(new BoxLayout(playerCharacter, BoxLayout.X_AXIS));

        // Image panel for character selection
        ImagePanel imagePanel = new ImagePanel(imgIndex);
        imagePanel.setMaximumSize(new Dimension(100, 100));
        imagePanels.add(imagePanel);

        // Buttons for changing character image
        JButton leftArrow = new JButton("<-");
        JButton rightArrow = new JButton("->");
        final int[] imgIndexHolder = {imgIndex};

        // Action listeners for changing character image
        leftArrow.addActionListener(e -> {
            if (imgIndexHolder[0] == 0) {
                imgIndexHolder[0] = 3;      // 3 is the last index of the array
            } else {
                imgIndexHolder[0]--;
            }
            imagePanel.updateImage(imgIndexHolder[

                    0]);
            imagePanel.repaint();
        });

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

        // Panel for entering player name and displaying controls
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setPreferredSize(new Dimension(250, 100));
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));

        JTextField playerNameField = new JTextField(playerName);
        playerNameField.setMaximumSize(new Dimension(playerNameField.getPreferredSize().width + 40, playerNameField.getPreferredSize().height));
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

        // Button to remove player
        if (playerName.equals("Player 3")) {
            JButton removePlayerButton = new JButton("Remove");
            removePlayerButton.setForeground(Color.RED);

            // Action listener to remove player
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

    // Display dialog with player controls
    private void showPlayerControls(String controls) {
        JDialog playerControlsDialog = new JDialog();
        playerControlsDialog.setPreferredSize(new Dimension(330, 100));
        JLabel playerControlsLabel = new JLabel(controls);
        playerControlsDialog.add(playerControlsLabel);
        playerControlsDialog.pack();
        playerControlsDialog.setLocationRelativeTo(null);
        playerControlsDialog.setVisible(true);
    }

    // Create panel for selecting round count
    private JPanel createRoundCountPanel() {
        JPanel roundCount = new JPanel();
        roundCount.setPreferredSize(new Dimension(250, 150));
        roundCount.setLayout(new BoxLayout(roundCount, BoxLayout.X_AXIS));

        final int maxRoundCount = 9;
        final int minRoundCount = 1;

        JTextArea roundCountArea = new JTextArea(String.valueOf(roundCountValueHolder[0]));
        roundCountArea.setMaximumSize(new Dimension(roundCountArea.getPreferredSize().width, roundCountArea.getPreferredSize().height));
        roundCountArea.setEditable(false);

        // Buttons to increase/decrease round count
        JButton decreaseRoundCount = new JButton("-");
        JButton increaseRoundCount = new JButton("+");

        // Action listeners to adjust round count
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

    // Get the selected round count
    public int getRoundCount() {
        return roundCountValueHolder[0];
    }

    // Add player panels and round count panel to the initial screen
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

        // Action listener to add new player
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

        // Label for round count
        JLabel roundCountLabel = new JLabel("Round count");
        roundCountLabel.setFont(new Font(roundCountLabel.getFont().getName(), Font.BOLD, 15));
        roundCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button to start the game
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(250, 150));
        startButton.setMaximumSize(new Dimension(200, 150));

        // Action listener to start the game
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

    // Method to start the game with selected settings
    private void startGame() throws IOException {
        Image[] images = new Image[4];
        images[0] = ResourceCollection.Images.JAMIL.getImage();
        images[1] = ResourceCollection.Images.NAHID.getImage();
        images[2] = ResourceCollection.Images.MIKE.getImage();
        images[3] = ResourceCollection.Images.GOSHA.getImage();

        if (selectedMapRadioButton != null) {
            for (int i = 0; i < playerNameFields.size(); i++) {
                String playerName = playerNameFields.get(i

                ).getText();
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

    // Reset game with new settings
    public void reset(List<Player> players, int round, int map) throws IOException {
        gameGui.setGameEngine(new GameEngine(players, round, map));
        gameGui.startGame();
    }

    // Get the index of the selected map
    public int getMapIndex() {
        return mapIndex;
    }

    // Set the index of the selected map
    public void setMapIndex(int mapIndex){
        this.mapIndex = mapIndex;
    }
}

/**
 * This class represents a JPanel used for displaying images.
 * It allows for dynamic updating of the displayed image.
 */
class ImagePanel extends JPanel {
    private Image image; // The image to be displayed
    private int imgIndex; // Index of the image in the array

    // Arrays containing images for characters and maps
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

    /**
     * Constructs an ImagePanel with the specified image index.
     * @param img The index of the image to be displayed
     */
    public ImagePanel(int img) {
        this.imgIndex = img;
        Image originalImage;

        // Selects the image based on the provided index
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

    /**
     * Updates the displayed image based on the provided image index.
     * @param imgIndex The index of the new image to be displayed
     */
    public void updateImage(int imgIndex) {
        // Array containing file paths for character images
        String[] charact = {
                "src/main/java/assets/jamil.png",
                "src/main/java/assets/nahid.png",
                "src/main/java/assets/mike.png",
                "src/main/java/assets/gosha.png",
                "src/main/java/assets/dead.jpeg"
        };

        this.imgIndex = imgIndex;
        try {
            // Reads the image from file and sets it as the displayed image
            image = ImageIO.read(new File((charact[imgIndex])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the index of the currently displayed image.
     * @return The index of the currently displayed image
     */
    public int getImgIndex() {
        return imgIndex;
    }

    /**
     * Overrides the method to return the preferred size of the panel.
     * @return The preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return getParent().getSize();
    }

    /**
     * Overrides the method to paint the component.
     * Draws the scaled image with rounded corners on the panel.
     * @param g The Graphics object used for painting
     */
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
