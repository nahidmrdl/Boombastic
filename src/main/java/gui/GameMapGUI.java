    package gui;

    import cell.Cell;
    import cell.box.BoxCell;
    import cell.normalCell.NormalCell;
    import entity.monster.Monster;
    import entity.monster.monstertypes.ConfusedMonster;
    import entity.monster.monstertypes.GhostlyMonster;
    import entity.monster.monstertypes.SpeedyMonster;
    import entity.player.Player;
    import gameengine.GameEngine;
    import item.GameItem;
    import item.bomb.Bomb;
    import item.curse.Curse;
    import item.powerup.PowerUp;
    import util.ResourceCollection;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.io.IOException;
    import java.util.Random;
    import cell.wall.WallCell;

    public class GameMapGUI extends JPanel {
        private JFrame frame;
        private GameEngine model;
        public Image wallImage;
        public Image walkableImage;
        public Image boxImage;
        private final GameTopPanelGUI topPanelGUI;
        public static Timer moveTimer;
        private static Timer timer;
        private static GameMapGUI instance;

        public GameMapGUI( GameEngine model, JFrame frame, GameTopPanelGUI topPanelGUI) throws IOException {
            this.model = model;
            this.frame = frame;
            this.topPanelGUI = topPanelGUI;
            instance = this;

            loadMapAssetsRandomly();

            setupKeyListener();
            updateGUI();
            this.setFocusable(true);
            this.frame.setLocationRelativeTo(null);

            int delay = 1000 / 24;
            timer = new Timer(delay, e -> {
                try {
                    this.model.runGameUnit();
                    this.topPanelGUI.updateTopPanel();
                    long countDeadPlayers = model.getPlayers().stream().filter(Player::isDead).count();

                    if (countDeadPlayers == model.getPlayers().size() - 1) {
                        timer.stop();
                        showGameOverDialog();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                this.repaint();
            });

            timer.start();
        }

        public static GameMapGUI getInstance() {
            return instance;
        }

        private void showGameOverDialog() {
            topPanelGUI.timerObj.stop();
            JDialog dialog = new JDialog(frame, "Game over!", true);
            dialog.setSize(200, 300);
            dialog.setLocationRelativeTo(frame);

            for(Player player : model.getPlayers()){
                if(!player.isDead()){
                    player.victoryCount++;
                    topPanelGUI.updateTopPanel();
                    JLabel label = new JLabel();
                    label.setFont(new Font("Arial", Font.BOLD, 12));
                    if (!isGameOver()) {
                        dialog.setLayout(new GridLayout(3, 1));
                        label.setText("Player " + player.getName() + " won this round!");
                    } else {
                        JLabel trophyLabel = new JLabel(new ImageIcon(ResourceCollection.Images.TROPHY.getImage()));
                        dialog.setLayout(new GridLayout(4, 1));
                        dialog.add(trophyLabel);
                        label.setText("Player " + player.getName() + " won the game!");
                    }
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    dialog.add(label);
                }
            }

            JButton restart = new JButton("Restart");
            restart.addActionListener(e -> {
                topPanelGUI.restartDialog(dialog, isGameOver());
                dialog.dispose();
                frame.dispose();
            });
            dialog.add(restart);

            JButton menuButton = new JButton("Back to Menu");
            menuButton.addActionListener(e -> {
                topPanelGUI.backToMenu(dialog);
                dialog.dispose();
                frame.getContentPane().removeAll();
                frame.dispose();
            });
            dialog.add(menuButton);

            dialog.setVisible(true);
        }

        public boolean isGameOver() {
            boolean isGameOver = false;
            for (Player player : model.getPlayers()) {
                if (player.victoryCount == model.getRoundCount()) {
                    isGameOver = true;
                    break;
                }
            }
            return isGameOver;
        }

        public static void stopTimer() {timer.stop(); moveTimer.stop();}
        public static void startTimer() {timer.restart(); moveTimer.restart();}

        private void loadMapAssetsRandomly() throws IOException {
            Random random = new Random();

            // Define arrays of possible asset paths for each type
            Image[] wallPaths = {
                    ResourceCollection.Images.WALLMAP1.getImage(),
                    ResourceCollection.Images.WALLMAP2.getImage(),
                    ResourceCollection.Images.WALLMAP3.getImage()
            };

            Image[] walkablePaths = {
                    ResourceCollection.Images.GROUNDMAP1.getImage(),
                   // ResourceCollection.Images.GROUNDMAP2.getImage(), // this image is not visually appealing
                    ResourceCollection.Images.GROUNDMAP3.getImage()
            };

            Image[] boxPaths = {
                    ResourceCollection.Images.BOXMAP1.getImage(),
                    ResourceCollection.Images.BOXMAP2.getImage(),
                    ResourceCollection.Images.BOXMAP3.getImage(),
                    ResourceCollection.Images.PLACED_BOX.getImage()
            };

            // Randomly select an asset for each type
            wallImage = wallPaths[random.nextInt(wallPaths.length)];
            walkableImage = walkablePaths[random.nextInt(walkablePaths.length)];
            boxImage = boxPaths[random.nextInt(boxPaths.length)];

        }

        private void updateGUI() {
            initializeLevel();
        }

        public void initializeLevel(){
            this.moveTimer = new Timer(300, e -> {
                this.model.removeDeadMonsters(this.model.monsters);
                for(Monster ms : model.getMonsters()){
                    ms.moveRandomly();
                    for(Player p: model.getPlayers()){
                        if(ms.isNextToPlayer(p.getX(), p.getY())){
                            if(!p.isInvincible()){
                                p.setDead(true);
                            }
                        }
                    }
                }
                repaint();
            });
            moveTimer.start();
        }

        /**
         * Sets up the key listener for the game.
         */

        // refactor paintcomponent pls
        //TODO
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Cell[][] mapCell = this.model.getMap().getMap();
            int cellSize = 32;

            for (int i = 0; i < mapCell.length; i++) {
                for (int j = 0; j < mapCell[i].length; j++) {
                    Cell cell = mapCell[i][j];
                    if (cell instanceof NormalCell) {
                        g.drawImage(walkableImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    } else if (cell instanceof WallCell) {
                        g.drawImage(wallImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    } else if (cell instanceof BoxCell) {
                        g.drawImage(boxImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                    }
                }
            }

            for (Monster monster : model.getMonsters()){
                if(!monster.isDead()){
                    if (monster instanceof GhostlyMonster){
                        g.drawImage(ResourceCollection.Images.GHOSTLY_MONSTER.getImage(), monster.getX() * cellSize, monster.getY() * cellSize, cellSize, cellSize, this);

                    }
                    else if(monster instanceof SpeedyMonster){
                        g.drawImage(ResourceCollection.Images.SPEEDY_MONSTER.getImage(), monster.getX() * cellSize, monster.getY() * cellSize, cellSize, cellSize, this);

                    }
                    else if(monster instanceof ConfusedMonster){
                        g.drawImage(ResourceCollection.Images.CONFUSED_MONSTER.getImage(), monster.getX() * cellSize, monster.getY() * cellSize, cellSize, cellSize, this);

                    }
                    else{
                        g.drawImage(ResourceCollection.Images.BASIC_MONSTER.getImage(), monster.getX() * cellSize, monster.getY() * cellSize, cellSize, cellSize, this);
                    }
                }
            }


            // Draw players
            for (Player player : model.getPlayers()){
                if (!player.isDead()) {
                    Graphics2D g2d = (Graphics2D) g;
                    if (player.isGhost()) {
                        float alpha = 0.5f;
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                    } else {
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    }

                    g2d.drawImage(player.getImage(), player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
            }

            // Draw bombs
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

            //draw curses
            for (int i = 0; i < this.model.getMap().getMap().length; i++) {
                for (int j = 0; j < this.model.getMap().getMap()[0].length; j++) {
                    Cell cell = this.model.getMap().getMap()[i][j];
                    if (cell instanceof NormalCell) {
                        NormalCell normalCell = (NormalCell) cell;
                        int finalJ = j;
                        int finalI = i;
                        normalCell.getItems().forEach(item -> {
                            if (item instanceof Curse) {
                                // specific curse icons for debugging
                               // g.drawImage(item.getImage(), finalJ * cellSize, finalI * cellSize, cellSize, cellSize, this);
                                g.drawImage(ResourceCollection.Images.CURSE_ICON.getImage(), finalJ * cellSize, finalI * cellSize, cellSize, cellSize, this);
                            }
                        });
                    }
                }
            }

            // draw powerups
            for (int i = 0; i < this.model.getMap().getMap().length; i++) {
                for (int j = 0; j < this.model.getMap().getMap()[0].length; j++) {
                    Cell cell = this.model.getMap().getMap()[i][j];
                    if (cell instanceof NormalCell) {
                        NormalCell normalCell = (NormalCell) cell;
                        int finalJ = j;
                        int finalI = i;
                        normalCell.getItems().forEach(item -> {
                            if (item instanceof PowerUp) {
                                // image should be changed to baseImage for production
                                g.drawImage(item.getImage(), finalJ * cellSize, finalI * cellSize, cellSize, cellSize, this);
                            }
                        });
                    }
                }
            }
        }



        private void setupKeyListener() {
            this.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
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
                            // box
                            case KeyEvent.VK_Q: // Q key
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("81", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_O:
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("79", model.getMap().getMap());
                                }
                                break;
                            case KeyEvent.VK_CLOSE_BRACKET:
                                for (Player player : model.getPlayers()) {
                                    player.HandleAction("93", model.getMap().getMap());
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
    }


