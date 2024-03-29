    package gui;

    import cell.Cell;
    import entity.player.Player;
    import levels.LevelReader;
    import map.Map;

    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.io.File;
    import java.io.IOException;
    import java.util.Objects;

    // Simple example to demonstrate the GameMapGUI structure.
    public class GameMapGUI extends JPanel {
        private JFrame frame;
        private Player player; // Add a player reference here
        private Map map;
        private int playerCount;
        private int roundCount;
        public Image wallImage;
        public Image walkableImage;
        public Image boxImage;
        public Image playerImage;
        private LevelReader lr = new LevelReader();
        public GameMapGUI(int roundCount, Map map, int playerCount, JFrame frame) throws IOException {
            this.map = map;
            this.roundCount = roundCount;
            this.playerCount = playerCount;
            this.frame = frame;
            this.wallImage = ImageIO.read(new File("src\\assets\\mapAssets\\map1\\map1wall.png"));
            this.walkableImage = ImageIO.read(new File("src\\assets\\mapAssets\\map1\\map1walkable.png"));
            this.boxImage = ImageIO.read(new File("src\\assets\\mapAssets\\map1\\map1box.png"));
            this.playerImage = ImageIO.read(new File("src\\assets\\jamil.jpg"));
            this.setFocusable(true);
            this.frame.setLocationRelativeTo(null);
            initializePlayer();
            setupKeyListener();
            updateGUI();


        }



        private void initializePlayer() {
            // Initialize your player object here instead of in paintComponent

            int x = 3;
            int y = 2;

            this.player = new Player(x, y, this.map, "Jamshud", 0); // Use the actual x and y values found
        }

        private void setupKeyListener() {
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println("Key pressed: " + e.getKeyCode()); // Debugging
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W: // W key
                            player.Move("87", map.getMap());
                            break;
                        case KeyEvent.VK_S: // S key
                            player.Move("83", map.getMap());
                            break;
                        case KeyEvent.VK_A: // A key
                            player.Move("65", map.getMap());
                            break;
                        case KeyEvent.VK_D: // D key
                            player.Move("68", map.getMap());
                            break;
                    }
                    repaint();
                }
            });
            frame.setVisible(true);
            SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
        }



        private void updateGUI() {
    //        this.setLayout(new BorderLayout());
    //        JLabel mapLabel = new JLabel("Map " + map.getName() + " Displayed Here", SwingConstants.CENTER);
    //        this.add(mapLabel, BorderLayout.CENTER);



    //        GameTopPanelGUI topPanel = new GameTopPanelGUI();
    //        this.add(topPanel, BorderLayout.NORTH); // Correctly adding the panel
            initializeLevel();
        }

        public void initializeLevel(){
            Timer moveTimer = new Timer(300, e -> repaint());
            moveTimer.start();

            moveTimer = new Timer(300, e -> {
            repaint();
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Cell[][] mapCell = this.map.getMap();

            int cellSize = 32;
            for(int i = 0; i < mapCell.length; i++){
                for (int j = 0; j < mapCell[0].length; j++) {
                    if(Objects.equals(mapCell[i][j].getType(), "#")){
                        g.drawImage(wallImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                    if(Objects.equals(mapCell[i][j].getType(), ".") || Objects.equals(mapCell[i][j].getType(), "S")){
                        g.drawImage(walkableImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                    if(Objects.equals(mapCell[i][j].getType(), "X")){
                        g.drawImage(boxImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                }
            }

            // Use the player instance to draw the player's current position
            g.drawImage(playerImage, player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);
        }}


