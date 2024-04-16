package item.bomb;

import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import item.GameItem;
import util.ResourceCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Bomb extends GameItem {

    private Timer timer;
    private int state = 0;

    public int getBlastRadius() {
        return blastRadius;
    }

    private final int blastRadius = 1;



    public Bomb() throws IOException {
        super(ResourceCollection.Images.POWER_BOMBSTAGE1.getImage());

        this.setFinishTime(System.currentTimeMillis() + 3000);

        this.invokeDetonateAnimation();
    }
    private void invokeDetonateAnimation() {
        timer = new Timer(500, e -> {
            state++;

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            Cell[][] gameMap = this.getCell().getMap().getMap();
            int x = this.getCell().getX();
            int y = this.getCell().getY();

            switch (state) {
                case 1:
                    // Transition to the second state
                    this.setImage(ResourceCollection.Images.POWER_BOMBSTAGE2.getImage());
                    break;
                case 2:
                    // Transition to the third state
                    this.setImage(ResourceCollection.Images.POWER_BOMBSTAGE3.getImage());

                    break;
                case 3:
                    // Trigger the blast
                    this.setImage(ResourceCollection.Images.BLAST.getImage());


                    // change image in range


                    // The blast extends this far in each direction unless blocked by a wall

// Define directions: up, down, left, right


                    for (int[] direction : directions) {
                        for (int i = 1; i <= blastRadius; i++) {
                            int targetX = x + i * direction[0];
                            int targetY = y + i * direction[1];

                            // Ensure target coordinates are within map bounds
                            if (targetX < 0 || targetX >= gameMap.length || targetY < 0 || targetY >= gameMap[0].length) {
                                System.out.println("Target X: " + targetX + ", Target Y: " + targetY);

                                continue; // skip this iteration if target is out of bounds
                            }

                            Cell targetCell = gameMap[targetX][targetY];
                            // Stop the blast if it hits a wall
                            if (targetCell instanceof WallCell) { // '#' represents a wall
                                break; // Stops extending the blast in this direction
                            }

                            // If it's a BoxCell, replace it with a NormalCell, possibly carrying over a power-up
                            if (targetCell instanceof BoxCell) {
                                    NormalCell newCell = new NormalCell(targetX, targetY);
                                    newCell.setRandomPowerUp();
                                    newCell.setForegroundImage(ResourceCollection.Images.BLAST.getImage());
                                    gameMap[targetX][targetY] = newCell;
                            } else if (targetCell != null) {
                                targetCell.setForegroundImage(ResourceCollection.Images.BLAST.getImage());
                            }
                        }
                    }


                    // After the blast, perform any necessary cleanup
                    break;
                case 4:

                    // Remove bomb form cell and clear blast


                    for (int[] direction : directions) {
                        for (int i = 1; i <= blastRadius; i++) {
                            int targetX = x + i * direction[0];
                            int targetY = y + i * direction[1];

                            // Ensure target coordinates are within map bounds
                            if (targetX < 0 || targetX >= gameMap.length || targetY < 0 || targetY >= gameMap[0].length) {
                                System.out.println("Target X: " + targetX + ", Target Y: " + targetY);

                                continue; // skip this iteration if target is out of bounds
                            }

                            Cell targetCell = gameMap[targetX][targetY];
                            // Stop the blast if it hits a wall
                            if (targetCell instanceof WallCell) { // '#' represents a wall
                                break; // Stops extending the blast in this direction
                            }

                            // If it's a BoxCell, replace it with a NormalCell, possibly carrying over a power-up
                            if (targetCell instanceof BoxCell) {
                                NormalCell newCell = new NormalCell(targetX, targetY);
                                newCell.setRandomPowerUp();
                                newCell.setForegroundImage(null);
                                gameMap[targetX][targetY] = newCell;
                            } else if (targetCell != null) {
                                targetCell.setForegroundImage(null);
                            }
                        }
                    }

                    this.getCell().setForegroundImage(null);
                    this.getCell().getItems().remove(this);


                    timer.stop();
                    break;
            }
        });
        // Ensure the timer only runs once
        timer.setRepeats(true);
        // Start the timer
        timer.start();
    }

    /**
     * This method is used to change the image of the bomb
     * @param imagePath
     */


    private void changeImage(String imagePath) {
        try {
            this.setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
