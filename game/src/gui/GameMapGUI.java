    package gui;

    import cell.Cell;
    import entity.player.Player;
    import gameengine.GameEngine;
    import levels.LevelReader;
    import map.GameMap;

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
        private java.util.List<Player> players; // Add a player reference here
        private GameMap gameMap;

        private GameEngine model;
        public Image wallImage;
        public Image walkableImage;
        public Image boxImage;
        public Image playerImage;
        private LevelReader lr = new LevelReader();
        public GameMapGUI( GameEngine model, JFrame frame) throws IOException {
            this.model = model;
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
            System.out.println(model.getPlayers());


        }



        private void initializePlayer() {
            // Initialize your player object here instead of in paintComponent

            int x = 3;
            int y = 2;

            this.players = model.getPlayers();
            this.players.get(0).setX(x);
            this.players.get(0).setY(y);
            this.players.get(0).setGameMap(model.getMap());


            this.players.get(1).setX(4);
            this.players.get(1).setY(10);
            this.players.get(1).setGameMap(model.getMap());



            // Use the actual x and y values found
        }

        private void setupKeyListener() {
            this.addKeyListener(new KeyAdapter() {

//                @Override
//                public void keyPressed(KeyEvent e) {
//                    Integer key = e.getKeyCode(); // Get the action based on key code
//                    player.Move(key, model.getMap().getMap());
//                    repaint();
//                }
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println("Key pressed: " + e.getKeyCode()); // Debugging
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W: // W key
                            for (Player player : players) {
                                player.Move("87", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_S: // S key
                            for (Player player : players) {
                                player.Move("83", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_A: // A key
                            for (Player player : players) {
                                player.Move("65", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_D: // D key
                            for (Player player : players) {
                                player.Move("68", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_UP: // Up arrow key
                            for (Player player : players) {
                                player.Move("38", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_DOWN: // Down arrow key
                            for (Player player : players) {
                                player.Move("40", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_LEFT: // Left arrow key
                            for (Player player : players) {
                                player.Move("37", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_RIGHT: // Right arrow key
                            for (Player player : players) {
                                player.Move("39", model.getMap().getMap());
                            }
                            break;
                        case KeyEvent.VK_ESCAPE: // Escape key
                            frame.dispose();
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
            super.paintComponents(g);
            Cell[][] mapCell = this.model.getMap().getMap();

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
            for (Player player : players) {
                g.drawImage(player.getImage(), player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);

            }
            //g.drawImage(playerImage, player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);
        }}


