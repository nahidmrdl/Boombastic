package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameInitialScreenGUI extends JPanel {

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

    private void addMaps() {
        JPanel maps = new JPanel();
        maps.setPreferredSize(new Dimension(900, 400));
        maps.setLayout(new BoxLayout(maps, BoxLayout.X_AXIS));

        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel( "imgAzer"));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel( "imgSumy"));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space
        maps.add(createMapPanel("imgDnipro"));
        maps.add(Box.createRigidArea(new Dimension(10, 0))); // Add space

        add(maps);
    }

    private JLayeredPane createMapPanel(String imageName) {
        JLayeredPane mapPanel = new JLayeredPane() {
            @Override
            public void doLayout() {
                super.doLayout();

                // Calculate the position of the checkbox after the layout
                int checkboxSize = 70;
                int x = getWidth() - checkboxSize + 25;
                int y = getHeight() - checkboxSize + 15;

                for (Component component : getComponents()) {
                    if (component instanceof JCheckBox) {
                        component.setBounds(x, y, checkboxSize, checkboxSize);
                    } else {
                        component.setBounds(0, 0, getWidth(), getHeight());
                    }
                }
            }
        };

        ImagePanel imagePanel = new ImagePanel(imageName);
        mapPanel.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        JCheckBox mapCheckBox = new JCheckBox();
        mapPanel.add(mapCheckBox, JLayeredPane.PALETTE_LAYER);

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
        playersAndRounds.add(createPlayerPanel("Player 1", "imgNahid"), constraints);

        // Player 2 Panel
        constraints.gridx = 1;
        playersAndRounds.add(createPlayerPanel("Player 2", "imgGosha"), constraints);

        // Empty Panel to take up the remaining space
        constraints.gridx = 2;
        playersAndRounds.add(createPlayerPanel("Player 3", "imgJamil"), constraints);
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

        roundsAndStart.add(roundCountLabel);
        roundsAndStart.add(createRoundCountPanel());
        roundsAndStart.add(startButton);

        constraints.gridx = 3;
        playersAndRounds.add(roundsAndStart, constraints);
        add(playersAndRounds);
    }

    private JPanel createPlayerPanel(String playerName, String imageName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setPreferredSize(new Dimension(250, playerPanel.getPreferredSize().height));

        JPanel playerCharacter = new JPanel();
        playerCharacter.setPreferredSize(new Dimension(250, 200));
        playerCharacter.setLayout(new BoxLayout(playerCharacter, BoxLayout.X_AXIS));

        JButton leftArrow = new JButton("<-");
        JButton rightArrow = new JButton("->");

        ImagePanel imagePanel = new ImagePanel(imageName);
        imagePanel.setMaximumSize(new Dimension( 100, 100));

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

        JTextArea roundCountArea = new JTextArea("10");

        roundCountArea.setMaximumSize(new Dimension
                (roundCountArea.getPreferredSize().width, roundCountArea.getPreferredSize().height));
        roundCountArea.setEditable(false);

        JButton increaseRoundCount = new JButton("+");
        JButton decreaseRoundCount = new JButton("-");

        roundCount.add(increaseRoundCount);
        roundCount.add(roundCountArea);
        roundCount.add(decreaseRoundCount);

        return roundCount;
    }
}
class ImagePanel extends JPanel {
    private Image image;
    public ImagePanel(String img) {
        try {
            BufferedImage originalImage;
            switch (img) {
                case "imgNahid":
                    originalImage = ImageIO.read(getClass().getResource("/assets/nahid.jpg"));
                    break;
                case "imgGosha":
                    originalImage = ImageIO.read(getClass().getResource("/assets/gosha.jpg"));
                    break;
                case "imgAzer":
                    originalImage = ImageIO.read(getClass().getResource("/assets/sumy.jpg"));
                    break;
                case "imgDnipro":
                    originalImage = ImageIO.read(getClass().getResource("/assets/dnipro.jpg"));
                    break;
                case "imgSumy":
                    originalImage = ImageIO.read(getClass().getResource("/assets/azer.jpg"));
                    break;
                case "imgMike":
                    originalImage = ImageIO.read(getClass().getResource("/assets/mike.jpg"));
                    break;
                case "imgJamil":
                    originalImage = ImageIO.read(getClass().getResource("/assets/jamil.jpg"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid image name: " + img);
            }
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