    package gui;

    import cell.Cell;
    import cell.box.BoxCell;
    import cell.normalCell.NormalCell;
    import cell.wall.WallCell;
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
    import java.util.Map;
    import java.util.Objects;

    // Simple example to demonstrate the GameMapGUI structure.
    public class GameMapGUI extends JPanel {
        private JFrame frame;
        private GameEngine model;
        public Image wallImage, walkableImage, boxImage, playerImage;
        private LevelReader lr = new LevelReader();

        public GameMapGUI( GameEngine model, JFrame frame) throws IOException {
            this.model = model;
            this.frame = frame;
            this.wallImage = ImageIO.read(new File("src/assets/mapAssets/map1/map1wall.png"));
            this.walkableImage = ImageIO.read(new File("src/assets/mapAssets/map1/map1walkable.png"));
            this.boxImage = ImageIO.read(new File("src/assets/mapAssets/map1/map1box.png"));
            this.playerImage = ImageIO.read(new File("src/assets/jamil.jpg"));
            this.setFocusable(true);
            this.frame.setLocationRelativeTo(null);

            this.setupKeyListener();
            this.startGameTimer();
        }

        /**
         * Initializes and starts the game timer.
         */
        private void startGameTimer() {
            int delay = 1000 / 24;
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

        /**
         * Sets up the key listener for the game.
         */
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

                            case KeyEvent.VK_I: // I key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("73", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_J: // J key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("74", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_K: // K key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("75", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_L: // L key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("76", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_SPACE: // Spacebar for placing bomb
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("32", model.getMap().getMap());
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

        /**
         * Paints the map onto the graphics context.
         * @param g The graphics context to paint on.
         * @param cellSize The size of each cell in pixels.
         */
        private void paintMap(Graphics g, int cellSize) {
            Cell[][] mapCell = this.model.getMap().getMap();

            for(int i = 0; i < mapCell.length; i++){
                for (int j = 0; j < mapCell[0].length; j++) {

                    Cell cell = mapCell[i][j];

                    if(cell instanceof WallCell){
                        g.drawImage(wallImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                    if(cell instanceof NormalCell){
                        g.drawImage(walkableImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                    if(cell instanceof BoxCell){
                        g.drawImage(boxImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                }
            }
        }

        /**
         * Paints the players onto the graphics context.
         * @param g The graphics context to paint on.
         * @param cellSize The size of each cell in pixels.
         */
        private void paintPlayer(Graphics g, int cellSize) {
            for (Player player : model.getPlayers()) {
                g.drawImage(player.getImage(), player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);
            }
        }

        /**
         * Paints the bombs onto the graphics context.
         * @param g The graphics context to paint on.
         * @param cellSize The size of each cell in pixels.
         */
        private void paintBombs(Graphics g, int cellSize) {
            Cell [][] mapCell = this.model.getMap().getMap();

            for(int i = 0; i < mapCell.length; i++){
                for (int j = 0; j < mapCell[0].length; j++) {
                    if (!mapCell[i][j].getItems().isEmpty()) {
                        for (GameItem item : mapCell[i][j].getItems()) {
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
        }

        /**
         * Paints the components onto the graphics context.
         * @param g The graphics context to paint on.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);

            int cellSize = 32;

            this.paintMap(g, cellSize);

            this.paintPlayer(g, cellSize);

            this.paintBombs(g, cellSize);
        }}


