    package gui;

    import cell.Cell;
    import entity.player.Player;
    import gameengine.GameEngine;
    import item.GameItem;
    import item.bomb.Bomb;
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

            int delay = 1000 / 24; // Approximately 41 milliseconds

            Timer timer = new Timer(delay, e -> {
                // Your repeated task here.
                // For example, you might want to call repaint() on your component to trigger paintComponent.
                //System.out.println("Repainting...");
                try {
                    this.model.runGameUnit();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                this.repaint();
            });

            timer.start();
        }



        private void initializePlayer() {
            // Initialize your player object here instead of in paintComponent

            int x = 3;
            int y = 2;

//            this.players = model.getPlayers();
//            this.players.get(0).setX(x);
//            this.players.get(0).setY(y);
//            this.players.get(0).setGameMap(model.getMap());
//
//
//            this.players.get(1).setX(4);
//            this.players.get(1).setY(10);
//            this.players.get(1).setGameMap(model.getMap());



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
                    try {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_W: // W key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("87", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_S: // S key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("83", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_A: // A key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("65", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_D: // D key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("68", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_UP: // Up arrow key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("38", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_DOWN: // Down arrow key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("40", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_LEFT: // Left arrow key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("37", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_RIGHT: // Right arrow key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("39", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_B: // B key for Player 1 action

                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("66", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_ENTER: // Enter (Return) key for Player 2 action

                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("10", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_ESCAPE: // Escape key
                                frame.dispose();
                                break;
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
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
            for (Player player : model.getPlayers()) {
                g.drawImage(player.getImage(), player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);

            }

            for(int i = 0; i < this.model.getMap().getMap().length; i++){
                for (int j = 0; j < this.model.getMap().getMap()[0].length; j++) {
                    if (!this.model.getMap().getMap()[i][j].getItems().isEmpty()) {
                        for (GameItem item : this.model.getMap().getMap()[i][j].getItems()) {
                            if (item instanceof Bomb) {
                                g.drawImage(item.getImage(), j * cellSize, i * cellSize, cellSize, cellSize, this);
                            }
                        }
                    }
                }
            }

            // Draw foregrownd

            for (int i = 0; i < this.model.getMap().getMap().length; i++) {
                for (int j = 0; j < this.model.getMap().getMap()[0].length; j++) {
                    if (this.model.getMap().getMap()[i][j].getForegroundImage() != null) {
                        g.drawImage(this.model.getMap().getMap()[i][j].getForegroundImage(), j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                }
            }

            //g.drawImage(playerImage, player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);
        }}


